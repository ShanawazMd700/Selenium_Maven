package stepdefs;
import Pages.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;

public class AlertFrameWindow {
    private final browserwindow browserwindow;
    private final Alerts alertpage;
    public AlertFrameWindow()
    {
        this.browserwindow = new browserwindow();
        this.alertpage = new Alerts();
    }
    @Then("The title of the page should be {string}")
    public void theTitleOfThePageShouldBe(String arg0) {
        // Write code here that turns the phrase above into concrete actions
       browserwindow.validateNewTab(arg0);
       System.out.println("Title  "+arg0+"  Shown");
    }
    
    //alerts Step definitions
    @When("We click on the Click me button {string}")
    public void weClickOnTheClickMeButton(String arg0) {
        alertpage.clickAlertButton(arg0);
        System.out.println("Clicked :"+arg0);
    }

    @Then("The alert should be displayed with the text {string}")
    public void theAlertShouldBeDisplayedWithTheText(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        alertpage.verifyAlertText(arg0);
        System.out.println("Alert "+arg0+" is displayed");
    }

    @When("We click on the Click me button with the text {string}")
    public void weClickOnTheClickMeButtonWithTheText(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        alertpage.clickalertButton(arg0);
        System.out.println("Clicked :"+arg0);
    }
}
