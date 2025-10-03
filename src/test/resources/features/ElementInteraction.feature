Feature: Element Interaction

  Scenario: Submitting details
    Given We go to the page "https://demoqa.com/"
    And We click on the menu item "Elements"
    When We click on the sub-option "Text Box"
    And We fill in the field with the label Full Name with the value "John Doe"
    And We fill in the field with the label Email with the value "jdoe@yahoo.com"
    And We fill in the field with the label Current Address with the value "123 Main St,Standard Avenel,Middlesex County,07001."
    And We fill in the field with the label Permanent Address with the value "456 Elm St,Standard Avenel,Middlesex County,07001."
    Then Validation should be done on the fields

  Scenario: Checking the checkbox
    Given We go to the page "https://demoqa.com/"
    And We click on the menu item "Elements"
    When We click on the sub-option "Check Box"
    When we click the toggle button "1"
    And We click on the element showing text "Desktop"
    Then The checkbox with the text "Desktop" should be checked

  Scenario: Enabling the Radio Buttons
    Given We go to the page "https://demoqa.com/"
    And We click on the menu item "Elements"
    When We click on the sub-option "Radio Button"
    And We click on the element with text "Yes"
    Then The radio button with the text "Yes" should be checked

  Scenario: Adding the data into the Web Tables
    Given We go to the page "https://demoqa.com/"
    And We click on the menu item "Elements"
    When We click on the sub-option "Web Tables"
    When We click on the button with the text "Add"
    And We fill in the field with the label First Name with the value "John"
    And We fill in the field with the label Last Name with the value "Doe"
    And We fill in the field with the label emailID with the value "johndoe@yahoo.com"
    And We fill in the field with the label Age with the value "30"
    And We fill in the field with the label Salary with the value "50000"
    And We fill in the field with the label Department with the value "Engineering"
    When We click on the submit button
    Then The firstname "John" should be added to the WebTable

  Scenario: Clicking the Buttons
    Given We go to the page "https://demoqa.com/"
    And We click on the menu item "Elements"
    When We click on the sub-option "Buttons"
    When We click on the button with the text "Click Me"
    Then The message "You have done a dynamic click" should be displayed
    When We double click on the button with the text "Double Click Me"
    Then The message "You have done a double click" should be displayed
    When We right click on the button with the text "Right Click Me"
    Then The message "You have done a right click" should be displayed

  Scenario: Selecting the links
    Given We go to the page "https://demoqa.com/"
    And We click on the menu item "Elements"
    When We click on the sub-option "Links"
    When We click on the link with the text Home
    Then We should be redirected to the page "https://demoqa.com/"
    When We click on the link with the text "Created"
    Then The text "Link has responded with staus 201 and status text Created" is shown

  Scenario: Selecting the valid link in Broken link
    Given We go to the page "https://demoqa.com/"
    And We click on the menu item "Elements"
    When We click on the sub-option "Broken Links - Images"
    When We opened the link containing "Click Here for Valid Link"
    Then We should redirect to the main page "https://demoqa.com/"

  Scenario: Handling uploads and downloads
    Given We go to the page "https://demoqa.com/"
    And We click on the menu item "Elements"
    When We click on the sub-option "Upload and Download"
    When We click on the button with the text Download
    Then The file should be downloaded successfully
    When We upload a file with the path "C:\Users\iray\Downloads\sampleFile.jpeg"
    Then Verify the text "C:\fakepath\sampleFile.jpeg" should be displayed successfully

  Scenario: Handling the Dynamic Interaction
    Given We go to the page "https://demoqa.com/"
    And We click on the menu item "Elements"
    When We click on the sub-option "Dynamic Properties"
    When We wait for "5" seconds
    When We click on the button with the text "Will enable 5 seconds"
    When We click on the button with the text "Color Change"
    When We click on the button with the text "Visible After 5 Seconds"
    Then Verify the button "Color Change" is "#dc3545"