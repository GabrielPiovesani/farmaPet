# FarmaPet

## Descrição do Projeto

O `FarmaPet` é um projeto de estudo focado no desenvolvimento de uma API REST para o setor farmacêutico/veterinário, com o objetivo de fornecer serviços relacionados ao cuidado de animais de estimação. Este projeto foi desenvolvido como parte de um trabalho acadêmico na [Resilia](https://www.resilia.com.br/)
.

## Funcionalidades

- **Criação de medicamento**
    - Endpoint: `/medicamentos/cadastrar-um-medicamento`
    - Método: `POST`
    - Parâmetros: JSON contendo as informações do medicamento a ser criado (nome, descrição, preço, etc.)
    - Resposta: JSON contendo os dados do medicamento criado, juntamente com o ID gerado pelo sistema.
    - Exemplo de uso:
      ```json
      POST /medicamentos/cadastrar-um-medicamento
      {
        "nome": "Medicamento X",
        "descricao": "Descrição do medicamento X",
        "preco": 10.99,
        "precoDesconto": 9.99,
        "marca": "Marca do medicamento X",
        "fabricante": "Fabricante do medicamento X"
      }
      ```
      Resposta:
      ```json
      {
        "id": 1,
        "nome": "Medicamento X",
        "descricao": "Descrição do medicamento X",
        "preco": 10.99,
        "precoDesconto": 9.99,
        "marca": "Marca do medicamento X",
        "fabricante": "Fabricante do medicamento X"
      }
      ```

- **Deleção de medicamento**
    - Endpoint: `/medicamentos/{id}`
    - Método: `DELETE`
    - Parâmetros: ID do medicamento a ser deletado
    - Resposta: Status HTTP 204 No Content em caso de sucesso

- **Busca de medicamentos por ordem de valores, do menor para o maior**
    - Endpoint: `/medicamentos/ordenar-medicamentos-pelo?ordenacao=asc`
    - Método: `GET`
    - Parâmetros: Query parameter `ordenacao` definido como "asc" para ordem ascendente
    - Resposta: JSON contendo a lista de medicamentos ordenados pelo valor em ordem ascendente
    - Exemplo de uso:
      ```
      GET /medicamentos/ordenar-medicamentos-pelo?ordenacao=asc
      ```
      Resposta:
      ```json
      [
        {
          "id": 1,
          "nome": "Medicamento A",
          "descricao": "Descrição do medicamento A",
          "preco": 9.99,
          "precoDesconto": 8.99,
          "marca": "Marca do medicamento A",
          "fabricante": "Fabricante do medicamento A"
        },
        {
          "id": 2,
          "nome": "Medicamento B",
          "descricao": "Descrição do medicamento B",
          "preco": 12.99,
          "precoDesconto": 10.99,
          "marca": "Marca do medicamento B",
          "fabricante": "Fabricante do medicamento B"
        },
        ...
      ]
      ```
      Para obter mais detalhes e testar as funcionalidades da API, você pode acessar o [Swagger UI](http://localhost:8080/swagger-ui/index.html#/) em seu navegador após iniciar a aplicação. O Swagger UI fornecerá uma interface interativa para visualizar e executar as operações disponíveis na API.

Lembre-se de substituir `http://localhost:8080` pelo URL correto da sua aplicação, caso seja diferente.

## Tecnologias Utilizadas

- Java 11
- Spring Boot 2.7.13
- Hibernate
- MySQL
- Flyway
- Springdoc OpenAPI UI
- Lombok

## Pré-requisitos

- Java 11 instalado
- MySQL instalado e configurado
- Maven instalado

## Como Executar

1. Faça o clone do repositório:

```bash
git clone https://github.com/[seu-usuario]/farmaPet.git
```
## Executando o Projeto FarmaPet

### Configuração do Banco de Dados

- Crie um banco de dados MySQL vazio.
- Abra o arquivo `src/main/resources/application.properties` no projeto FarmaPet.
- Atualize as informações de configuração do banco de dados, como URL, nome de usuário e senha, de acordo com a sua configuração local.

### Compilando o Projeto

1. No terminal ou prompt de comando, navegue até o diretório do projeto FarmaPet.

2. Execute o seguinte comando para compilar o projeto:
   ```bash
   mvn clean install
   ```
3. Para iniciar a aplicação, execute o seguinte comando:
    ```bash
    mvn spring-boot:run
    ```

### Acessando o Swagger UI

1. Abra um navegador da web.

2. Acesse o endereço [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/) para abrir o Swagger UI.

3. No Swagger UI, você poderá visualizar e testar as diferentes operações disponíveis na API do FarmaPet, incluindo a criação de medicamentos, deleção de medicamentos e busca de medicamentos por ordem de valores.

Certifique-se de substituir `[seu-usuario]` pelo seu nome de usuário correto ao clonar o repositório.

Espero que isso ajude a visualizar melhor as instruções de execução do projeto FarmaPet.

