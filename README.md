# API Parking Meter
## Bem-vindo à API Parking Meter! Aqui você encontrará informações sobre como utilizar os endpoints para criar e manipular dados.

## Sobre
```
- O Parking-meter-service é um serviço back-end para gerenciamento de estacionamentos.
- Disponibilizamos endpoints para que seja possível realizar o cadastro de Condutores, cadastro de veículos,
estacionar veículos e realizar pagamentos.
- A solução foi implantada no Google Cloud Run, o que permite uma escalabilidade horizontal e garantindo a
disponibilidade da aplicação mesmo em cenários de alto uso.
```

## Tecnologias adotadas
```
-Java 17: programação server-side
-Spring 3: criação API Restfull e serviços
-Swagger/OpenAPI: Utilizado para criar a documentação dos endpoints
-Docker: Gerar a imagem da aplicação
-PostgresSQL: Banco utilizado para persistir os dados (está dentro do Google Cloud SQL)
-DBeaver: Front-End para o SGBD
-PostMan: Utilizado nos testes dos endpoints
-Google Cloud Run: Utilizado para disponibilizar a aplicação de maneira escalável (serverless)
-Google Cloud SQL: Utilizado para disponibilizar o banco de dados que a aplicação utiliza para persistir os dados.
-Google Cloud Artifact Registry: Utilizado para armazenar as imagens Docker da aplicação que são geradas.
-Github Actions: Utilizado para realizar o CD (Continuous Deployment) do projeto parking-meter-service. 
```

## Como executar a aplicação
### Opção 1: Núvem
```
- Acessar o swagger da aplicação: https://parking-meter-fio6pykg2q-uc.a.run.app/swagger-ui/index.html
- Testar via Postman ou outra plataforma de requisições HTTP
```
### Opção 2: Local
```
- Clonar o repositório https://github.com/GrupoPosFIAP/parking-meter-service
- Entrar na pasta raiz do projeto e executar o comando "mvn spring-boot:run"
- Testar via Postman ou outra plataforma de requisições HTTP
```

