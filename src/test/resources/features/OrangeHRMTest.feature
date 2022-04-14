@OMT-7930 @smoke @regression
  Feature: Add timesheet functionality

    Scenario: Validating add timesheet functionality with valid parameters
      Given user navigates to the OrangeHRM application
      When user login to the application and provides username "Admin" and password "admin123"
      And user select time header and clicks on Timesheets dropdown and click on My Timesheets option and clicks on Add Timesheet option
      And user select a day to create Timesheet and click Ok button and provide parameters
     # Then user validates timesheet is added

    @OMT-7935 @smoke @regression
      Scenario: Adding user functionality
        Given user navigates to the OrangeHRM application
      When user login to the application and provides username "Admin" and password "admin123"
        When user adds user
        Then user validates user is added