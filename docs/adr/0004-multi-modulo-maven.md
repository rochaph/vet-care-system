# 4. Multi-módulo Maven em todos os serviços Java

- **Status:** aceita
- **Data:** 2026-10-25

## Contexto

A Arquitetura Hexagonal exige que as dependências apontem para dentro: o `domain` não conhece framework e o
`application` não conhece os adapters. Num módulo Maven único, todas as camadas compartilham o mesmo classpath,
então nada impede o domínio de importar `jakarta.persistence` ou um caso de uso de chamar um controller.
A regra vira convenção, e convenção se perde com o tempo. Violar uma fronteira precisa quebrar o build.

## Alternativas consideradas

- **Módulo único + ArchUnit**: um POM só e estrutura simples. A violação só aparece quando os testes rodam, e a IDE
  continua sugerindo a classe proibida no autocomplete.
- **Multi-módulo em todos os serviços**: cada camada é um módulo com as próprias dependências, e um import proibido
  nem compila, porque a classe não está no classpath. Em troca, são mais POMs e mais cerimônia por serviço.
- **Multi-módulo só no `appointments`** (serviço de referência): menos cerimônia, mas cria dois padrões de estrutura
  no projeto, contra a padronização que motivou o [ADR 0003](0003-maven-como-ferramenta-de-build.md).

## Decisão

Todo serviço Java tem um POM parent/agregador e 4 módulos:

- `<serviço>-domain`: agregados, value objects e regras; sem nenhuma dependência de framework.
- `<serviço>-application`: casos de uso e ports `in`/`out`; depende só do `domain`.
- `<serviço>-adapters`: adapters de entrada e saída (REST, JPA, Kafka, clientes HTTP); depende do `application`.
- `<serviço>-bootstrap`: classe `main` e wiring via `@Configuration` + `@Bean`; depende de todos.

O ArchUnit continua, para as regras que o compilador não enxerga *dentro* de um módulo
(ex.: um controller não acessa um repositório JPA diretamente).

O `notifications`, em Go, fica fora desta decisão.

## Consequências

- As fronteiras são garantidas pelo compilador: a violação aparece na IDE e no `compile`, antes de qualquer teste.
- `domain` e `application` são testados sem subir Spring, como exige o [CLAUDE.md](../../CLAUDE.md).
- Custo aceito: 5 POMs por serviço, build um pouco mais lento e wiring explícito dos casos de uso, sem component scan.
- Risco aceito: excesso de estrutura em serviços pequenos, como o `payments`. Se pesar, um serviço pode voltar a
  módulo único por meio de um novo ADR.
