# Documentação da API com Autorização e Autenticação para um Sistema de E-commerce

## Descrição do Microsserviço

O microsserviço é responsável por gerenciar a autenticação e autorização de usuários, bem como a manipulação de produtos em um banco de dados MongoDB. Ele utiliza JSON Web Tokens (JWT) para autenticação e autorização de usuários. Os principais recursos oferecidos incluem o registro de novos usuários, login, extração de papéis de usuários e operações CRUD em produtos.

# Diagrama de Fluxo do Microsserviço
```mermaid
graph TD;
    subgraph User_Operations
        A1[Registra Usuário] --> B1[Autentica Usuário]
        B1 --> C1[Extrai Papel de Usuário]
        style A1 fill:#e74c3c,stroke:#333,stroke-width:2px,color:#fff;
        style B1 fill:#2ecc71,stroke:#333,stroke-width:2px,color:#fff;
        style C1 fill:#f1c40f,stroke:#333,stroke-width:2px,color:#fff;
    end

    subgraph Admin_Operations
        D1[Exclui Usuário]
        style D1 fill:#e67e22,stroke:#333,stroke-width:2px,color:#fff;
    end

    subgraph Manager_Operations
        E1[Exclui Produto]
        style E1 fill:#3498db,stroke:#333,stroke-width:2px,color:#fff;
    end

    subgraph Seller_Operations
        F1[Cria Produto]
        style F1 fill:#9b59b6,stroke:#333,stroke-width:2px,color:#fff;
    end

    subgraph Customer_Operations
        G1[Visualiza Produto]
        style G1 fill:#2ecc71,stroke:#333,stroke-width:2px,color:#fff;
    end

    A[API de Autenticação] -->|Registra/Login| User_Operations
    style A fill:#f39c12,stroke:#333,stroke-width:2px,color:#fff;
    User_Operations -->|Token JWT| H[JWT Service]
    style H fill:#3498db,stroke:#333,stroke-width:2px,color:#fff;
    H -->|Autorização| Admin_Operations
    H -->|Autorização| Manager_Operations
    H -->|Autorização| Seller_Operations
    H -->|Autorização| Customer_Operations
    User_Operations -->|Acesso a Dados| I[MongoDB]
    style I fill:#e74c3c,stroke:#333,stroke-width:2px,color:#fff;
    Admin_Operations -->|Acesso a Dados| I
    Manager_Operations -->|Acesso a Dados| I
    Seller_Operations -->|Acesso a Dados| I
    Customer_Operations -->|Acesso a Dados| I
```

## Informações de Contato

- **Nome:** Gustavo Rodrigues
- **Telefone:** 31982759251
- **Email:** guhrodriguesads@gmail.com

## Roteiro de Plantão

- **Plantão Técnico:** Gustavo Rodrigues
- **Horário:** Segunda a Sexta, das 09:00 às 18:00
- **Contato de Emergência:** 31982759251 (WhatsApp disponível)

## Links para Informações Importantes

