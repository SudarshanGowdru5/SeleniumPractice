@tag
Feature: Purchase the order from Ecommerece website
  I want to use this template for my feature file

 Background:
Given I have landed on ecommerce page

  @Regresssion
  Scenario Outline: Positive scenario of Submit order
    Given Login to the application with username <username> and password <password>
    When Add the product <productname> to cart 
    And checkout the product<productname> and submit the order 
    Then "Thankyou for the order." is displayed on the confirmation Page

    Examples: 
      | username 					| password 		| productname  |
      | ss05@gmail.com 		| Suda@123		| ADIDAS ORIGINAL |
