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
```
- Clonar o repositório https://github.com/GrupoPosFIAP/parking-meter-service
- Entrar na pasta raiz do projeto e executar o comando "mvn spring-boot:run"
- Testar via Postman
```

## Escalabilidade horizontal:
A escalabilidade horizontal ocorre através das ferramentas serverless da Google Cloud. A solução adotada foi a
disponibilização do serviço dockerizado através da ferramenta Google Cloud Run, que foi pensada inicialmente para 
trabalhar simultâneamente com até 10 instâncias da aplicação.
Como o Google Cloud Run é uma ferramenta serverless, o gerenciamento das instâncias fica por conta da infraestrutura da Google Cloud, 
permitindo com que seja possível focar apenas no código, e "esquecer" de questões de infraestrutura do projeto.


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
