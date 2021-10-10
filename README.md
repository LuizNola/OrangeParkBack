# AOBA! Esse  e o OrangePark

Esse e o backend do sistema OrangePark, criado como resolução do desafio proposto pela FCamara . 
O sistema visa o gerenciamento de um estacionamento de carros e motos. Veja mais detalhes do desafio [aqui](https://github.com/fcamarasantos/backend-test-java)

### Principais tecs usadas

-SpringBoot 2.5.4  
-SpringSecurity  
-JWT  
-JPA  

# Rotas

Acessando a pasta Docs dentro do projeto vera um arquivo do Insomnia, com todas as rotas disponíveis ja preparadas para teste.

# Requisitos 
Não tem muito segredo não! Para rodar o projeto basta ter o java 11 instalado, e um arquivo **application.properties** com as seguintes configurações:

`spring.datasource.url=  
spring.datasource.username=  
spring.datasource.password=  
spring.datasource.driver-class-name=  
spring.jpa.hibernate.ddl-auto=  
spring.jpa.show-sql=  
var.config.cors.allowed =  `

*Temporariamente a um banco de dados exposto dentro do arquivo de configuração que esta no repositorio, porem não vai durar muito tempo ja que e um host gratuito que usei apenas para alguns testes durante o desenvolvimento.

*Recomendo o banco PostgreSQL

## Vai ter telinha sim! 

Junto com esse sistema em Java Spring Boot, Temos um FrontEnd feito em react, vai la dar uma olhada, basta clicar [aqui](https://github.com/LuizNola/OrangeParkFront)

## Estamos no ar 🤩

Por pouco tempo, a aplicação esta de pé! E você pode ir lá dar uma conferida: https://orange-park-front.vercel.app/
