# Microsserviço: Warehouse

Este microsserviço é o coração do gerenciamento de inventário do nosso sistema de e-commerce. Ele é a fonte da verdade para todas as informações relacionadas a produtos e seus respectivos estoques.

## Arquitetura
Como parte de uma arquitetura de microserviços, o `warehouse` opera de forma independente para gerenciar seus dados. Ele se comunica com outros serviços de forma assíncrona através do RabbitMQ para notificar sobre eventos importantes, como mudanças no status do estoque.

## Principais Responsabilidades
* Cadastrar e gerenciar produtos.
* Controlar a quantidade, preços e o status do estoque (Disponível, Indisponível, Em Conferência, etc.).
* Publicar eventos no RabbitMQ sempre que o status de um estoque é alterado, permitindo que outros serviços (como o `storefront`) reajam a essas mudanças.

## Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3.x**
* **Spring Data JPA:** Para persistência de dados.
* **H2 Database:** Configurado em modo arquivo para persistência de dados durante o desenvolvimento.
* **Spring AMQP:** Para integração com RabbitMQ.
* **Lombok & MapStruct:** Para reduzir código boilerplate.
* **Docker & Docker Compose:** Para containerização e orquestração do ambiente de desenvolvimento.
* **OpenAPI (Swagger):** Para documentação da API.

## Pré-requisitos
* JDK 21 ou superior.
* Docker e Docker Compose instalados.

## Como Executar

Existem duas formas principais de executar o projeto, dependendo do seu objetivo.

### 1. Ambiente Completo com Docker Compose
Esta abordagem sobe toda a infraestrutura (RabbitMQ) e todos os microsserviços definidos no `docker-compose.yml`.

1.  Na raiz do projeto, execute o comando:
    ```bash
    docker-compose up --build -d
    ```
2.  Aguarde os containers subirem. O serviço `warehouse` estará disponível em `http://localhost:8080/warehouse`.

### 2. Ambiente Híbrido para Desenvolvimento Local
Ideal para quando você está desenvolvendo ativamente este microsserviço e quer usar o live-reload do Spring DevTools.

1.  **Suba as dependências** (como o RabbitMQ) com o Docker Compose:
    ```bash
    docker-compose up -d rabbitmq
    ```
2.  **Configure o `application-dev.yml`** para apontar para o RabbitMQ na sua máquina local:
    ```yaml
    spring:
      rabbitmq:
        host: localhost
    ```
3.  **Execute a aplicação** diretamente pela sua IDE, rodando a classe principal `WarehouseApplication.java`.

## Endpoints da API
A documentação completa e interativa da API pode ser acessada via Swagger UI no seguinte endereço (após iniciar a aplicação):

* **[http://localhost:8080/warehouse/swagger-ui.html](http://localhost:8080/warehouse/swagger-ui.html)**

| Verbo  | Endpoint                   | Descrição                                  |
| :----- | :------------------------- | :----------------------------------------- |
| `POST` | `/stocks`                  | Cria um novo registro de estoque para um produto. |
| `PUT`  | `/stocks/{id}/active`      | Libera um estoque, tornando-o disponível.  |
| `DELETE` | `/stocks/{id}/active`      | Inativa um estoque.                        |
| ...    | ...                        | *(Adicionar outros endpoints de produto, etc.)* |

## Comunicação Assíncrona (Eventos Publicados)

Este serviço atua como **Produtor** de eventos.

* **Evento:** Mudança no Status do Estoque.
* **Exchange:** `product.change.availability.exchange` (configurável no `application.yml`)
* **Routing Key:** `product.change.availability.routing.key` (configurável no `application.yml`)
* **Payload (Mensagem):** `StockStatusMessage { UUID productId, StockStatus status }`
