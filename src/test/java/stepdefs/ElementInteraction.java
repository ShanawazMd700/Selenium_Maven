package stepdefs;
import Hooks.Hooks;
import Pages.*;
import io.cucumber.java.en.*;


public class ElementInteraction {

    private final submitform submitform;
    private final checkbox checkbox;
    private final radiobutton radiobutton;
    private final webtables webtables;
    private final buttons buttons;
    private final links links ;
    private final brokenlink brokenlink;
    private final uploadDownload uploadDownload;
    private final dynamicInteraction dynamicInteraction;

    public ElementInteraction() {
        this.submitform = new submitform(Hooks.driver); // initialize using Hooks
        this.checkbox = new checkbox(Hooks.driver);
        this.radiobutton = new radiobutton();
        this.webtables = new webtables();
        this.buttons = new buttons();
        this.links = new links();
        this.brokenlink = new brokenlink();
        this.uploadDownload = new uploadDownload();
        this.dynamicInteraction = new dynamicInteraction();
    }
    //Submit form Stepdefintions
    @Given("We go to the page {string}")
    public void we_go_to_the_page(String url) {
        System.out.println("Navigating to: " + url);
        submitform.gotowebpage(url);
    }

    @Given("We click on the menu item {string}")
    public void we_click_on_the_menu_item(String menuItem) {
        System.out.println("Clicking menu: " + menuItem);
        submitform.selectoption(menuItem);
    }

    @When("We click on the sub-option {string}")
    public void we_click_on_the_sub_option(String subOption) {
        System.out.println("Clicking sub-option: " + subOption);
        submitform.selectheader(subOption);
    }

    @When("We fill in the field with the label Full Name with the value {string}")
    public void fill_full_name(String fullName) {
        submitform.entername(fullName);
        System.out.println("Full Name entered: " + fullName);
    }

    @When("We fill in the field with the label Email with the value {string}")
    public void fill_email(String email) {
        submitform.enteremail(email);
        System.out.println("Email entered: " + email);
    }

    @When("We fill in the field with the label Current Address with the value {string}")
    public void fill_current_address(String currentAddress) {
        submitform.enteraddress(currentAddress);
        System.out.println("Current Address entered: " + currentAddress);
    }

    @When("We fill in the field with the label Permanent Address with the value {string}")
    public void fill_permanent_address(String permanentAddress) {
        submitform.enterpermanentaddress(permanentAddress);
        System.out.println("Permanent Address entered: " + permanentAddress);
    }

    @Then("Validation should be done on the fields")
    public void validate_fields() {
        submitform.validateTexts();
        System.out.println("Validation done!");
    }

    //CheckBox StepDefinitions
    @When("we click the toggle button {string}")
    public void we_click_the_toggle_button(String string) {
        // Write code here that turns the phrase above into concrete actions
        checkbox.selectTogglebutton(string);
        System.out.println("Clicking toggle: " + string);
    }

    @When("We click on the element showing text {string}")
    public void we_click_on_the_element_showing_text(String string) {
        // Write code here that turns the phrase above into concrete actions
        checkbox.selectcheckbox(string);
        System.out.println("Selecting toggle: " + string);
    }

    @Then("The checkbox with the text {string} should be checked")
    public void the_checkbox_with_the_text_should_be_checked(String string) {
        // Write code here that turns the phrase above into concrete actions
        checkbox.isCheckedstate(string);
        System.out.println("Checking State of toggle: " + string);
    }
    //Radio Button Step Definitions
    @When("We click on the element with text {string}")
    public void we_click_on_the_element_with_text(String string) {
        // Write code here that turns the phrase above into concrete actions
        radiobutton.selectRadiobutton(string);
        System.out.println("Clicking Element: " + string);
    }

    @Then("The radio button with the text {string} should be checked")
    public void the_radio_button_with_the_text_should_be_checked(String string) {
        // Write code here that turns the phrase above into concrete actions
        radiobutton.validateRadiobutton(string);
        System.out.println("Validating RadioButton: " + string);
    }
    //Web tables StepDefinitions
    @When("We click on the button with the text {string}")
    public void weClickOnTheButtonWithTheText(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        webtables.clickbuton(arg0);
        System.out.println("Clicking button: " + arg0);
    }

    @And("We fill in the field with the label First Name with the value {string}")
    public void weFillInTheFieldWithTheLabelFirstNameWithTheValue(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        webtables.fillfirstname(arg0);
        System.out.println("Filling First Name: " + arg0);
    }

    @And("We fill in the field with the label Last Name with the value {string}")
    public void weFillInTheFieldWithTheLabelLastNameWithTheValue(String arg0) {
        // Write code here that turns the phrase above into concrete actions
       webtables.filllastname(arg0);
        System.out.println("Filling Last Name: " + arg0);
    }

