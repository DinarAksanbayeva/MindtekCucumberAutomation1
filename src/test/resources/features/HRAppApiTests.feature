@smoke @regression
Feature: Validating HR App Rest Api calls

  @api
  Scenario: Validating GET Employee Api Call
    Given user sends GET call for employee with employeeId 150
    Then user validates status code is 200
    When user navigates to HR application
    And user logs in with username "Mindtek" password "MindtekStudent"
    And user search for employee with empoloyeeId 150
    Then validates for employee data from UI matches with API response

  @api @TC-101
  Scenario:Validating Location Api calls
    Given user creates location with Post api call with data
      | locationCity    | Orlando |
      | locationCountry | US      |
      | locationId      | 9395    |
      | locationState   | Florida |
    Then user validates status code is 201
    When user gets created location
    Then user validates status code is 200
    And user validates in get call data matches with created data

  @api @TC-102
  Scenario: Validating employees data from specific department in UI matches with API response
    When user navigates to HR application
    And user logs in with username "Mindtek" password "MindtekStudent"
    And user selects "IT" department
    And user stores employee data in selected department
    When sends gey employees api call fot "IT" department
    Then user validates status code is 200
    And user validates ui data matches with api data in "IT" department employee

  @api @TC-104
  Scenario: Validating jobs api calls
    Given user creates job with POST call with data
      | jobId   | mindtek |
      | salary | 100000  |
      | title  | SDET    |
    Then user validates status code is 201
    When user gets created job
    Then user validates status code is 200
    And user validates created data matches with data in get response

