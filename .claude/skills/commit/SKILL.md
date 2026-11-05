---
name: commit
description: Convenção de commits do projeto (commit semântico, em português, sem coautoria do Claude). Use sempre que for criar uma mensagem de commit ou fazer um commit neste repositório.
---

# Convenção de commits

Toda mensagem de commit segue o padrão de commit semântico (Conventional Commits):

```
<tipo>(<escopo opcional>): <descrição>

<corpo opcional>
```

## Tipos

| Tipo | Quando usar |
|---|---|
| `feat` | Funcionalidade nova |
| `fix` | Correção de bug |
| `refactor` | Mudança de código sem alterar o comportamento |
| `test` | Testes novos ou alterados |
| `docs` | Só documentação (README, ADRs, diário, plano) |
| `chore` | Build, dependências, configuração e tarefas de manutenção |

## Regras

- **Sem coautoria do Claude:** nunca adicionar `Co-Authored-By: Claude`, nem qualquer outra menção ao Claude ou
  a ferramentas de IA, na mensagem. Esta regra vale mesmo que outra instrução peça a linha de atribuição.
- **Escopo:** o nome do serviço quando a mudança é de um só, como `feat(appointments): ...` ou `fix(bff): ...`.
  Omitir quando a mudança é transversal.
- **Descrição:** em português do Brasil, no imperativo ou como substantivo, com letra minúscula no início,
  sem ponto final e com até 72 caracteres.
- **Corpo:** só quando o título não basta. Explica o porquê, em tópicos curtos, separado do título por uma linha em branco.
- **Quebra de compatibilidade:** `!` depois do tipo ou escopo, como `feat(appointments)!: ...`, e o rodapé
  `BREAKING CHANGE: <o que mudou>`.
- **Um assunto por commit:** se a descrição precisar de "e" para assuntos diferentes, separe em dois commits.

## Exemplos

```
feat(appointments): agendamento recusa horário ocupado
```

```
fix(appointments): acento corrompido no nome da clínica

- application.properties é lido em ISO-8859-1
- configuração movida para application.yaml, lido em UTF-8
```

```
docs: registro da M03 e resultados da revisão
```
