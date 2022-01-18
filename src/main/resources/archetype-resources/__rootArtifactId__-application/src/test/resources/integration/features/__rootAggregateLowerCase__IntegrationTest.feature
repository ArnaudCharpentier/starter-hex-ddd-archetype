Feature: ${rootAggregate} check on creation

  !! For demo purpose only !!
  Describe a scenario using Given / When / Then keywords

Scenario: Quand j'instancie une personne agé de moins de 18 ans, je dois lever une exception
  Given une étape commune
  When une personne de moins de 18 ans est instancié
  Then une exception du domaine est levée 