@applicativeRoles
Feature: Vérifie les rôles applicatifs sur chaque méthode public de la couche application services

  En tant qu'utilisateur
  Pour pouvoir réaliser une opération qui nécessite des droits spécifiques
  Je veux vérifier que le rôle applicatif est correctement associé à la méthode de la couche application service
  Je veux également pouvoir vérifier qu'un rôle applicatif est bien refusé lors de l'appel d'une méthode de la couche application service

  Scenario Outline: Vérifier que les rôles autorisés peuvent faire l'appel de méthode spécifié
    Given il y a des méthodes sécurisées dans la couche application service:
      | servicePath | <servicePath> |
      | methodName  | <methodName>  |
    When un utilisateur essaie d'exécuter la méthode spécifique "<methodName>"
    Then il est autorisé a exécuter cette opération si il a le rôle parmi "<neededApplicativeRoles>"
    Examples:
      | servicePath                                                             | methodName                          | neededApplicativeRoles                                            |
      | ${package}.application.service.impl.Recuperer${rootAggregate}ServiceImpl | getAll${rootAggregate}s             | ROLE_CAN_READ,ROLE_CAN_CREATE,ROLE_CAN_UPDATE,ROLE_CAN_DELETE |

  Scenario Outline: Vérifier que les rôles autorisés peuvent faire l'appel de méthode spécifié et que les rôles non authorisés sont bien bloqués
    Given il y a des méthodes sécurisées dans la couche application service:
      | servicePath | <servicePath> |
      | methodName  | <methodName>  |
    When un utilisateur essaie d'exécuter la méthode spécifique "<methodName>"
    Then il est autorisé a exécuter cette opération si il a le rôle parmi "<neededApplicativeRoles>"
    And il n'est pas autorisé à exécuter cette opération si il a le rôle parmi "<deniedApplicativeRoles>"
    Examples:
      | servicePath                                                             | methodName                          | neededApplicativeRoles                              | deniedApplicativeRoles  |
      | ${package}.application.service.impl.Ajouter${rootAggregate}ServiceImpl   | add${rootAggregate}                 | ROLE_CAN_CREATE,ROLE_CAN_UPDATE,ROLE_CAN_DELETE  | ROLE_CAN_READ          |
      | ${package}.application.service.impl.Modifier${rootAggregate}ServiceImpl  | update${rootAggregate}              | ROLE_CAN_CREATE,ROLE_CAN_UPDATE,ROLE_CAN_DELETE  | ROLE_CAN_READ          |
      | ${package}.application.service.impl.Supprimer${rootAggregate}ServiceImpl | remove${rootAggregate}              | ROLE_CAN_CREATE,ROLE_CAN_UPDATE,ROLE_CAN_DELETE  | ROLE_CAN_READ          |