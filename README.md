 # Sistema Integrado PetCare

Implementacao em Java puro, sem frameworks ou dependencias externas, baseada no
diagrama UML e nas regras de negocio do trabalho.

## Estrutura

- `src/petcare/Main.java`: demonstracao dos modulos e das regras.
- `src/petcare/model`: usuarios, animais, prontuario, agenda, estoque, financeiro e auditoria.

## Requisitos

- JDK 17 ou superior.

## Compilar e executar

```bash
rm -rf out
mkdir out
javac -d out $(find src -name '*.java' -print)
java -cp out petcare.Main
```

## Pilares de POO aplicados

- **Heranca:** `Administrador`, `Veterinario`, `Recepcionista` e `Tutor` estendem `Usuario`; registros clinicos especializados estendem `RegistroClinico`.
- **Encapsulamento:** atributos privados, validacoes nos construtores e setters e colecoes expostas como somente leitura.
- **Composicao:** `Animal` cria seu `HistoricoClinico`; `Estoque`, `Agendamento` e `Fatura` mantem seus objetos colaboradores.
- **Delegacao:** `Agendamento`, `Estoque` e `Fatura` delegam comunicacoes ao `Notificador`.

## Regras demonstradas

O codigo implementa horario comercial, disponibilidade do veterinario, duracao minima de cirurgia,
bloqueio de historico finalizado, alerta de estoque minimo, rastreabilidade de controlados,
reforco de vacinas, confirmacao de pagamento e log de auditoria.

