package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import java.io.File;

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
    // ✅ Static initializer to ensure rerun.txt exists
    static {
        try {
            File rerunFile = new File("target/rerun.txt");
            rerunFile.getParentFile().mkdirs(); // ensure target/ exists
            if (!rerunFile.exists()) {
                rerunFile.createNewFile(); // create rerun.txt if missing
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to prepare rerun.txt file", e);
        }
    }
}
