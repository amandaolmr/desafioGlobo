# Desafio Globo - API de Produtos com Spring Boot

Este projeto é uma API desenvolvida em Spring Boot que realiza operações CRUD em uma entidade "Produto".
Ele utiliza o banco de dados em memória H2 para armazenar os produtos e expõe endpoints RESTful para cada operação.

## Requisitos

- Java 17
- Spring Boot
- Maven
- Bando de dados H2
- Swagger
- Postman ou outro cliente HTTP para testes

## Funcionalidades

- CRUD para a entidade `Produto`
- Banco de dados H2 em memória
- Testes unitários com JUnit

## Estrutura do Projeto

A estrutura do projeto segue as camadas de MVC, organizando as responsabilidades da seguinte forma:

- **Controller**: Contém os endpoints RESTful para realizar as operações CRUD.
- **Service**: Implementa a lógica de negócios para manipulação dos produtos.
- **Repository**: Interface para comunicação com o banco de dados H2.

## Modelo da Entidade `Produto`

| Campo       | Tipo   | Descrição                       |
|-------------|--------|---------------------------------|
| `id`        | Long   | Identificador único do produto  |
| `nome`      | String | Nome do produto                |
| `descricao` | String | Descrição detalhada do produto |
| `preco`     | Double | Preço do produto               |

## Endpoints da API

### Criar Produto
- **POST** `/api/produtos`
- **Descrição**: Adiciona um novo produto ao banco de dados.

### Obter todos os Produto
- **GET** `/api/produtos`
- **Descrição**: Retorna a lista de todos os produtos.

### Obter Produto por ID
- **GET** `/api/produtos/{id}`
- **Descrição**: Retorna as informações de um produto específico pelo ID.

### Atualizar Produto
- **PUT** `/api/produtos/{id}`
- **Descrição**: Atualiza as informações de um produto existente.

### Excluir Produto
- **DELETE** `/api/produtos/{id}`
- **Descrição**: xclui um produto do banco de dados pelo ID.