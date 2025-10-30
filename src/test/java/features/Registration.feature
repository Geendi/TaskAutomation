@Registration
Feature: Registration

  Background:
    Given User is on the registration page

  Scenario Outline: Successful user registration with valid credentials
    When User attempts registration with valid "<password>" and "<confirm password>"
    And User clicks the register button
    Then User should see the success message "<expected message>"

    Examples:
      | password | confirm password | expected message |
      | validPass123 | validPass123 | Thank you for registering with Tealium Ecommerce. |

  Scenario Outline: Verify error message for invalid registration inputs
    When User attempts registration with invalid email "<email>", password "<password>", and confirm password "<confirmPass>"
    And User clicks the register button
    Then The application should display field error for "<fieldName>" containing "<expectedError>"

    Examples:
      | email            | password       | confirmPass  | fieldName        | expectedError                                                       |
      |    test@gmail    | validPass123   | validPass123 | emailField       | Please enter a valid email address. For example johndoe@domain.com. |
      | test@example.com | short          | short        | passwordField    | Please enter more characters or clean leading or trailing spaces.   |
      | test@example.com | pass123        | mismatch     | confirmPassField | Please make sure your passwords match.                              |