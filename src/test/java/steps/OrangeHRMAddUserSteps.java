package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.OrangeHRMAddUserPage;
import pages.OrangeHRMLoginPage;
import utilities.ConfigReader;
import utilities.Driver;

public class OrangeHRMAddUserSteps {
    WebDriver driver = Driver.getDriver();

    OrangeHRMLoginPage login = new OrangeHRMLoginPage();
    OrangeHRMAddUserPage addUserPage=new OrangeHRMAddUserPage();

    @Given("user navigates to the OrangeHRM application")
    public void userNavigatesToTheOrangeHRMApplication() {
        driver.get(ConfigReader.getProperty("OrangeHRM"));
    }


    @When("user adds user")
    public void user_adds_user() {

        addUserPage.adminButton.click();
        addUserPage.addButton.click();
        addUserPage.essButton.click();
        addUserPage.employeeName.sendKeys("Nina Patel");
        addUserPage.userName.sendKeys("Nina.P");
        addUserPage.status.click();
        addUserPage.password.sendKeys("Nina123!");
        addUserPage.confirmPassword.sendKeys("Nina123!");
        addUserPage.saveButton.click();



    }

    @Then("user validates user is added")
    public void userValidatesUserIsAdded() {

        driver.findElement(By.id("searchSystemUser_userName")).sendKeys("Nina.D");
        driver.findElement(By.id("searchBtn")).click();
        String actual=driver.findElement(By.xpath("//table[@class='table hover']/tbody/tr/td[4]")).getText();
        String expected="Nina Patel";

        Assert.assertEquals(expected,actual);

    }
}