## Endpoints
* [Condutor](#condutor)
* [Estacionamento](#estacionamento)
* [Pagamento](#pagamento)
* [Veículo](#veículo)

- PS: Mais informações sobre os endpoints podem ser encontradas através do swagger da aplicação, disponibilizado através da url: https://parking-meter-fio6pykg2q-uc.a.run.app/swagger-ui/index.html 

## Condutor

* Rota POST http://localhost:8080/condutor

Você pode utilizar o seguinte JSON como exemplo para cadastrar um novo condutor:

```sh
{
    "nome": "Primeiro Condutor",
    "telefone": "11999999999",
    "email": "primeiro-condutor@teste.com",
    "endereco": {
        "estado": "SP",
        "cidade": "São Paulo",
        "bairro": "Bela Vista",
        "rua": "Avenida Paulista",
        "numero": 1811
    }
}
```

* Descrição dos Campos

Campo   | Descrição
--------- | ------
nome | O nome da condutor. Deve ser uma string.
telefone  |O número do telefone do condutor. Deve ser uma string.
e-mail |O e-mail da condutor. Deve ser uma string.
endereco |O endereço do condutor é no formato JSON com no os seguintes campos:
          estado, cidade, bairro, rua e número.

####  Se você deseja copiar e editar o JSON de exemplo para criar uma nova condutor, 
sinta-se à vontade para fazer isso! Basta copiar o JSON acima, modificá-lo conforme 
necessário e enviá-lo como corpo da requisição POST para o endpoint /condutor.

  * Exemplo de Resposta

    Após enviar a requisição, você receberá a seguinte resposta:  
```sh
{
    "id": 1
    "nome": "Primeiro Condutor",
    "telefone": "11999999999",
    "email": "primeiro-condutor@teste.com",
    "endereco": {
        "estado": "SP",
        "cidade": "São Paulo",
        "bairro": "Bela Vista",
        "rua": "Avenida Paulista",
        "numero": 1811
    }
}
```

* Rota GET http://localhost:8080/condutor/1

Essa rota faz a consulta de condutor pelo id:

 * Exemplo de Resposta

    Após enviar a requisição, você receberá a seguinte resposta:  
```sh
{
    "id": 1
    "nome": "Primeiro Condutor",
    "telefone": "11999999999",
    "email": "primeiro-condutor@teste.com",
    "endereco": {
        "estado": "SP",
        "cidade": "São Paulo",
        "bairro": "Bela Vista",
        "rua": "Avenida Paulista",
        "numero": 1811
    }
}
```

* Rota PUT http://localhost:8080/condutor/1

Essa rota faz a atualização de condutor pelo id:

Você pode utilizar o seguinte JSON como exemplo para atualizar um condutor:

```sh
{
    "nome": "Primeiro Condutor Alterado",
    "telefone": "115555555555",
    "email": "primeiro-condutor@teste.com",
    "endereco": {
        "estado": "RJ",
        "cidade": "Rio de Janeiro",
        "bairro": "Ipanema",
        "rua": "Avenida Ipanema",
        "numero": 2255
    }
}
```

 * Exemplo de Resposta

    Após enviar a requisição, você receberá a seguinte resposta:  
```sh
{
    "id": 1
    "nome": "Primeiro Condutor Alterado",
    "telefone": "115555555555",
    "email": "primeiro-condutor@teste.com",
    "endereco": {
        "estado": "RJ",
        "cidade": "Rio de Janeiro",
        "bairro": "Ipanema",
        "rua": "Avenida Ipanema",
        "numero": 2255
    }
}
```

* Rota DELETE http://localhost:8080/condutor/1

Essa rota faz a exclusão de condutor pelo id:
  


## Estacionamento ( >>> Dúvida <<< )

* Rota POST : http://localhost:8080/iniciar
  
  Você pode utilizar o seguinte JSON como exemplo para cadastrar um novo estacionamento.

```sh
{
    "horarioInicio": 2023-11-03T10:50:00
}
```

* Descrição dos Campos

Campo   | Descrição
--------- | ------
horarioInicio | O horário do início do tempo de estacionamento

* Exemplo de Resposta

  Após enviar a requisição, você receberá a seguinte resposta. 
```sh
{
    "id": 1,
    "horarioInicio": 2023-11-03T10:50:00
}
```

* Rota GET : http://localhost:8080/iniciar/calcular-pagamento
  
  Essa rota retorna o cálculo do pagamento.

* Exemplo de Resposta

  Após enviar a requisição, você receberá a seguinte resposta. 
```sh
{
    "id": 1,
    "horarioInicio": 2023-11-03T10:50:00
	"horarioFim": 2023-11-03T15:25:00
	"formaDePagamento": PIX,
	"estacionamentoFixo": true,
	"tempoRealUtilizado": 4:35:00
}
```


## Pagamento 

* Rota POST : http://localhost:8080/estacionamento/{id}
  
  Essa rota é ativada quando finalizar um período de estacionamento retornando. o Id informado é o id do estacionamento finalizado.

```sh
{
    "id": 1,
    "estacionamento": {
        "veiculoId": 0,
        "horarioInicio": "datetime",
        "horarioFim": "datetime",
        "valor": "00,00",
        "formaDePagamento": "PIX|DEBITO|CREDITO"
     },
    "formaPagamento": PIX,
     "valorPagamento": 30,55
}
```

* Descrição dos Campos

Campo   | Descrição
--------- | ------
estacionamento | Informa os dados do estacionamento.
formaDePagamento | Forma de pagamento escolhida.
valorPagamento | Valor pago pelo tempo de uso do estacionamento.

* Exemplo de Resposta


## Veículo

* Rota POST : http://localhost:8080/veiculo
  
  Você pode utilizar o seguinte JSON como exemplo para cadastrar um novo veículo.

```sh
{
    "placa": "JKL9876", 
    "marca": "Volks", 
    "modelo": "Polo"
}
```

* Descrição dos Campos

Campo   | Descrição
--------- | ------
placa | Placa do veículo
marca | Marca do veiculo
modelo | Modelo do veículo

* Exemplo de Resposta

  Após enviar a requisição, você receberá a seguinte resposta. 
```sh
{
    "id": 1
    "placa": "JKL9876", 
    "marca": "Volks", 
    "modelo": "Polo"
}
```

* Rota GET : http://localhost:8080/veiculo/1

  Retorna veículo informado no id.

```sh
{
    "id": 1
    "placa": "JKL9876", 
    "marca": "Volks", 
    "modelo": "Polo"
}
```

* Rota GET : http://localhost:8080/veiculo 

  Retorna listagem dos veículos.

* Rota PUT : http://localhost:8080/veiculo/1
  
  Atualiza dados do veículo informado via id.
  Os novos dados devem ser infomado via arquivo JSON.

```sh
{
    "placa": "JKL9876", 
    "marca": "VolksWagem", 
    "modelo": "Polo Hatch"
}
```

* Exemplo de Resposta

  Após enviar a requisição, você receberá a seguinte resposta. 
```sh
{
    "id": 1
    "placa": "JKL9876", 
    "marca": "VolksWagem", 
    "modelo": "Polo Hatch"
}
```

* Rota DELETE : http://localhost:8080/veiculo/1

  Exclui veículo informado no id.


## Escalabilidade horizontal:
A escalabilidade horizontal ocorre através das ferramentas serverless da Google Cloud. A solução adotada foi a
disponibilização do serviço dockerizado através da ferramenta Google Cloud Run, que foi pensada inicialmente para 
trabalhar simultâneamente com até 10 instâncias da aplicação, sendo esse um valor totalmente modificável para o número 
mínimo e máximo de instâncias desejadas. Vale ressaltar que adicionamos toda a parte de Continuous Deployment dentro
do GitHub Actions, permitindo ainda que a quantidade de instâncias sejam configuradas pelo próprio script da pipeline.
Como o Google Cloud Run é uma ferramenta serverless, o gerenciamento das instâncias fica por conta da infraestrutura da Google Cloud, 
permitindo com que seja possível focar apenas no código, e "esquecer" de questões de infraestrutura do projeto.

Abaixo podemos ver um pouco do poder da escalabilidade horizontal:

Aplicação sendo executada em 1 instância em momentos que chegam poucas requisições:
<img src="/src/main/resources/static/1instancia.png">

Aplicação sendo executada em 10 instâncias em momentos que chegam muitas requisições:
<img src="/src/main/resources/static/10instancias.png">

Teste de stress realizado, chegando a 1300 requisições por segundo, todas com sucesso:
<img src="/src/main/resources/static/1300requests.png">

## Dificuldades
```
* O grupo não alinhou a finalização dos módulos da pós-graduação para liberar tempo adequado para o
desenvolvimento do projeto.
* Horários de reunião do grupo divergiu entre alguns participantes.
* Dificuldade no entendimento do enunciado do projeto. Exemplo: O relacionamento de algumas entidades.
* Estruturar e disponibilizar o projeto em uma solução serverless e escalável
```

## Aprendizados
```
* A utilização de IDE (Integrated Developer Envoirement – Ambiente de Desenvolvimento Integrado) idênticas
por parte dos membros da equipe, evita problemas de incompatibilidade.
* Realizar a criação de uma pipeline de CD (Continuous Deployment) através do GitHub Actions
```
