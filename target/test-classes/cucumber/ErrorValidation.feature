
@tag
Feature: Error validation scenarios
  I want to use this template for my feature file

  @Smoke
  Scenario Outline: Error validation for login credentials
    Given I have landed on ecommerce page
    When Login to the application with username <username> and password <password> 
    Then "Incorrect email or password." error message is displayed

  Examples: 
      | username 					| password 		|
      | ss05@gmail.com 		| Suda@12		|
