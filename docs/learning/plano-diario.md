# Plano diário

Uma missão por dia, de ~60 minutos, com começo, meio e fim. A rotina das 15h lê este arquivo e o
[diário](diario.md) para propor a missão do dia e as perguntas de revisão.

## Regras

- **Uma missão por dia.** Terminou cedo? Pare ou revise. Não puxe a próxima.
- **Pulou um dia? Sem acúmulo.** No dia seguinte a missão é a mesma, nunca duas.
- **Dia difícil? Faça a versão mínima.** Ela mantém o hábito vivo, e isso vale mais que a missão inteira.
- **Toda sessão segue o ciclo:** revisão (perguntas curtas de dias anteriores) → missão → quiz do dia → registro no diário → commit e push.
- **ADR: o Claude escreve, você estuda.** Quando surge uma decisão com trade-off, o Claude escreve o ADR curto
  na hora, mostra na sessão e você concorda ou discorda com um clique. O trade-off vira pergunta no banco de revisão
  e a linha entra no [índice](../adr/README.md). Escrever ADR nunca é missão nem critério de pronto.
- O plano é vivo: missões podem ser quebradas, juntadas ou reordenadas na revisão de cada bloco.

## Progresso

| Missão | Bloco | Status |
|---|---|---|
| M01 · ADR do multi-módulo | 1 | concluída |
| M02 · Spring Boot do zero | 1 | pendente |
| M03 · Postgres, JPA e Flyway | 1 | pendente |
| M04 · CRUD de agendamento em camadas | 1 | pendente |
| M05 · Testes no Spring | 1 | pendente |
| M06 · Regras de negócio e a dor | 1 | pendente |
| M07 · Domínio puro | 1 | pendente |
| M08 · Ports e caso de uso | 1 | pendente |
| M09 · Adapters e wiring | 1 | pendente |
| M10 · Testes de arquitetura com ArchUnit | 1 | pendente |
| M11 · Extrair multi-módulo | 1 | pendente |
| M12 · Fronteira transacional | 1 | pendente |
| M13 · Fechamento do Bloco 1 | 1 | pendente |

Status possíveis: `pendente`, `em andamento`, `concluída`.

## Bloco 1: Spring Boot e Arquitetura Hexagonal no `appointments`

Uma novidade por vez, sempre com código rodando. Primeiro o Spring Boot em camadas (M02–M06), até sentir a dor de
regra de negócio espalhada. Depois a refatoração para hexagonal (M07–M10) e, por fim, para multi-módulo (M11),
que é a estrutura final do [ADR 0004](../adr/0004-multi-modulo-maven.md). Os testes da M05 são a rede de segurança
de toda a refatoração.

O `appointments` é o serviço de referência. Os demais nascem nos blocos em que entram em cena:
`inventory` e `payments` no Bloco 2, `pets` e `bff` no Bloco 3.

### M01 · ADR do multi-módulo

- **Por quê:** fechar como as fronteiras do hexágono serão garantidas.
- **Pronto quando:**
  - [x] Perguntas de checagem sobre ports respondidas e revisadas
  - [x] ADR 0004 (multi-módulo Maven em todos os serviços) aceito
  - [x] Documentação commitada
- **Conceito de entrevista:** como garantir que as dependências apontem para dentro (compilador vs. teste).

### M02 · Spring Boot do zero

- **Por quê:** aprender o framework sozinho, antes de misturar arquitetura.
- **Primeiro passo (5 min):** gerar o projeto no start.spring.io (Maven, Java 21, Spring Boot 4, Spring Web) em
  `services/appointments` e rodar `./mvnw spring-boot:run`.
- **Pronto quando:**
  - [ ] Projeto em módulo único com Maven Wrapper versionado
  - [ ] Um endpoint `GET` respondendo, com controller que recebe um `@Service` por injeção via construtor
  - [ ] Um valor lido do `application.yml` com `@ConfigurationProperties`
- **Conceito de entrevista:** IoC e injeção de dependência, bean, starter, autoconfiguração
  (paralelo com o Laravel: service container ≈ `ApplicationContext`, service provider ≈ `@Configuration`).
- **Versão mínima:** projeto gerado e subindo.

