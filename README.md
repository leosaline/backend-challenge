# Invillia recruitment challenge

[![Build Status](https://travis-ci.org/shelsonjava/invillia.svg?branch=master)](https://travis-ci.org/shelsonjava/invillia)

![Invillia Logo](https://invillia.com/public/assets/img/logo-invillia.svg)
[Invillia](https://https://www.invillia.com/) - A transformação começa aqui.

The ACME company is migrating their monolithic system to a microservice architecture and you’re responsible to build their MVP (minimum viable product)  .
https://en.wikipedia.org/wiki/Minimum_viable_product

Your challenge is:
Build an application with those features described below, if you think the requirements aren’t detailed enough please leave a comment (portuguese or english) and proceed as best as you can.

You can choose as many features you think it’s necessary for the MVP,  IT’S NOT necessary build all the features, we strongly recommend to focus on quality over quantity, you’ll be evaluated by the quality of your solution.

If you think something is really necessary but you don’t have enough time to implement please at least explain how you would implement it.

## Tasks

Your task is to develop one (or more, feel free) RESTful service(s) to:
* Create a **Store**
* Update a **Store** information
* Retrieve a **Store** by parameters
* Create an **Order** with items
* Create a **Payment** for an **Order**
* Retrieve an **Order** by parameters
* Refund **Order** or any **Order Item**

Fork this repository and submit your code with partial commits.

## Business Rules

* A **Store** is composed by name and address
* An **Order** is composed by address, confirmation date and status
* An **Order Item** is composed by description, unit price and quantity.
* A **Payment** is composed by status, credit card number and payment date
* An **Order** just should be refunded until ten days after confirmation and the payment is concluded.

## Non functional requirements

Your service(s) must be resilient, fault tolerant, responsive. You should prepare it/them to be highly scalable as possible.

The process should be closest possible to "real-time", balancing your choices in order to achieve the expected
scalability.

## Nice to have features (describe or implement):
* Asynchronous processing
* Database
* Docker
* AWS
* Security
* Swagger
* Clean Code

Observações Gerais:

Do campo Tasks foram executadas todas as tarefas, e foram criados testes para os controllers e para serviços.
A tarefa de realizar o refund especifico para o **Order Item** ficou um pouco confuso de entender, gerou algumas 
interpretações e precisava de mais dados mas fiz da forma como entendi.

Foi criado um projeto a partir do projeto modelo utilizando o fork no github.

Foi utilizado clean code de forma a usar nome significativos para os metodos e variaveis. Usei algumas constantes 
para evitar numeros ou nomes magicos. Existem outras premissas mas que não foram aplicadas.

Adicionei o Swagger no projeto para que seja feito a documentação dos serviços, para acessar utilize a url 
a seguir apos subir o projeto: http://localhost:8080/swagger-ui.html

Não utilizei parte de security, como se trata de um MVP dependendo da arquitetura a segurança poderia ficar a cargo 
de outro servidor como um single sign on(SSO). A segurança funciona atraves da definição de roles pra acesso a URL 
via servlets de filter configuraveis no spring boot. Pode se definir o acesso por recurso.

Não foi adicionado AWS, mas poderia ter feito a configuração via yaml para definir gateway de roteamento para 
chamadas a outros serviços e diretorios para cadastro do serviço para que o mesmo seja descoberto. O metodo mais
simples seria utilizar o elasticBean para configurar a aplicação seguindo o passo a passo do AWS.

No tocante ao Docker poderia ter criado um conteiner com o war do sprintboot para subir a aplicação. Esse war que é gerado
contem o minimo necessario de um webserver simples para rodar o app. No AWS é possível adicionar o conteiner criado e
então deixar que o mesmo gerencie a carga aumentando ou diminuindo a quantidade de conteineres. Isso é possível de realizar
tambem no Google cloud com o uso do kubernetes que é um gerenciador de container. (AWS não tem kubernetes nativo mas
possui mecanismo similar).

Foi incluso um Database em memoria para rodar a aplicação.

Foi utilizado um metodo de uma classe PaymentClientImp para simular o uso de processamento assíncrono. 
Na classe OrderServiceImp metodo refundOrderPurchase é utilizado a chamada assíncrona. O ganho real teria sido
perceptivel nesse cenário caso fosse necessario realizar mais de uma consulta dentro do metodo para outros servicos. 
Por exemplo se fosse necessario consultar alguma coisa de usuario ou preços e cada ação dispararia um serviço.
Ao chegar no ponto CompletableFuture.allOf(cft).join(); o sistema esperaria pelos outros processamentos para
continuar com o processamento da thread do metodo em questão.

Existem diversas abordagens para a migração de monolitico para microservices, em se tratando da questão de banco de dados
pode-se criar um owner para cada servico e assim entao separar as tabelas ou criar bancos realmente separados.
Nesse projeto não foi criado owner devido ao proposito de ser um MVP. E foram criados alguns clients para demonstrar
como seria a chamada de serviço referente a order consultando um payment, sendo nesse caso utilizado client apontando
para uma URL especifica do serviço de payment.

Em caso de evolução futura do projeto, pode-se dividi-lo em varios sub-projetos como por exemplo um para order e 
item de order, outro para payment e um terceiro para store. As entidades foram criadas sem mapeamento direto de forma
a reduzir o acoplamento e utilizam apenas um campo numerico para indicar o ID de uma entidade de outro dominio.

Por se tratar de um MVP foi utilizado o @Autowired mas dependendo do contexto pode-se utilizar a injeção por construtor
que diminui o overread, mas ficando atento para o caso de muitos parametros no construtor voce pode quebrar a
responsabilidade unica.
