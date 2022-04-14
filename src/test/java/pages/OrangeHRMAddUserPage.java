package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class OrangeHRMAddUserPage {
    public OrangeHRMAddUserPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "(//a[@class='firstLevelMenu'])[1]")
    public WebElement adminButton;

    @FindBy(id = "btnAdd")
    public WebElement addButton;

    @FindBy(xpath = "//option[@value='2']")
    public WebElement essButton;

    @FindBy(id = "systemUser_employeeName_empName")
    public WebElement employeeName;

    @FindBy(id = "systemUser_userName")
    public WebElement userName;

    @FindBy(xpath = "//select[@class='formSelect']/option[1]")
    public WebElement status;

    @FindBy(id = "systemUser_password")
    public WebElement password;

    @FindBy(id = "systemUser_confirmPassword")
    public WebElement confirmPassword;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

}