### M03 · Postgres, JPA e Flyway

- **Por quê:** persistir de verdade; é o equivalente ao Eloquent com migrations.
- **Primeiro passo (5 min):** subir só um Postgres com `infra/docker-compose`.
- **Pronto quando:**
  - [ ] Postgres no compose, configuração via `.env` (só `.env.example` versionado)
  - [ ] Migration Flyway da tabela de atendimentos, aplicada quando a aplicação sobe
  - [ ] Entidade `@Entity Appointment` e uma interface `JpaRepository`
- **Conceito de entrevista:** ORM, Data Mapper (JPA) vs. Active Record (Eloquent), Spring Data, versionamento de schema.
- **Versão mínima:** Postgres no compose e a aplicação conectando.

### M04 · CRUD de agendamento em camadas

- **Por quê:** o fluxo completo controller → service → repository, do jeito mais comum no mercado.
- **Primeiro passo (5 min):** escrever o JSON de request e response de "agendar atendimento".
- **Pronto quando:**
  - [ ] `POST /appointments` e `GET /appointments/{id}`, com DTOs em `record`
  - [ ] Validação com Bean Validation (`@Valid`)
  - [ ] Erros respondidos como Problem Details (RFC 9457) via `@RestControllerAdvice`
  - [ ] Agendar e consultar com `curl`
- **Conceito de entrevista:** por que DTO ≠ entidade; validação; tratamento global de erros.
- **Versão mínima:** `POST` salvando no banco.

### M05 · Testes no Spring

- **Por quê:** ter rede de segurança antes de refatorar.
- **Primeiro passo (5 min):** listar os cenários (agendar com sucesso, payload inválido, id inexistente).
- **Pronto quando:**
  - [ ] Teste unitário do service com Mockito
  - [ ] Teste do controller com `@WebMvcTest`
  - [ ] Teste de integração com Testcontainers (`@ServiceConnection`) contra Postgres real
  - [ ] `./mvnw verify` verde
- **Conceito de entrevista:** pirâmide de testes, slices do Spring, por que não H2.
- **Versão mínima:** um `@WebMvcTest` passando.

### M06 · Regras de negócio e a dor

- **Por quê:** sentir o problema que a Arquitetura Hexagonal resolve, antes de aprender a solução.
- **Primeiro passo (5 min):** listar 3 regras (não agendar no passado; horário do veterinário já ocupado;
  cancelar só atendimento agendado, com reembolso conforme a antecedência).
- **Pronto quando:**
  - [ ] Regras implementadas onde parecer natural no código em camadas, com testes
  - [ ] Endpoint de cancelamento
  - [ ] Anotação curta no diário: onde cada regra foi parar e o que incomodou
- **Conceito de entrevista:** modelo anêmico vs. rico; por que regra no service se espalha.
- **Versão mínima:** uma regra com teste e a anotação da dor.

### M07 · Domínio puro

- **Por quê:** tirar as regras do service e da entidade JPA e colocá-las num modelo que não conhece framework.
- **Primeiro passo (5 min):** desenhar a máquina de estados do atendimento
  (agendado → check-in → em atendimento → finalizado; cancelado; faltou).
- **Pronto quando:**
  - [ ] Pacote `domain` com `Appointment` (sem setters públicos nem anotações JPA), `AppointmentStatus` e `TimeSlot`
  - [ ] Pet e veterinário referenciados só por ID (são de outros serviços)
  - [ ] Regras da M06 movidas para o agregado, com testes unitários sem Spring
- **Conceito de entrevista:** agregado, value object, invariantes, referência entre agregados por ID.
- **Versão mínima:** `TimeSlot` com testes.

### M08 · Ports e caso de uso

- **Por quê:** é aqui que ports de entrada e de saída deixam de ser teoria.
- **Primeiro passo (5 min):** escrever só as assinaturas de `ScheduleAppointmentUseCase` e `AppointmentRepository`.
- **Pronto quando:**
  - [ ] Pacote `application` com o port de entrada e um comando, e o port de saída
  - [ ] Caso de uso sem anotações Spring, recusando horário ocupado
  - [ ] Teste unitário do caso de uso com repositório em memória
