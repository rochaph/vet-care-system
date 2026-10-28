# Decisões de arquitetura

Cada ADR responde "por que assim, e não de outro jeito?". Esta tabela é a cola para entrevista: se você consegue
explicar a linha de cada um em voz alta, consegue defender a decisão.

| ADR | Decisão | Alternativa descartada | Custo aceito |
|---|---|---|---|
| [0001](0001-registrar-decisoes-de-arquitetura.md) | Registrar decisões relevantes como ADRs curtos | Decisões só no código e na memória | Manter os arquivos atualizados |
| [0002](0002-stack-java-spring-e-go.md) | Java/Spring nos serviços; Go no `notifications` | Tudo em Java (cold start na Lambda) ou tudo em Go (ecossistema OAuth menor) | Duas toolchains no CI |
| [0003](0003-maven-como-ferramenta-de-build.md) | Maven com Maven Wrapper | Gradle (mais rápido, mas o ganho é marginal em builds pequenos) | Build mais lento, XML menos flexível |
| [0004](0004-multi-modulo-maven.md) | Multi-módulo `domain` / `application` / `adapters` / `bootstrap` | Módulo único + ArchUnit (só detecta a violação nos testes) | Mais POMs e wiring explícito com `@Bean` |

## Como os ADRs entram no estudo

- **Quem escreve:** o Claude, no formato curto do [template](template.md), na mesma sessão em que a decisão aparece.
- **Na sessão:** o ADR é mostrado inteiro (é curto) e você concorda ou discorda com um clique. Discordou, ele muda.
- **Na revisão:** o trade-off de cada ADR entra no banco de revisão como pergunta de múltipla escolha.
- **No fechamento do bloco:** explicar cada ADR do bloco em 1 minuto faz parte do simulado de entrevista.
