# Programação Web II - Projeto Movie

## Integrantes:
- [Emanuel Montenegro](https://github.com/emanuelcatao/)
- [João Victor Santana Costa](https://github.com/jvictorscosta/)
- [Helen Her](https://github.com/herhelen/)
- [Marianny Neris Ferreira](https://github.com/mariannyneris2004/)
- [Pedro Henrique Guerra De Abreu](https://github.com/PedroHenriqueGuerra/)

## Objetivo

Construir uma API:
- Utilizando tecnologias e conceitos aprendidos nos módulos.
- Com persistência em banco de dados (H2 ou Postgres)
- Configuração de segurança com controle de rota e login.
- Consume uma API externa pública

Opcionais:
- Usar jwt
- Frontend

## Resultado

Implementamos uma API que possibilita o usuário criar várias listas contendo filmes.
Os filmes são retornados atráves de chamadas a uma API externa, [TMDB](https://developer.themoviedb.org/docs).
Implementamos também outras operações de CRUD (*Create, Read, Update, Delete*) para lista e usuário.

Utilizamos banco de dados *Postgres* no projeto hospedado no [supabase](https://supabase.com/). Possibilitando compartilharmos os mesmos dados durante o desenolvimento, e também tirando o processo de configurar um servidor local.

A parte de segurança foi a parte mais trabalhosa, mas conseguimos usar *jwt*. E apesar de termos criado a entidade *Role*, não foi usada, pois a fim de simplificar a implementação não tinha necessidade de definir papéis com diferentes permissões para os usuários. Como um ponto de melhoria, podemos criar pelo menos 2 papéis, um para administrador e outro para usuário comum.

Ainda não desenvolvemos habilidades para planejar e implemntar um frontend. Usamos a plataforma [Postman](https://www.postman.com/) para realizar nossos testes.

Por fim, documentamos os *endpoints* da nossa API usando [springdoc-openapi](https://central.sonatype.com/search?q=springdoc-openapi-starter-webmvc-ui) que tem integração com *Swagger UI*, o que possibilita uma interface mais amigável com mais detalhes da função de cada *endpoint* e de seus possíveis retornos.

