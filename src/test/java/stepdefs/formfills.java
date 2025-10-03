package stepdefs;
import Pages.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;

public class formfills {
    private final forms forms;
    public formfills() {
        this.forms = new forms();
    }

    @When("We enter the user's {string} as {string}")
    public void weEnterTheUsersAs(String field, String value) {
        // Write code here
        forms.fillname(field,value);
        System.out.println("Entered"+field+"of value :"+value);
    }

    @When("we enter the user's email as {string}")
    public void weEnterTheUsersEmailAs(String email) {
        forms.enteremail(email);
        System.out.println("Entered Email"+email);
    }

    @When("We select gender as {string}")
    public void weSelectGenderAs(String gender) {
        forms.selectoption(gender);
        System.out.println("Selected option"+gender);
    }

    @When("We enter the users mobile number as {string}")
    public void weEnterTheUsersMobileNumberAs(String mobile) {
        forms.enterNumber(mobile);
        System.out.println("Entered mobile number as :"+mobile);
    }

    @When("We select the subject as {string}")
    public void weSelectTheSubjectAs(String subject) {
        forms.EnterSubjects(subject);
        System.out.println("Selected value :"+subject);
    }

    @When("We select the radiobutton {string}")
    public void weSelectTheRadiobutton(String option) {
        forms.selectoption(option);
        System.out.println("Selected Option :"+option);
    }

    @When("we select the state {string}")
    public void weSelectTheState(String state) {
        forms.selectState(state);
        System.out.println("Selected Option :"+state);
    }

    @When("We select the city {string}")
    public void weSelectTheCity(String city) {
        forms.selectCity(city);
        System.out.println("Selected Option :"+city);
    }

    @When("We select the year {string} and month {string} and the day {string}")
    public void weSelectTheYearMonthAndDay(String year, String month, String day) {
        forms.selectYearMonthDay(year, month, day);
        System.out.println("Selected year: "+year+" Selected Month :"+month+" Selected day :"+day);
    }

    @Then("We the text {string} should be displayed")
    public void weTheTextShouldBeDisplayed(String expectedText) {
        forms.verifyTheText(expectedText);
        System.out.println("Displayed text :"+expectedText);
    }

}