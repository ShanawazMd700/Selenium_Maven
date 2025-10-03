package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/rerun.txt",   // read failed scenarios
        glue = {"stepdefs", "Hooks"},
        plugin = {
                "pretty",
                "html:target/rerun-report.html",
                "listeners.ExtentCucumberAdapter"
        },
        monochrome = true
)
public class FailedRunnerTest extends AbstractTestNGCucumberTests {
    // No @Test annotation needed
}
