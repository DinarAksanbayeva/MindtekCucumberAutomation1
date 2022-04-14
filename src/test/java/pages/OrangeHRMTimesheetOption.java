package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class OrangeHRMTimesheetOption {
    public OrangeHRMTimesheetOption() {
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "(//a[@class='firstLevelMenu'])[4]")
    public WebElement timeButton;

    @FindBy(id = "menu_time_Timesheets")
    public WebElement timesheetsButton;

    @FindBy(id = "menu_time_viewMyTimesheet")
    public WebElement myTimesheetsButton;

    @FindBy(id = "btnAddTimesheet")
    public WebElement addTimesheetsButton;


}