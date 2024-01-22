#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Validating placeAPI
Scenario Outline: verify if place is being successfully added using addPlaceApi
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user call "AddPlaceAPI" with "post" http request
    Then the api call got success with status code 200
    And "status" in Response Body is "OK"
    And "scope" in Response Body is "APP"
    And verify place_id is created map to "<name>" using "GetPlaceAPI"
   
   Examples:
   |name            |language     |address        |
   |Matoshree House |Hindi        |Hinjewadi pune |
   |Umang House     |Marathi      |Wagholi Pune   |
   |Alpesh          |Gujarati     |Ambreli Gujrat |
   
  Scenario: if delete Place functionality is working
    Given DeletePlace Payload 
    When user call "DeletePlaceAPI" with "delete" http request
    Then the api call got success with status code 200 
    And "status" in Response Body is "OK"
   
  