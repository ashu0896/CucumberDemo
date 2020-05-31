#Author: Ashish Gupta
Feature: Validate API’s to check the current and historical exchange data for financial raesons.

  Background: 
    Given Foreign Exchange Rates API is up and running

  Scenario: To assert the success status code  of the latest date
    When Hit the API with end point as "/latest"
    Then API should return the status code as 200
    
   Scenario Outline: Hit the API with correct and incorrect endpoint - validate the response
    When Hit the API with end point as "<endpoint>"
    Then base value should be <status>
    Examples:
    | endpoint                     | status |
      | /latest?base=USD           |    200 |
      | /latest?base=XXX	       |    400 |
      
    Scenario Outline: To assert the success status of past conversion rates response And
    Hit the API with future date and validate current date data should present
    
    When Hit the API with end point as "<endpoint>"
    Then API should return the result as "<result>"
    Examples:
  | endpoint                 | result |
      | /2010-01-12          |   200 |
	  | /2021-01-12     	| currentDateData |