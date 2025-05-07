package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Tests Cucumber del API del Price
 * @author Sergio Torres Téllez 2012
 */


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/get.feature", monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/runapiat",
        "json:target/cucumber-report/runapiat/cucumber.json",
        "rerun:target/cucumber-report/runapiat/rerun.txt"},
        glue = "cucumber")
public class GetTests {
    // Implementación vacía. Ver los features definidos en src/test/resources
}