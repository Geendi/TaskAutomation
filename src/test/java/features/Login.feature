@LoginTest
Feature: Login

  Background:
    Given User is on the Login Page

  Scenario Outline: Successful user login with valid credentials
    When User enters valid credentials "<username>" and "<password>"
    And User clicks the login button
    Then User should be redirected to the account dashboard with the header "<expected>"
    Examples:
      | username              | password     | expected     |
      | validUser@example.com | ValidPass123 | MY DASHBOARD |

  Scenario Outline: Failed login attempt with invalid credentials
    When User attempts login with invalid credentials "<username>" and "<password>"
    And User clicks the login button
    Then an error message should be displayed "<expected message>"
    Examples:
      | username                | password       | expected message |
      | inValidUser@example.com | inValidPass123 | Invalid login or password.         |