package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.OrangeHRMLoginPage;
import pages.OrangeHRMTimesheetOption;
import utilities.ConfigReader;
import utilities.Driver;

public class OrangeHRMSteps {

    WebDriver driver = Driver.getDriver();

    OrangeHRMLoginPage login =new OrangeHRMLoginPage();
    OrangeHRMTimesheetOption timesheetOption=new OrangeHRMTimesheetOption();


    @When("user login to the application and provides username {string} and password {string}")
    public void userLoginToTheApplicationAndProvidesUsernameAndPassword(String username, String password) throws InterruptedException {
     login.loginUserName.sendKeys(username);
      login.loginPassword.sendKeys(password);
      login.loginButton.click();
      Thread.sleep(2000);
    }

    @And("user select time header and clicks on Timesheets dropdown and click on My Timesheets option and clicks on Add Timesheet option")
    public void userSelectTimeHeaderAndClicksOnTimesheetsDropdownAndClickOnMyTimesheetsOptionAndClicksOnAddTimesheetOption() {
        timesheetOption.timeButton.click();
        timesheetOption.timesheetsButton.click();
        timesheetOption.myTimesheetsButton.click();
        timesheetOption.addTimesheetsButton.click();

    }

    @And("user select a day to create Timesheet and click Ok button and provide parameters")
    public void userSelectADayToCreateTimesheetAndClickOkButtonAndProvideParameters() {

        driver.findElement(By.xpath("//img[@class='ui-datepicker-trigger']")).click();
       driver.findElement(By.xpath("(//a[@class='ui-state-default'])[4]")).click();
        driver.findElement(By.id("addTimesheetBtn")).click();


    }

//   // @Then("user validates timesheet is added")
//    public void userValidatesTimesheetIsAdded() {
//        String actual=driver.findElement(By.id("columnName")).getText();
//        System.out.println(actual);
//        String expected="Internal - Training and Development";
//        Assert.assertEquals(expected,actual);
    }
