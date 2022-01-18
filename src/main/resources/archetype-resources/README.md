# Structuration

Cette application est un canevas permettant de commencer une application MicroService REST basée sur SpringBoot.

Elle suit les préconisations de l'architecture logicielle de référence : l'architecture hexagonale 

Elle est composée d'un projet parent et de 4 sous-modules :

- Domain
- Application
- Exposition
- Infrastructure

# Packaging

L'artifact produit par le build est un fichier "JAR" autonome embarquant le Tomcat.


# Démarrer l'application

1) Depuis Eclipse sur le projet "Exposition" : Run as java application

	url : http://localhost:8080/hello?name=toto

# La liste des actuators :
La liste des actuators est visible via l'url : http://localhost:8080/diagnostic/actuators/

# Documentation API
L'interface swagger-ui est visible via l'url : http://localhost:8080/diagnostic/swaggerfiles.html


# Paramétrage

Les paramètres de l'application (port, contextPath, etc...) peuvent être modifiés dans les fichiers de configuration au format yaml :
- application.yml contient tous les paramètres communs à tous les environnements (ie paramètres métiers) 

# Couverture de code et code quality

Il est possible de voir unitairement la couverture de code de chaque module
Pour cela un reporting JaCoCo est créé à chaque phase de build
Rendez-vous dans le répoertoire __target/site/jacoco__ de chaque module et ouvrez le rapport contenu dans le fichier __index.html__

### Rapport global

Une tâche dans le pom parent vous permet de centraliser les différents rapports de chaque module.
Pour cela :
* Faites tourner une image docker de __sonarqube__
  * ```docker pull sonarqube```
  * ```docker run -d --name sonarqube -p 9000:9000 sonarqube```
  * Vérifier que votre sonarqube s'est bien initialisé avec la commande ```docker ps```
* A la racine de votre projet, lancer la commande suivante
  * ```mvn sonar:sonar -D'sonar.issuesreport.html.enable=true'```
  * Vous devriez obtenir une url à la fin du rapport :
  ```
  [INFO] ANALYSIS SUCCESSFUL, you can browse http://localhost:9000/dashboard?id=com.bnpp.pf.your-project
  ```

  Cliquez sur le lien pour avoir l'intégralité de votre rapport