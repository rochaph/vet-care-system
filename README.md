# vet-care-system

Laboratório de arquitetura distribuída: uma plataforma veterinária simplificada (agendamento de atendimentos e
carteira de saúde do pet) construída para praticar, de ponta a ponta,
**microsserviços, Arquitetura Hexagonal, mensageria, Session, OAuth 2.0/OIDC, Kubernetes, serverless e AWS**.

O domínio é propositalmente enxuto. O foco está em como os serviços se comunicam, falham, se recuperam,
autenticam usuários e são implantados.

## Domínio

- O **tutor** cadastra seus pets e mantém a carteira de saúde: vacinas, peso, alergias.
- O tutor **agenda um atendimento** (ex.: vacinação) com um **veterinário**, pagando um sinal.
- O tutor **autoriza a clínica** a ler a carteira de saúde do pet, com escopo limitado e revogável.
- A clínica controla o **estoque de vacinas e medicamentos** por lote e validade.
- O sistema envia **lembretes** de vacina, vermífugo e retorno.

Fora do escopo: internação, prontuário clínico completo, exames, telemedicina, nota fiscal e multi-clínica.

## Arquitetura

```
                 Browser
                    │
             [Ingress NGINX]
                    │
        ┌───────────▼───────────┐       ┌───────────┐
        │   BFF / Gateway       │◄─────►│ Keycloak  │  OAuth 2.0 / OIDC
        │   sessão em Redis     │       └───────────┘
        └──────┬─────────┬──────┘
          REST │         │ REST (access token)
        ┌──────▼──┐  ┌───▼──────────┐
        │  pets   │  │ appointments │──► Postgres + Outbox
        └─────────┘  └───┬──────────┘
                         │ eventos (Kafka)
              ┌──────────┼───────────┐
        ┌─────▼─────┐ ┌──▼────────┐  │   Saga: horário → dose em estoque → sinal
        │ inventory │ │ payments  │  │
        └───────────┘ └───────────┘  │
                                     ▼  appointment.confirmed, vaccine.due
                           SQS (LocalStack → AWS)
                                     │
                          ┌──────────▼──────────┐
                          │  notifications (Go) │  Lambda OU pod k8s
                          └─────────────────────┘
```

| Serviço | Responsabilidade |
|---|---|
| `bff` | Sessão (cookie + Redis) e login OAuth 2.0/OIDC; único ponto de entrada do browser |
| `pets` | Tutores, pets e carteira de saúde; consentimento de acesso para clínicas |
| `appointments` | Agenda dos veterinários e ciclo de vida do atendimento; orquestra a Saga |
| `inventory` | Vacinas e medicamentos por lote e validade; reserva de doses |
| `payments` | Sinal do agendamento e reembolso |
| `notifications` | Lembretes de vacina, vermífugo e retorno (Go) |

## Stack

| Camada | Tecnologia |
|---|---|
| Serviços principais | Java 21, Spring Boot 4 |
| Serviço serverless | Go (AWS Lambda) |
| Identidade | Keycloak (local), Amazon Cognito (AWS) |
| Sessão | Spring Session + Redis |
| Mensageria | Kafka, Amazon SQS |
| Persistência | PostgreSQL, DynamoDB |
| Orquestração | Kubernetes (kind), Helm, Argo CD |
| Observabilidade | OpenTelemetry, Prometheus, Grafana, Jaeger |
| Infraestrutura como código | Terraform (LocalStack e AWS) |
| CI | GitHub Actions |

## Onde cada conceito aparece

| Conceito | Onde |
|---|---|
| Microsserviços | 6 serviços, banco por serviço, deploy independente |
| Arquitetura Hexagonal | todos os serviços; `appointments` é a referência; `notifications` troca adaptadores (Lambda ↔ HTTP) |
| Mensageria | Outbox, consumidor idempotente, retry + DLQ, Saga do agendamento com compensação |
| Session | BFF com cookie de sessão e store no Redis |
| OAuth 2.0 / OIDC | Authorization Code + PKCE, Client Credentials, Token Exchange, validação de JWT, consentimento do tutor por escopo |
| Kubernetes | kind, Helm, Ingress, HPA, réplicas |
| Serverless / AWS | Lambda de lembretes (agendada e via SQS) + DynamoDB, via Terraform |

## Estrutura

```
services/        microsserviços (um diretório por serviço)
infra/           docker-compose, Kubernetes (Helm/Argo CD) e Terraform
docs/adr/        decisões de arquitetura (Architecture Decision Records)
docs/learning/   anotações de estudo de cada conceito
```

## Roteiro

- [ ] **1. Base**: Spring Boot em camadas, refatorado para Arquitetura Hexagonal e multi-módulo; Testcontainers, docker-compose
- [ ] **2. Mensageria e consistência**: Kafka, Outbox, idempotência, retry/DLQ, Saga
- [ ] **3. Session e OAuth 2.0/OIDC**: Keycloak, BFF, Redis, resource servers, Client Credentials, Token Exchange, logout
- [ ] **4. Kubernetes**: kind, Helm, Argo CD, OpenTelemetry, HPA
- [ ] **5. Serverless e AWS**: `notifications` em Go na Lambda, SQS, Terraform (LocalStack → AWS)

## Como rodar

_Será preenchido à medida que os serviços forem implementados._
