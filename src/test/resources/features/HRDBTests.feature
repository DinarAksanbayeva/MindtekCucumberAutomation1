@db, @ui, @regression, @MB-105
Feature: Validating Database data is properly displayed in UI

  Scenario Outline: Validating Marketing department employees data in DB and UI
    When user navigates to HR application
    And user logs in with username "Mindtek" password "MindtekStudent"
    And user selects "<Department>" department
    And user stores employee data in selected department
    Then user validates UI data matches with data in DB for "<Department>" department
    Examples:
      | Department     |
      | Marketing      |
      | IT             |
      | Administration |

    @MB4-192
    Scenario: Validating edit employee functionality
      When user navigates to HR application
      And user logs in with username "Mindtek" password "MindtekStudent"
      And user clicks on edit button for employee with 100 id
      And user updates firstName for employee with "Patel"
      Then user validates employee name is updated in
      And user validated employee name is updated API get call