    @And("We fill in the field with the label emailID with the value {string}")
    public void weFillInTheFieldWithTheLabelEmailIDWithTheValue(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        webtables.fillemail(arg0);
        System.out.println("Filling emailID: " + arg0);
    }

    @And("We fill in the field with the label Age with the value {string}")
    public void weFillInTheFieldWithTheLabelAgeWithTheValue(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        webtables.fill_age(arg0);
        System.out.println("Filling Age: " + arg0);
    }

    @And("We fill in the field with the label Salary with the value {string}")
    public void weFillInTheFieldWithTheLabelSalaryWithTheValue(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        webtables.fillsalary(arg0);
        System.out.println("Filling Salary: " + arg0);
    }

    @And("We fill in the field with the label Department with the value {string}")
    public void weFillInTheFieldWithTheLabelDepartmentWithTheValue(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        webtables.fill_department(arg0);
        System.out.println("Filling Department: " + arg0);
    }

    @When("We click on the submit button")
    public void weClickOnTheSubmitButton() {
        // Write code here that turns the phrase above into concrete actions
        webtables.clickSubmitbutton();
        System.out.println("Clicking Submit Button: " );
    }

    @Then("The firstname {string} should be added to the WebTable")
    public void theFirstnameShouldBeAddedToTheWebTable(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        webtables.validate_firstname(arg0);
        System.out.println("Validating firstname: " + arg0);
    }
        //buttons step Definitions
    @Then("The message {string} should be displayed")
    public void theMessageShouldBeDisplayed(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        buttons.validatebuttonaction(arg0);
        System.out.println("Validating message: " + arg0);
    }

    @When("We double click on the button with the text {string}")
    public void weDoubleClickOnTheButtonWithTheText(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        buttons.doubleclickbutton(arg0);
        System.out.println("Double clicking : " + arg0);
    }

    @When("We right click on the button with the text {string}")
    public void weRightClickOnTheButtonWithTheText(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        buttons.rightclick(arg0);
        System.out.println("Right clicking : " + arg0);
    }
    //links StepDefinitions
    @When("We click on the link with the text Home")
    public void weClickOnTheLinkWithTheTextHome() {
        // Write code here that turns the phrase above into concrete actions
        links.clickHome();
        System.out.println("Clicking Home");
    }

    @Then("We should be redirected to the page {string}")
    public void weShouldBeRedirectedToThePage(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        links.validateClickHome1(arg0);
        System.out.println("Redirected To : " + arg0);
    }

    @When("We click on the link with the text {string}")
    public void weClickOnTheLinkWithTheText(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        links.selectLinkOptions(arg0);
        System.out.println("Clicked  : " + arg0);
    }

    @Then("The text {string} is shown")
    public void theTextIsShown(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        links.validateTheClick(arg0);
        System.out.println("Text Shown  : " + arg0);
    }

    //brokenlink stepefinitions
    @When("We opened the link containing {string}")
    public void weOpenedTheLinkContaining(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        brokenlink.clickonlink(arg0);
        System.out.println("Opened The Link  : " + arg0);
    }

    @Then("We should redirect to the main page {string}")
    public void weShouldRedirectToTheMainPage(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        brokenlink.validatelink(arg0);
        System.out.println("Redirected To The Page : " + arg0);
    }
    //Upload and download StepDefinitions
    @When("We click on the button with the text Download")
    public void weClickOnTheButtonWithTheTextDownload() {
        // Write code here that turns the phrase above into concrete actions
        uploadDownload.clickdownloadfile();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Clicked Download file");
    }

    @Then("The file should be downloaded successfully")
    public void theFileShouldBeDownloadedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions
        uploadDownload.VerifyFileDownloaded();
        System.out.println("Downloaded Successfully");
    }

    @When("We upload a file with the path {string}")
    public void weUploadAFileWithThePath(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        uploadDownload.uploadFile(arg0);
        System.out.println("Uploaded File location : " + arg0);
    }


    @Then("Verify the text {string} should be displayed successfully")
    public void verifyTheTextShouldBeDisplayedSuccessfully(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        uploadDownload.verifyUploadSuccess(arg0);
        System.out.println("Displayed Successfully : " + arg0);
    }
    //Dynamic Interaction.java Step Definitions

    @Then("Verify the button {string} is {string}")
    public void verifyTheButtonIs(String arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions
       dynamicInteraction.validateTheButtonColor(arg0,arg1);
        System.out.println("The button " + arg0 + " is of color " + arg1);

    }

    @When("We wait for {string} seconds")
    public void weWaitForSeconds(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        int seconds = Integer.parseInt(arg0);
        dynamicInteraction.pause(seconds);
        System.out.println("Waited for" + arg0+ "Seconds");

    }
}
