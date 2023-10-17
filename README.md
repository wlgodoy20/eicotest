# eicotest
🛠 Pré-requisitos
Docker e Docker Compose: Necessários para subir as dependências, como o MySQL.
Java 17 e Gradle: Essenciais se você estiver executando comandos fora do Docker.
🚀 Configurando e executando a API
1. Subindo as Dependências
   Antes de iniciar a API, garanta que as dependências estejam ativas.
2. Para subir o MySQL usando Docker:
   execute :  make run-deps
3. Executando Migrations
   Para inicializar o banco de dados ou executar migrations, utilize:
   make migration
4. Limpando o BD
   Se desejar limpar o banco de dados (removendo todas as tabelas), utilize:
   make clean-db
5. Executando os Testes
    Para certificar-se de que tudo está funcionando corretamente, você pode executar os testes e também obter um relatório de cobertura:
   make test
6. Swagger disponivel em: 
 http://localhost:8090/swagger-ui/index.html
