package ${package}.application.common;

import ${package}.application.config.Authorities;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Configuration de Spring
 *
 * Mettre les beans à mocker ici (Les adapters de la couche infrastructure) 
 * (à récupérer avec un @AutoWired dans les classes steps)
 * 
 * Ajouter les classes des services à injecter dans nos steps (Service, Domaine, ...)
 */
@CucumberContextConfiguration
@SpringBootTest(
    classes = { 
        SpringIntegrationContext.class,
        Authorities.class
    }
)
public class SpringIntegrationContext {

    /**
     * Example:
     * @MockBean
     * ${rootAggregate}RepositoryPort situationRaRepository;
     */
}
