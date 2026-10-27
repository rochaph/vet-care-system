# Diário de estudos

Registro de cada sessão e banco de perguntas de revisão. A rotina das 15h usa este arquivo para escolher
as perguntas de revisão do dia.

## Banco de revisão

Perguntas curtas, feitas em múltipla escolha (4 alternativas, uma correta). Prioridade na revisão:
❌ → ⚠️ → nunca perguntada → ✅ mais antiga.

Resultado: ✅ acertou · ⚠️ parcial · ❌ errou · — ainda não perguntada

| # | Pergunta | Origem | Última revisão | Resultado |
|---|---|---|---|---|
| 1 | Por que Maven e não Gradle neste projeto? | 2026-10-14 | 2026-10-27 | ✅ |
| 2 | Para que serve o Maven Wrapper? | 2026-10-14 | 2026-10-27 | ✅ |
| 3 | Que problema a arquitetura em camadas tradicional causa no domínio? | 2026-10-15 | — | — |
| 4 | O que é um port de entrada? E um de saída? | 2026-10-15 | 2026-10-27 | ✅ |
| 5 | Por que o port de saída é declarado no centro, e não no adapter? | 2026-10-15 | — | — |
| 6 | Qual princípio do SOLID sustenta a Arquitetura Hexagonal? | 2026-10-15 | — | — |
| 7 | Quando a Arquitetura Hexagonal *não* vale a pena? | 2026-10-15 | — | — |
| 8 | Módulo único + ArchUnit vs. multi-módulo: quando cada um detecta a violação? | 2026-10-15 | — | — |
| 9 | Por que o multi-módulo não elimina o ArchUnit? | 2026-10-15 | — | — |
| 10 | Por que os casos de uso não levam `@Service` nem `@Transactional` aqui? | 2026-10-15 | 2026-10-27 | ✅ |
| 11 | Chamar o `payments` via HTTP gera que tipo de port, e onde ficam interface e implementação? | 2026-10-25 | 2026-10-27 | ✅ |
| 12 | No multi-módulo, em que módulo fica a `@SpringBootApplication` e quais módulos têm Spring no classpath? | 2026-10-25 | — | — |
| 13 | O que é o composition root e qual módulo cumpre esse papel? | 2026-10-27 | 2026-10-27 | ✅ |
| 14 | Como evitar um `@Bean` por caso de uso sem pôr Spring no `application`? | 2026-10-27 | — | — |
| 15 | Herdar `spring-boot-starter-parent` coloca o Spring no classpath do `domain`? | 2026-10-27 | — | — |

## Registro

Uma entrada por sessão, da mais recente para a mais antiga.

<!--
### AAAA-MM-DD · Mxx · título da missão

- **Status:** concluída | parcial | versão mínima
- **Aprendi:** 2–3 frases com as próprias palavras
- **Quiz do dia:** acertos / total; perguntas novas entram no banco de revisão
- **Ponto fraco:** o que precisa voltar na revisão
-->

### 2026-10-27 · M01 · ADR do multi-módulo (fechamento) e novo Bloco 1

- **Status:** concluída
- **Aprendi:** o `bootstrap` é o composition root: o único lugar que conhece as classes concretas e liga as peças.
  Casos de uso entram via `@Bean` porque `@Service` prenderia o `application` ao Spring; uma anotação `@UseCase`
  própria com `includeFilters` evita um `@Bean` por caso de uso.
- **Decisão:** o Bloco 1 foi reordenado. Spring Boot em camadas primeiro (M02–M06), refatoração para hexagonal e
  multi-módulo depois (M07–M11): uma novidade por vez, e sentir a dor antes do remédio. ADRs passam a ser escritos
  pelo Claude, em formato curto, e nunca são missão.
- **Quiz do dia:** não aplicado. Revisão: 4/4 (#1, #2, #4, #11) e checagem 2/2 (#10, #13).
- **Ponto fraco:** nenhum novo; Spring Boot em si ainda não foi praticado.

### 2026-10-25 · M01 · ADR do multi-módulo

- **Status:** parcial (checagem de ports feita; ADR 0004 rascunhado, aguardando aceite)
- **Aprendi:** port de entrada é o que a aplicação oferece ("me chamam"); port de saída é o que ela precisa ("eu chamo"),
  e as duas interfaces moram no `application`. Banco e outro serviço são a mesma coisa para o caso de uso: um port de saída.
  O Spring fica só em `adapters` e `bootstrap`; os casos de uso entram via `@Bean`.
- **Quiz do dia:** não aplicado. Revisão: #1 ⚠️, #2 ⚠️, #4 ❌.
- **Ponto fraco:** diferença entre port de entrada e de saída; papel do Maven Wrapper (fixar a versão).

### 2026-10-23 · Mudança de domínio

- **Status:** concluída; sem ADR (decisão de escopo do projeto, não de arquitetura)
- **Aprendi:** o que um domínio precisa ter para exercitar o roteiro: fluxo com compensação (Saga), recurso disputado,
  login com papéis, algo assíncrono e um agregado com estados. O domínio veterinário dá sentido real ao OAuth:
  o tutor autoriza a clínica a ler a carteira do pet (autorização delegada, com escopo e revogação).
- **Quiz do dia:** não aplicado.
- **Ponto fraco:** ainda não avaliado.

### 2026-10-18 · Rotina diária e escolha de domínio

- **Status:** concluída
- **Aprendi:** rotina diária criada (plano, diário e routine das 15h). Ideias de domínio comparadas: e-commerce, CS2 e veterinária.
- **Quiz do dia:** não aplicado.
- **Ponto fraco:** —

### 2026-10-15 · Conceito de Arquitetura Hexagonal

- **Status:** concluída (antes da rotina diária existir)
- **Aprendi:** Ports & Adapters; inversão de dependência; diferença entre módulo único + ArchUnit e multi-módulo.
  Decisão: multi-módulo em todos os serviços (vira ADR 0004 na M01).
- **Quiz do dia:** não aplicado. Ficaram em aberto 2 perguntas de checagem:
  1. No caso de uso "agendar atendimento", quais seriam os ports (in e out), e em qual módulo mora cada interface e cada implementação?
  2. Por que o `AppointmentRepository` é declarado no `application`, e não no módulo de adapters?
- **Ponto fraco:** ainda não avaliado.

### 2026-10-14 · Ferramenta de build

- **Status:** concluída
- **Aprendi:** Maven vs. Gradle e como escrever um ADR com forças, alternativas justas e custos aceitos.
  Resultado: [ADR 0003](../adr/0003-maven-como-ferramenta-de-build.md).
- **Quiz do dia:** não aplicado.
- **Ponto fraco:** a primeira versão do ADR não tinha contexto específico nem custos.