- [Repositório GitHub]([https://github.com/username/repository](https://github.com/GuuhRodrigues/Av2ArquiteturaAppWeb))
- [Documentação da API](https://docs.api.com)
- [Ferramentas de Monitoramento] - Quando tiver irei adicionar
- [Ferramentas de Log] - Quando tiver irei adicionar

## Guia de Bordo e Desenvolvimento

### Requisitos

- **Java 11**
- **Spring Boot 2.7.5**
- **MongoDB 4.x**
- **Maven 3.6+**

### Configuração do Ambiente

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/username/repository.git
   cd repository
   ```
Configure o MongoDB:

Certifique-se de que o MongoDB está em execução e configurado corretamente conforme as propriedades do application.properties.

Instale as dependências:

```bash
mvn clean install
Execute a aplicação:
```
```bash
mvn spring-boot:run
```

Configuração do application.properties
```bash
spring.application.name=Av2
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=bd-user
server.port=8080
jwt.secret=secret_key
```
Informações sobre o(s) Fluxo(s) de Solicitação
Endpoints

## Registro de Usuário

![Register](https://github.com/GuuhRodrigues/Av2ArquiteturaAppWeb/blob/master/img/Register.PNG)

URL: /register

Método: POST

Descrição: Registra um novo usuário.

Corpo da Solicitação:

json
```bash
{
  "username": "string",
  "password": "string",
  "email": "string",
  "role": "string"
}
```

Resposta:
```bash
{
  "message": "User registered successfully"
}
```

## Login

![Register](https://github.com/GuuhRodrigues/Av2ArquiteturaAppWeb/blob/master/img/Login.PNG)

URL: /login

Método: POST

Descrição: Autentica um usuário e retorna um token JWT.

Corpo da Solicitação:

```bash
{
  "username": "string",
  "password": "string"
}
```
Resposta:

```bash
{
  "token": "string"
}
```

## Excluir Usuário

![DeleteUser](https://github.com/GuuhRodrigues/Av2ArquiteturaAppWeb/blob/master/img/DeleteUser.PNG)

URL: /admin/users/{id}

Método: DELETE

Descrição: Exclui um usuário por ID. Requer papel ADMIN.

Resposta:

```bash
{
  "message": "Usuário deletado com sucesso"
}
```

## Excluir Produto

![DeleteProduct](https://github.com/GuuhRodrigues/Av2ArquiteturaAppWeb/blob/master/img/DeleteProduct.PNG)

URL: /manager/products/{id}

Método: DELETE

Descrição: Exclui um produto por ID. Requer papel MANAGER.

Resposta:

```bash
{
  "message": "Produto deletado com sucesso"
}
```

## Criar Produto

![CreateProduct](https://github.com/GuuhRodrigues/Av2ArquiteturaAppWeb/blob/master/img/CreateProduct.PNG)

URL: /seller/products

Método: POST

Descrição: Cria um novo produto. Requer papel SELLER.

Corpo da Solicitação:

```bash
{
  "descricao": "string",
  "valor": "string"
}
```

Resposta:

```bash
{
  "message": "Produto criado com sucesso"
}
```

## Visualizar Produto

![Visualizar](https://github.com/GuuhRodrigues/Av2ArquiteturaAppWeb/blob/master/img/VisualizeProduct.PNG)

URL: /customer/products/{id}

Método: GET

Descrição: Retorna detalhes de um produto por ID. Requer papel CUSTOMER.

Resposta:

```bash
{
  "id": "string",
  "descricao": "string",
  "valor": "string"
}
```

## Extrair Papel

![Extrair Role](https://github.com/GuuhRodrigues/Av2ArquiteturaAppWeb/blob/master/img/ExtractRole.PNG)

URL: /role/{token}

Método: GET

Descrição: Extrai o papel de um usuário a partir de um token JWT.

Resposta:

```bash
{
  "role": "string"
}
```

Dependências do Serviço
Spring Boot Starter Web: Para criação de aplicações web.
Spring Boot Starter Data MongoDB: Para integração com MongoDB.
Spring Boot Starter Security: Para segurança e autenticação.
JWT: Para autenticação baseada em token.
Spring Boot Starter Test: Para testes unitários e de integração.
Respostas para Perguntas Mais Frequentes
Como configurar o banco de dados?

O banco de dados MongoDB deve estar em execução na localhost na porta 27017. A base de dados utilizada é bd-user.

Como gerar um token JWT?

Um token JWT é gerado quando um usuário faz login com sucesso no endpoint /login.

Como proteger os endpoints?

Os endpoints são protegidos por papéis definidos no token JWT. Use o cabeçalho Authorization: Bearer <token> para autenticar as requisições.

Como adicionar novos usuários?

Use o endpoint /register para registrar novos usuários. Um usuário com papel ADMIN pode então atribuir papéis apropriados aos usuários.

Quais são os papéis disponíveis e suas permissões?

ADMIN: Pode excluir usuários.
MANAGER: Pode excluir produtos.
SELLER: Pode criar produtos.
CUSTOMER: Pode visualizar produtos.
Como alterar a senha de um usuário?

A senha de um usuário pode ser alterada implementando um endpoint adicional que permita ao usuário alterar sua própria senha após autenticação.

```bash
Essa documentação deve ser suficiente para fornecer uma visão completa do seu microsserviço, suas funcionalidades, e como configurá-lo e utilizá-lo. Basta copiar e colar o conteúdo no `README.md` do seu repositório GitHub.
```
