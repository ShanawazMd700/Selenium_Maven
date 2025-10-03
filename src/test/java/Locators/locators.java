package Locators;
import org.openqa.selenium.*;
public interface locators
{
    //SubmitForm.java locators
    //public static String url = "https://www.calculator.net/";
    static By selectOption(String value) {return By.xpath("//h5[contains(text(),'" + value + "')]");}
    static By selectSliderOption(String value) { return By.xpath("//li[@class='btn btn-light ']/span[text()='" + value + "']");}
    static By button(String value) {return By.xpath("//button[text()='" + value + "']");}
    public static By fullname = By.xpath("//input[@id='userName']");
    public static By emailbox = By.xpath("//input[@id='userEmail']");
    public static By currentaddress = By.xpath("//textarea[@id='currentAddress']");
    public static By permanentaddress = By.xpath("//textarea[@id='permanentAddress']");
    public static By submitButton = By.xpath("//button[@id='submit']");

    //checkboxlocators
    static By checkboxToggle(String index) {return By.xpath("(//button[@title='Toggle'])[" + index + "]");	}//selecting toggle
    static By checkboxWithLabel(String value) {return By.xpath("//span[text()='" + value + "']/preceding-sibling::span[contains(@class, 'checkbox')]");}

    //radiobitton.java locators
    static By radiobutton(String value) { return By.xpath("//label[text()='" + value + "']");	}
    static By radiobuttonvalidationtext(String value) {return By.xpath("//p[@class='mt-3']/span[text()='"+ value +"']");}

    //Webtable locators
    static By butttontext(String value) {return By.xpath("//button[text()='"+ value +"']");}//to select buttons with text
    public static By firstname = By.id("firstName");
    public static By lastname = By.id("lastName");
    public static By email = By.xpath("//input[@id='userEmail']");
    public static By age = By.id("age");
    public static By salary = By.id("salary");
    public static By department = By.id("department");
    public static By submitbutton = By.xpath("//button[@id='submit']");
    static By textvalidation(String value) {return By.xpath("//div[@class='rt-tr-group']/descendant::div[@class = 'rt-td' and text() = '"+ value +"']");}

    //button.java locators
    static By buttonActionText(String value) {return By.xpath("//p[text()='"+value +"']");}

    //links.java locators
    public static By links_link1 = By.xpath("//a[@id = 'simpleLink']");
    public static By links_link2 = By.xpath("//a[@id = 'dynamicLink']");
    static By linkoptions(String value) {return By.xpath("//a[text()='"+value+"']");}
    public static By link_response = By.xpath("//p[@id='linkResponse']");
    public static String locators = null;

    //brokenlink.java locators
    static By link1(String value) {return By.xpath("//a[text()='"+value+"']");}
    public static By homebanner = By.xpath("//div[@class='home-banner']");

    //UploadDownload.java Locators
    public static By downloadButton= By.xpath("//a[@id='downloadButton']");
    public static By uploadButton = By.xpath("//div[@class='form-file']/input['uploadFile']");
    public  static By uploadResponse = By.xpath("//p[@id='uploadedFilePath']");

    //forms.java locators
    static By name_registration(String value){ return By.xpath("//div[@class ='col-md-4 col-sm-6']/input[@placeholder='"+value+"']");}
    //public static By email_registration = By.xpath("//input[@id='userEmail']");
     static By form_genderSelect(String value){ return By.xpath("//label[text() = '"+value+"']");}
    public static By mobilenumber = By.xpath("//input[@id='userNumber']");
    public static By subjectinput = By.xpath("//input[@id='subjectsInput']");
    public static By dob_box = By.id("dateOfBirthInput");
    public static By yearbase = By.xpath("//select[@class='react-datepicker__year-select']");
    public static By monthbase = By.xpath("//select[@class='react-datepicker__month-select']");
    //static By month_pick(String value){ return By.xpath("//select[@class = 'react-datepicker__month-select']/option[contains(text(),'"+value+"')]");}
    static By datePick(String value1,String value2){return By.xpath("//div[contains(@aria-label,'"+value1+"') and text() = '"+value2+"']");}
    public static By state_input = By.id("react-select-3-input");
    public static By city_input = By.id("react-select-4-input");
    static By header_text(String value) {return By.xpath("//div[text()='"+value+"']");}

    //browswerwindows.java locators
    static By newtabHeader(String value) { return By.xpath("//h1[text()='"+value+"']");}

    //alerts.java locators
    static By alert_button(String value)  {return By.xpath("(//button[text()='Click me'])["+value+"]");}
    static By alert_buttons(String value)  {return By.xpath("//span[contains(text(),'"+value+"')]/ancestor::div[@class='mt-4 row']//button[text()='Click me']");}

    //Selectmenu.java locators
    public static By firstDropdown = By.xpath("(//div[contains(@class,'css-1hwfws3')])[1]");
    public static By thirdDropdown = By.xpath("//div[@class='css-12jo7m5']");
    public static By standardDropdown = By.id("oldSelectMenu");
    static By dropdownOptions(String value){return By.xpath("//div[@class='css-12jo7m5' and text() = '"+value+"']");}


}
