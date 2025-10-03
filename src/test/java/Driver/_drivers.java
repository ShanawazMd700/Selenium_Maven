package Driver;
import org.openqa.selenium.WebDriver;
public class _drivers
{
    private static WebDriver driver;

    // Getter
    public static WebDriver getDriver() {
        return driver;
    }

    // Setter
    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
}
