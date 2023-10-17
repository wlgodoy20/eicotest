# HELP
# This will output the help for each task
# thanks to https://marmelab.com/blog/2016/02/29/auto-documented-makefile.html
.PHONY: help

tag=latest

help: ## Lista de comandos disponíveis
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)

.DEFAULT_GOAL := help

run-deps: ## Sobe compose das dependências (MySQL)
	cd docker && docker-compose -f docker-compose-dependencies.yml up

migration: ## Executa migrations do banco de dados, e outros scripts de inicialização
	./gradlew -Dflyway.configFiles=flyway.conf flywayMigrate

clean-db: ## Limpa o banco de dados (drop)
	./gradlew -Dflyway.configFiles=flyway.conf flywayClean

test: ## Executa os testes e relatório de cobertura
	./gradlew clean test jacocoTestReport