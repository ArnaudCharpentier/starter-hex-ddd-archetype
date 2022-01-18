package ${package}.application;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/technical/features/", 
    plugin = { 
        "pretty", 
        "html:target/cucumber.html" 
    }, 
    glue = {
        "${package}.application.common",
        "${package}.application.technical",
     })
public class CucumberTechnicalTest {
}
