Feature: Login

  Scenario: Successful user login with valid credentials
    Given A registered user exists and is on the Login Page
    When User logs in with stored valid credentials
    Then User should be redirected to the account dashboard with the header "MY DASHBOARD"


  Scenario: Failed login attempt with invalid credentials
    Given User is on the Login Page
    When User attempts login with "invaliduser@example.com" and "WrongPass"
    Then The application should display the global error message "Invalid login or password."