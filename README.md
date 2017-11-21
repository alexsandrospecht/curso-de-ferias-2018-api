# Curso de Férias de Desenvolvimento API Rest
------------

### Inicialização do Projeto
```bash
```

### Introdução
* **O que é uma API?**
* **O que é REST?**

### Protocolo HTTP
#### Verbos [POST, GET, PUT, PATCH, DELETE]

| Verbo         | CRUD                  | Coleção '/usuarios' | Item Específico '/usuarios/{id}'
| ------------- |:---------------------:| -----:| -----:|
| POST          | Criar                 | 201 (Created), 'Location' header com a URI para o recurso criado '/usuarios/{id}' | 404 (Not Found), 409 (Conflict) caso recurso já exista
| GET           | Ler                   | 200 (Ok), lista todos dados da coleção | 200 (Ok) Usuário encontrado, 404 (Not Found) usuário não encontrado
| PUT           | Atualizar/Substituir  | 405 (Method not allowed) a não ser que seja possível atualizar toda a coleção | 200 (ok) ou 204 (No content) em caso de sucesso, 404 caso a entidade a ser atualizada nao exista (ID invalido)
| PATCH         | Atualizar/Modificar   | 405 (Method not allowed) a não ser que seja possível atualizar toda a coleção | 200 (ok) ou 204 (No content) em caso de sucesso, 404 caso a entidade a ser atualizada nao exista (ID invalido)
| DELETE        | Deletar               | 405 (Method not allowed) a não ser que seja possível deletar toda a coleção   | 200 (ok) em caso de sucesso, 404 caso a entidade a ser atualizada nao exista (ID invalido)

#### Headers

#### Códigos de Retorno
##### 1xx Informativo
* 100 Continue 
* 101 Switching Protocols
##### 2xx Sucesso 
* 200 Success
* 201 Created 
* 204 No Content
##### 3xx Redirecionamento
* 301 Moved Permanently
* 302 Found
* 304 Not Modified
##### 4xx Client side error
* 400 Bad Request
* 401 Unauthorized
* 403 Forbidden
* 404 Not Found
* 405 Method Not Allowed
* 409 Conflict
* 415 Unsupported Media Type
##### 5xx Server side error
* 500 Internal Server Error
* 502 Bad Gateway
* 503 Service Unavailable
* 504

### SpringBoot Starter
### Testes Unitários
### Docker
