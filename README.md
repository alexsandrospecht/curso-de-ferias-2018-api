# Curso de Férias de Desenvolvimento API Rest
------------
https://frozen-savannah-80661.herokuapp.com/


### Inicialização do Projeto
```bash
```

### Introdução
* **O que é uma API?**
  API é um conjunto de rotinas e padrões de programação para acesso a um aplicativo de software ou plataforma baseado na Web. A sigla API refere-se ao termo em inglês "Application Programming Interface" que significa em tradução para o português "Interface de Programação de Aplicativos".
  
  Através das APIs, os aplicativos podem se comunicar uns com os outros sem conhecimento ou intervenção dos usuários. Elas funcionam através da comunicação de diversos códigos, definindo comportamentos específicos de determinado objeto em uma interface. A API liga as diversas funções em um site de maneira que possam ser utilizadas em outras aplicações.
  
* **O que é REST?**
  O termo REST foi definido por Roy T. Fielding em sua tese de PhD. Roy foi um dos principais desenvolvedores de muitos dos protocolos Web essenciais, incluindo HTTP e URIs, e ele formalizou várias das idéias por trás deles nesse documento. Nesta dissertação, Roy primeiro define uma metodologia de falar sobre estilos arquiteturais — alto nível, padrões de abstração que expressam as principais ídeias por trás de uma abordagem arquitetural. Cada estilo arquitetural com um conjunto de regras que o define. Exemplos de estilos arquiteturais incluem “o estilo nulo” (que não possue regras), pipe e filter, cliente/servidor, objetos distrubuídos e REST.

  Se tudo isso soa um pouco abstrato pra você. Você está certo - REST em si é um estilo de alto nível que poderá ser implementado utilizando muitas tecnologias diferentes, e instanciado utilizando diferentes valores para suas propriedades abstratas.

  Uma roupagem do estilo REST é o HTTP, ou de forma um pouco mais abstrata: a arquitetura da Web em si. Pensando dessa forma, o HTTP "instancia" a interface uniforme do REST com uma interface especial, consistindo nos verbos HTTP.

  Comumente, REST é conhecido como um conjunto de princípios que definem como Web Standards, como HTTP e URIs, devem ser usados. A promessa é que se você aderir a princípios REST enquanto estiver desenhando sua aplicação, você terá um sistema que explora a arquitetura da Web em seu benefício.

**Os cinco princípios fundamentais são os seguintes:**
  
  **1. Dê a todas as coisas um Identificador**
  
  Use URIs para identificar tudo o que precisar ser identificado, especifique todos os recursos de "alto nível" que seu aplicativo oferece, se eles representam itens individuais, conjuntos de itens, objetos virtuais e físicos, ou resultados de computação.
  
  **2. Vincule as coisas**
  
  Use liks para referênciar coisas que possam ser identificadas (recursos) sempre que for possível. Hiperlinks são o que fazem a Web ser a Web.
      
  **3. Utilize métodos padronizados**
  
  Para que clientes possam interagir com seus recursos, eles devem implementar o protocolo de aplicação padrão (HTTP) corretamente, isto é, utilizar os métodos padrão: GET, PUT, POST e DELETE.
  
  **4. Recursos com múltiplas representações**
  
  Ofereça diversos formatos dos recursos para diferentes necessidades.
  
  **5. Comunique sem estado**
  
  Em outras palavras, um servidor não deveria guardar o estado da comunicação de qualquer um dos clientes que se comunique com ele além de uma única requisição. A razão mais óbvia para isso é escalabilidade - o número de clientes que podem interagir com o servidor seria consideravelmente impactado se fosse preciso manter o estado do cliente.
      

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
* 504 Gateway Timeout

### SpringBoot Starter
https://start.spring.io/

### Testes Unitários/Integração
Classes de testes unitários devem ser sufixadas como: Test (ex: HelloTest), para testes de integração
o sufixo deve ser IT (ex: HelloIT).

São executados durante o build (mvn clean install) via surefire e failsafe plugins.
* surefire: http://maven.apache.org/surefire/maven-surefire-plugin/
```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.20.1</version>
    <configuration>
        <parallel>methods</parallel>
        <threadCount>10</threadCount>
    </configuration>
</plugin>
```

* failsafe: http://maven.apache.org/surefire/maven-failsafe-plugin/
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-failsafe-plugin</artifactId>
    <version>2.20.1</version>
    <configuration>
        <additionalClasspathElements>
            <additionalClasspathElement>${basedir}/target/classes</additionalClasspathElement>
        </additionalClasspathElements>
        <includes>
            <include>**/*IT.java</include>
        </includes>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>integration-test</goal>
                <goal>verify</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

O bootstrapping da aplicação é feito via spring-boot plugin (mvn spring-boot:run -Drun.profiles=stub)
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <profiles>
            <profile>stub</profile>
        </profiles>
    </configuration>
    <executions>
        <execution>
            <id>pre-integration-test</id>
            <goals>
                <goal>start</goal>
            </goals>
            <configuration>
                <skip>${it.skip}</skip>
            </configuration>
        </execution>
        <execution>
            <id>post-integration-test</id>
            <goals>
                <goal>stop</goal>
            </goals>
            <configuration>
                <skip>${it.skip}</skip>
            </configuration>
        </execution>
    </executions>
</plugin>
```
### Docker
### Heroku
