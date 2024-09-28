
# Sistema bancário com threads

Projeto feito para o trabalho individual da matéria "Programação concorrente", consiste na criação de um sistema bancário simulado, em Java 17, utilizando o conceito de multithreading para gerenciar as atividades de clientes, funcionários, lojas e o banco. Cada cliente e funcionário é representado por uma thread, com o objetivo de realizar transações de compra, pagamento de salários e investimentos de forma simultânea.

Lojas tem funcionários, o saldo das lojas começa zerado e enquanto a loja não tem dinheiro para pagar seus funcionários, estes ficam esperando... simultaneamente há clientes fazendo compras nessas lojas, quando a loja consegue dinheiro suficiente, ela realiza o pagamento para um funcionário, ele é notificado do recebimento e em seguida deposita em uma conta salário e uma conta investimento. Todas as transações são feitas através de um banco, o banco a realiza de forma sincronizada e thread safe, ou seja, ele realiza uma transação por vez, assim garantindo a consistência do saldo de todas as contas de funcionário, clientes e loja. 



## Entendendo o projeto

Crie um projeto (em Java 17) para representar um sistema bancário utilizando threads.

O sistema deve conter pelo menos as seguintes entidades:
- Banco;
- Loja;
- Funcionário;
- Cliente;
- Conta;

E deve conter um total de:
- 1 banco;
- 2 lojas;
- 4 funcionários (2 por loja);
- 10 clientes;

Cada cliente:
- Deve ser uma thread;
- Deve possuir uma conta com um saldo inicial de R$ 2.000,00;
- Deve realizar 2 compras em cada loja (4 compras no total);
- Cada compra deve ter um valor aleatório entre R$ 200,00 e R$ 500,00.

Cada loja:
- Deve possuir uma conta, para receber os pagamentos dos clientes;
- Deve pagar os funcionários assim que possuir o valor dos seus salários (R$ 1.400,00);

Cada funcionário:
- Deve ser uma thread;
- Deve possuir duas contas, uma para receber o salário da loja e outra de investimentos;
- As contas iniciam zeradas;
- Deve investir 20% do salário na conta de investimentos logo após seu recebimento.

O banco:
- Deve intermediar as transações de forma síncrona e coordenada, garantindo a consistência dos saldos das contas.

#### Além disso, o sistema deve exibir o valor das transferências e o saldo final de cada conta, garantindo que os saldos estejam consistentes ao fim da execução, independente da ordem em que as transações foram feitas.
## Rode localmente

Clone o projeto

```bash
  git clone https://github.com/caiovrodrigues/sistema-bancario-com-threads.git
```

Vá até a pasta do projeto via terminal

```bash
  cd sistema-bancario-com-threads
```

Digite o comando

```bash
  code .
```
Sua IDE vai abrir já na pasta e então rode o projeto

Ou abra direto na sua IDE de preferência e rode o projeto

