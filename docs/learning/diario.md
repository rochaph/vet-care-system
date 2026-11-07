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
| 3 | Que problema a arquitetura em camadas tradicional causa no domínio? | 2026-10-15 | 2026-10-28 | ✅ |
| 4 | O que é um port de entrada? E um de saída? | 2026-10-15 | 2026-11-07 | ✅ |
| 5 | Por que o port de saída é declarado no centro, e não no adapter? | 2026-10-15 | 2026-10-28 | ✅ |
| 6 | Qual princípio do SOLID sustenta a Arquitetura Hexagonal? | 2026-10-15 | 2026-10-28 | ✅ |
| 7 | Quando a Arquitetura Hexagonal *não* vale a pena? | 2026-10-15 | 2026-10-28 | ✅ |
| 8 | Módulo único + ArchUnit vs. multi-módulo: quando cada um detecta a violação? | 2026-10-15 | 2026-11-05 | ✅ |
| 9 | Por que o multi-módulo não elimina o ArchUnit? | 2026-10-15 | 2026-11-05 | ✅ |
| 10 | Por que os casos de uso não levam `@Service` nem `@Transactional` aqui? | 2026-10-15 | 2026-10-27 | ✅ |
| 11 | Chamar o `payments` via HTTP gera que tipo de port, e onde ficam interface e implementação? | 2026-10-25 | 2026-10-27 | ✅ |
| 12 | No multi-módulo, em que módulo fica a `@SpringBootApplication` e quais módulos têm Spring no classpath? | 2026-10-25 | 2026-11-05 | ✅ |
| 13 | O que é o composition root e qual módulo cumpre esse papel? | 2026-10-27 | 2026-10-27 | ✅ |
| 14 | Como evitar um `@Bean` por caso de uso sem pôr Spring no `application`? | 2026-10-27 | 2026-11-07 | ❌ |
| 15 | Herdar `spring-boot-starter-parent` coloca o Spring no classpath do `domain`? | 2026-10-27 | 2026-11-05 | ✅ |
| 16 | ADR 0002: por que Go só no `notifications`, e não em todos os serviços? | 2026-10-27 | 2026-11-05 | ✅ |
| 17 | ADR 0002: por que Java 25 e não Java 21? | 2026-11-01 | 2026-11-05 | ✅ |
| 18 | Como o Spring Boot decide o que autoconfigurar? | 2026-11-05 | 2026-11-05 | ✅ |
| 19 | Por que injeção pelo construtor, e não `@Autowired` no campo? | 2026-11-05 | 2026-11-05 | ✅ |
| 20 | O que faz um método de `@RestController` devolver JSON? | 2026-11-05 | 2026-11-05 | ✅ |
| 21 | Por que um arquivo apagado de `src` ainda pode valer depois de `mvnw verify`? | 2026-11-05 | 2026-11-05 | ✅ |
| 22 | Em que charset o Spring Boot lê `application.properties` e `application.yml`? | 2026-11-05 | 2026-11-07 | ✅ |
| 23 | ADR 0005: por que o API Gateway fica no `bff`, e não em um serviço separado? | 2026-11-05 | 2026-11-05 | ✅ |
| 24 | Qual a diferença entre Ingress, API Gateway e BFF? | 2026-11-05 | 2026-11-07 | ✅ |
| 25 | Por que `pet_id` na tabela de atendimentos não tem foreign key? | 2026-11-05 | 2026-11-05 | ✅ |
| 26 | O que acontece ao editar uma migration Flyway que já foi aplicada? | 2026-11-05 | 2026-11-05 | ✅ |
| 27 | Numa transação, `setStatus()` numa entidade carregada, sem `save()`: o que acontece? | 2026-11-07 | 2026-11-07 | ❌ |
| 28 | Por que `@Enumerated(EnumType.STRING)` e não o padrão `ORDINAL`? | 2026-11-07 | 2026-11-07 | ✅ |
| 29 | Por que `timestamptz`, e não `timestamp`, para o horário do atendimento? | 2026-11-07 | 2026-11-07 | ❌ |
| 30 | Quando é aceitável editar uma migration Flyway já aplicada? | 2026-11-07 | 2026-11-07 | ✅ |

## Registro

Uma entrada por sessão, da mais recente para a mais antiga.

<!--
### AAAA-MM-DD · Mxx · título da missão

- **Status:** concluída | parcial | versão mínima
- **Aprendi:** 2–3 frases com as próprias palavras
- **Quiz do dia:** acertos / total; perguntas novas entram no banco de revisão
- **Ponto fraco:** o que precisa voltar na revisão
-->

### 2026-11-07 · M03 · Postgres, JPA e Flyway (fechamento)

- **Status:** concluída
- **Aprendi:** Flyway com `V1__create_appointments.sql` aplicada na subida e checksum no `flyway_schema_history`;
  migration só se edita enquanto não saiu da máquina, depois disso é uma nova versão. Entidade `Appointment` com
  `JpaRepository` e `ddl-auto: validate` (o Flyway cria o schema, o Hibernate só confere). Data Mapper (JPA) vs.
  Active Record (Eloquent). Tropeços que viraram lição: `timestamp` sem fuso, escopos `compile`/`runtime` trocados,
  bloco `jpa` fora de `spring:` (ignorado em silêncio) e campos `public` que pulam o proxy do Hibernate.
- **Quiz do dia:** 2/4 (#27 ❌, #29 ❌). Revisão: 3/4 (#14 ❌ pela segunda vez; exemplo resolvido mostrado).
- **Ponto fraco:** dirty checking (#27); o que o `timestamptz` guarda (#29); `@UseCase` com `includeFilters` (#14).

### 2026-11-05 · M03 · Postgres, JPA e Flyway

- **Status:** parcial (Postgres no compose com `.env`; a aplicação ainda não conecta ao banco)
- **Aprendi:** cada serviço tem o próprio container de banco, e por isso `pet_id` não tem foreign key. Nas portas do
  compose, o IP define de onde o banco aceita conexões: sem IP, o Docker usa `0.0.0.0`; com `127.0.0.1`, só a própria
  máquina. O Flyway foi só apresentado: versiona o schema em SQL e usa checksum para detectar migration editada.
  Decisão: o `bff` será também o API Gateway, com Spring Cloud Gateway, no Bloco 3 (ADR 0005).
- **Quiz do dia:** 3/4 (#24 ❌). Revisão: 3/4 (#14 ❌).
- **Ponto fraco:** diferença entre Ingress, API Gateway e BFF (#24); `@UseCase` própria com `includeFilters` (#14).
- **Próximo passo:** dependências do Flyway e `datasource` no `application.yaml`, depois `V1__create_appointments.sql`.

### 2026-11-05 · M02 · Spring Boot do zero

- **Status:** concluída (iniciada em 2026-10-28; versão mínima em 2026-11-01)
- **Aprendi:** `GET /clinic` passando por controller → service → `@ConfigurationProperties`. IoC, beans, starters e
  autoconfiguração pelo classpath, em paralelo com o service container do Laravel. Dois tropeços viraram lição: o
  Spring Boot lê `.properties` em ISO-8859-1 (só o YAML é UTF-8), o que corrompeu o acento; e um arquivo apagado de
  `src` continua em `target` até o `clean`.
- **Quiz do dia:** 4/4. Revisão: 3/4 (#12 ❌).
- **Ponto fraco:** quais módulos têm Spring no classpath (#12); sintaxe de `record`.

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
