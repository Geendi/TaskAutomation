@LoginTest
Feature: Login

  Scenario Outline: Successful user login with valid credentials
    Given User is on the Login Page
    When User logs in with username "<username>" and password "<password>"
    Then User should be redirected to the account dashboard with the header "<expected>"
    Examples:
      | username              | password     | expected     |
      | validUser@example.com | ValidPass123 | MY DASHBOARD |

  Scenario Outline: Failed login attempt with invalid credentials
    Given User is on the Login Page
    When User attempts login with username "<username>" and password "<password>"
    Then The application should display the global error message "<expected>"
    Examples:
      | username                | password       | expected |
      | inValidUser@example.com | inValidPass123 | Invalid login or password.         |

