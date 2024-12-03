# Weather API

Este é um projeto de API para gerenciar cidades, regiões e condições climáticas, integrando-se à API do OpenWeather. Ele permite consultar, cadastrar, atualizar e excluir informações sobre regiões, cidades e condições climáticas.

## Executando o Projeto (???)

1. Renomeie o arquivo `.env.template` para `.env` e preencha as variáveis de ambiente necessárias.
2. Execute o comando `docker-compose up -d` na raiz do projeto para iniciar a aplicação.

---

## Tecnologias Utilizadas

- **Spring Boot**
- **Spring Data JPA**
- **H2 Database**
- **Jakarta Validation**
- **Docker**

---

## Recursos

1. **Região**: Gerenciamento de regiões cadastradas.
2. **Cidade**: Cadastro e gerenciamento de cidades associadas a regiões.
3. **Clima**: Consulta de condições climáticas por cidade.

---

## Rotas


### **Região**

#### `GET /regiao`

Lista todas as regiões cadastradas.

**Resposta de Sucesso:**
```json
[
  {
    "id": "1",
    "nome": "Norte"
  },
  {
    "id": "2",
    "nome": "Sul"
  }
]
```

Respostas de erro:
* `204` - Nenhuma região encontrada.

#### `GET /regiao/{id}`

Retorna os detalhes de uma região específica pelo `id`.

**Resposta de Sucesso:**
```json
{
  "id": "1",
  "nome": "Norte"
}
```

Respostas de Erro:
* `404` - Região não encontrada.
* `400` - id inválido.

#### `POST /regiao`

Cadastra uma nova região.

Corpo da Requisição:
```json
{
  "nome": "Sudeste"
}
```

Corpo da Resposta:
````json
{
  "id": "3",
  "nome": "Sudeste"
}
````

Respostas de Erro:
  * `400` - Dados inválidos.
  * `409` - Região já cadastrada.

#### `PUT /regiao/{id}`

Atualiza as informações de uma região específica pelo `id`.

Corpo da Requisição:
````json
{
  "nome": "Sudeste"
}
````

Corpo da Resposta:
````json
{
  "id": "3",
  "nome": "Sudeste"
}
````

Respostas de erro:
* `400` - `id` inválido;
* `404` - região não encontrada;

#### `DELETE /regiao/{id}`

Deleta uma região específica pelo `id`.

Corpo da Resposta:
````json
[
  {
    "id": "1",
    "nome": "Norte"
  }
]
````

Respostas de erro:
* `400` - `id` inválido;
* `404` - região não encontrada;


### **Cidade**

#### `GET /cidade`

Lista todas as cidades cadastradas.

**Resposta de Sucesso:**
```json
[
  {
    "id": "1",
    "nome": "Criciuma",
    "pais": "BR",
    "latitude": 0,
    "longitude": 0,
    "regiaoId": "1"
  }
]
```

Respostas de erro:
* `204` - Nenhuma cidade encontrada.

#### `GET /cidade/{id}`

Retorna os detalhes de uma cidade específica pelo `id`.

**Resposta de Sucesso:**
```json
{
  "id": "1",
  "nome": "Criciuma",
  "pais": "BR",
  "latitude": -28.6775,
  "longitude": -49.3697,
  "regiaoId": "1"
}
```

Respostas de Erro:
* `404` - Cidade não encontrada.
* `400` - id inválido.

#### `POST /regiao`

Cadastra uma nova cidade.

Corpo da Requisição:
```json
{
  "nome": "Icara",
  "pais": "BR",
  "regiaoId": "1"
}
```

Corpo da Resposta:
````json
{
  "id": "2",
  "nome": "Icara",
  "pais": "BR",
  "latitude": -28.7133,
  "longitude": -49.3,
  "regiaoId": "1"
}
````

Respostas de Erro:
  * `400` - Dados inválidos.
  * `409` - Cidade já cadastrada.

#### `PUT /cidade/{id}`

Atualiza as informações de uma cidade específica pelo `id`.

Corpo da Requisição:
````json
{
  "nome": "Içara"
}
````

Corpo da Resposta:
````json
{
  "id": "2",
  "nome": "Içara",
  "pais": "BR",
  "latitude": -28.7133,
  "longitude": -49.3,
  "regiaoId": "1"
}
````

Respostas de erro:
* `400` - `id` inválido;
* `404` - cidade não encontrada;

#### `DELETE /cidade/{id}`

Deleta uma cidade específica pelo `id`.

Corpo da Resposta:
````json
[
  {
    "id": "1",
    "nome": "Criciuma",
    "pais": "BR",
    "latitude": -28.6775,
    "longitude": -49.3697,
    "regiaoId": "1"
  }
]
````

Respostas de erro:
* `400` - `id` inválido;
* `404` - cidade não encontrada;


### **Clima**

#### `GET /clima`

Lista todas os climas cadastrados
**Resposta de Sucesso:**
```json
[
  {
    "id": "1",
    "temperatura": 18.57,
    "humidade": 88,
    "dateTime": "2024-12-02T23:33:04.180511",
    "cidade": {
      "id": "1",
      "nome": "Criciuma",
      "pais": "BR",
      "latitude": -28.6775,
      "longitude": -49.3697,
      "regiao": {
        "id": "1",
        "nome": "Sul"
      }
    }
  }
]
```

Respostas de erro:
* `204` - Nenhum clima encontrado.

#### `GET /clima/{id}`

Retorna os detalhes de um clima específico pelo `id`.

**Resposta de Sucesso:**
```json
{
  "id": "1",
  "temperatura": 18.57,
  "humidade": 88,
  "dateTime": "2024-12-02T23:33:04.180511",
  "cidade": {
    "id": "1",
    "nome": "Criciuma",
    "pais": "BR",
    "latitude": -28.6775,
    "longitude": -49.3697,
    "regiao": {
      "id": "1",
      "nome": "Sul"
    }
  }
}
```

Respostas de Erro:
* `404` - Clima não encontrado.
* `400` - id inválido.

#### `GET /clima/cidade/{id}`

Retorna os detalhes de um clima específico pelo `id` da cidade.

**Resposta de Sucesso:**
```json
{
  "id": "1",
  "temperatura": 18.57,
  "humidade": 88,
  "dateTime": "2024-12-02T23:33:04.180511",
  "cidade": {
    "id": "1",
    "nome": "Criciuma",
    "pais": "BR",
    "latitude": -28.6775,
    "longitude": -49.3697,
    "regiao": {
      "id": "1",
      "nome": "Sul"
    }
  }
}
```

Respostas de Erro:
* `404` - Clima não encontrado.
* `404` - Cidade não encontrada.
* `400` - id inválido.
