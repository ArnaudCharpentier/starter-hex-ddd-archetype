# DDD Hexagonal archetype

##  Archetype installation in local maven repository

```sh
mvn clean install
```
## How to use
```sh
mvn archetype:generate
```

* Sélectionner ensuite le numéro correspondant à l'archetype (normalement, le dernier de la liste)
* Renseigner les champs nécessaires
  
## Release notes

- v1.0.0 : Initial commit

## Running

- simple run maven command to build the jar
`mvn clean install`
- access the -exposition directory, and start the spring boot app with maven 
`mvn spring-boot:run`
*comments*: the first command maven build, and launch units tests for every modules.

