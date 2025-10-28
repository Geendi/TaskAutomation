Feature: Registration

  Scenario Outline: Successful user registration with unique credentials
    Given I am on the Home Page
    When User attempts registration with unique "<password>" and "<confirmPass>"
    Then User should see the success message "Thank you for registering with Tealium Ecommerce."

    Examples:
      | email | password     | confirmPass  | fieldName  | expectedError             |
      |       | ValidPass123 | ValidPass123 | emailField | This is a required field. |

  Scenario Outline: Verify error message for invalid registration inputs
    Given User is on the Home Page
    When User navigates to Registration page
    And User attempts registration with invalid input: "<email>", "<password>", "<confirmPass>"
    Then The application should display field error for "<fieldName>" containing "<expectedError>"

    Examples:
      | email              | password | confirmPass | fieldName      | expectedError           |
      |                    | pass123  | pass123     | emailField     | This is a required field. |
      | test@example.com   | short    | short       | passwordField  | Please enter 6 or more characters. |
      | test@example.com   | pass123  | mismatch    | confirmPassField | Please make sure your passwords match. |