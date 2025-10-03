package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefs", "Hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "rerun:target/rerun.txt",
                "listeners.ExtentCucumberAdapter"// generates list of failed scenarios
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // Nothing else needed
}
