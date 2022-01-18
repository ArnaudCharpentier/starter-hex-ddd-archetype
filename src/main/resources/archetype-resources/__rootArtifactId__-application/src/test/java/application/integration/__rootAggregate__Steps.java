package ${package}.application.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import ${package}.domain.${rootAggregateLowerCase.toLowerCase()}.model.${rootAggregate};
import ${package}.domain.common.exception.DomainConstraintViolationException;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ${rootAggregate}Steps {

    /**
     * These are automatically created test steps
     * for example purpose only
     * Remove or rename it depending of your needs
     */

    private Exception _exception;

    @When("^une personne de moins de 18 ans est instancié$")
    public void unePersonneDeMoinsDe18ansEstInstancie() {
        try {
            ${rootAggregate}.builder()
                .firstName("John")
                .lastName("Doe")
                .birthDate(LocalDate.now())
                .build();
        } catch (Exception e) {
            _exception = e;
        }
    }

    @Then("^une exception du domaine est levée$")
    public void uneExceptionDuDomaineEstLeve() throws Exception {
        assertTrue(_exception != null);
        assertTrue(_exception.getClass().equals(DomainConstraintViolationException.class));
    }
}
