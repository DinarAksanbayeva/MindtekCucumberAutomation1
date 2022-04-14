package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class OrangeHRMLoginPage {

    public OrangeHRMLoginPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="txtUsername")
    public WebElement loginUserName;

    @FindBy(id="txtPassword")
    public WebElement loginPassword;

    @FindBy(id="btnLogin")
    public WebElement loginButton;






}
