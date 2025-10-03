package stepdefs;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import Pages.*;

public class widgets
{
    private final selectmenu menuselect;
    public widgets()
    {
        this.menuselect = new selectmenu();
    }

    //Selectmenu.java stepdefinitions
    @When("We select the option {string} from the first dropdown")
    public void weSelectTheOptionFromTheFirstDropdown(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        menuselect.selectDropdownOption(arg0);
        System.out.println("Selected Option: " + arg0);
    }

    @Then("Verify if the option {string} is selected in the first dropdown")
    public void verifyIfTheOptionIsSelectedInTheFirstDropdown(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        menuselect.verifySelectedOption(arg0);
        System.out.println("Validated Option: " + arg0);
    }

    @When("We select the option {string} from the second dropdown")
    public void weSelectTheOptionFromTheSecondDropdown(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        menuselect.selectSecondOption(arg0);
        System.out.println("Selected Option: " + arg0);
    }

    //Standard Step definitions
    @When("We select {string} from the standard dropdown")
    public void weSelectFromTheStandardDropdown(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        menuselect.selectStandardOption(arg0);
        System.out.println("Selected Option: " + arg0);
    }

    @Then("Verify if {string} is selected in the standard dropdown")
    public void verifyIfIsSelectedInTheStandardDropdown(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        menuselect.verifyStandardOption(arg0);
        System.out.println("Validated Option: " + arg0);
    }

    @When("We select {string} from the third dropdown")
    public void weSelectFromTheThirdDropdown(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        menuselect.selectMultiDropdownOptions(arg0);
        System.out.println("Selected Option: " + arg0);
    }

    @Then("Verify if the option {string} is selected in the third dropdown")
    public void verifyIfTheOptionIsSelectedInTheThirdDropdown(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        menuselect.verifyselectedfromdropdown(arg0);
        System.out.println("Validated Option: " + arg0);
    }
}