- **Conceito de entrevista:** ports in/out, inversão de dependência, comandos.
- **Versão mínima:** ports declarados e compilando.

### M09 · Adapters e wiring

- **Por quê:** ligar o núcleo ao mundo real sem que ele saiba disso.
- **Primeiro passo (5 min):** listar as classes que vão para `adapters` (controller, entidade JPA, repositório).
- **Pronto quando:**
  - [ ] Entidade JPA separada do domínio, com mapper, num adapter que implementa `AppointmentRepository`
  - [ ] Controller chamando o port de entrada
  - [ ] Caso de uso registrado via `@Configuration` + `@Bean`
  - [ ] Testes da M05 verdes, com o mesmo comportamento de antes
- **Conceito de entrevista:** composition root, wiring explícito, modelo de persistência vs. domínio.
- **Versão mínima:** adapter JPA implementando o port.

### M10 · Testes de arquitetura com ArchUnit

- **Por quê:** em módulo único, só um teste garante as fronteiras.
- **Primeiro passo (5 min):** escrever em português as 3 regras (ex.: `domain` não depende de Spring nem de JPA).
- **Pronto quando:**
  - [ ] Regras ArchUnit rodando no `verify`
  - [ ] Uma violação proposital quebra o build (e depois é revertida)
- **Conceito de entrevista:** fitness functions de arquitetura.
- **Versão mínima:** uma regra funcionando.

### M11 · Extrair multi-módulo

- **Por quê:** passar a proteção das fronteiras do teste para o compilador (ADR 0004).
- **Primeiro passo (5 min):** desenhar os 4 módulos e as setas de dependência entre eles.
- **Pronto quando:**
  - [ ] POM parent/agregador com `appointments-domain`, `appointments-application`, `appointments-adapters` e `appointments-bootstrap`
  - [ ] `appointments-domain` sem nenhuma dependência de framework
  - [ ] Os mesmos testes verdes; ArchUnit mantido só para regras dentro de cada módulo
- **Conceito de entrevista:** reactor do Maven, parent vs. agregador, `dependencyManagement` vs. `dependencies`.
- **Versão mínima:** `domain` extraído para módulo próprio e compilando.

### M12 · Fronteira transacional

- **Por quê:** o caso de uso não conhece Spring, mas precisa de transação.
- **Primeiro passo (5 min):** listar as opções (adapter de entrada, decorator com `TransactionTemplate`, outra).
- **Pronto quando:**
  - [ ] Abordagem escolhida, implementada e testada
- **Conceito de entrevista:** transação em arquitetura hexagonal.
- **Versão mínima:** abordagem escolhida e explicada em 3 frases.

### M13 · Fechamento do Bloco 1

- **Por quê:** consolidar o bloco é o que transforma prática em resposta de entrevista.
- **Primeiro passo (5 min):** reler a anotação da dor (M06) e os ADRs.
- **Pronto quando:**
  - [ ] `appointments` no docker-compose e seção "Como rodar" do README preenchida
  - [ ] `docs/learning/arquitetura-hexagonal.md` no formato do README da pasta
  - [ ] Simulado de entrevista do bloco (10 perguntas), incluindo explicar cada ADR do bloco em 1 minuto
  - [ ] Item 1 do roteiro marcado no README
  - [ ] Missões do Bloco 2 detalhadas neste arquivo
- **Versão mínima:** anotação de estudo escrita.

## Próximos blocos

Detalhados em missões ao fim do bloco anterior.

- **Bloco 2, Mensageria e consistência:** Kafka, Transactional Outbox, consumidor idempotente, retry/DLQ, Saga do
  agendamento com compensação (`inventory` e `payments`)
- **Bloco 3, Session e OAuth 2.0/OIDC:** Keycloak, BFF com sessão no Redis, Authorization Code + PKCE, resource
  servers, consentimento do tutor por escopo, Client Credentials, Token Exchange, logout (`pets` e `bff`)
- **Bloco 4, Kubernetes:** kind, Helm, Argo CD, OpenTelemetry, HPA
- **Bloco 5, Serverless e AWS:** lembretes de vacina com `notifications` em Go na Lambda, SQS, Terraform (LocalStack → AWS)
