package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HRAppHomePage;
import pages.HRAppLoginPage;
import pojos.Employee;
import pojos.Job;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HRApiSteps {
    Response response;
    WebDriver driver= Driver.getDriver();
    HRAppLoginPage loginPage=new HRAppLoginPage();
    HRAppHomePage homePage=new HRAppHomePage();
    String locationId;
    Map<String,Object> locationPostData;
    List<String> firstNames=new ArrayList<>();
    String jobId;
    Job job=new Job();

    @Given("user sends GET call for employee with employeeId {int}")
    public void user_sends_GET_call_for_employee_with_employeeId(Integer employeeId) {

      response=given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept("application/json")
                .when().get("/api/employees/"+employeeId);



    }

    @Then("user validates status code is {int}")
    public void user_validates_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode,response.statusCode());

    }
    @When("user navigates to HR application")
    public void user_navigates_to_HR_application() {
        driver.get(ConfigReader.getProperty("HRAPPURL"));
    }

    @When("user logs in with username {string} password {string}")
    public void user_logs_in_with_username_password(String username, String password) {
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.login.click();

    }

    @When("user search for employee with empoloyeeId {int}")
    public void user_search_for_employee_with_empoloyeeId(Integer employeeID) throws InterruptedException {
        homePage.searchBox.sendKeys(employeeID+"");
        Thread.sleep(3000);
        driver.findElement(By.id("searchButton")).click();

    }

    @Then("validates for employee data from UI matches with API response")
    public void validates_for_employee_data_from_UI_matches_with_API_response() {
        String uiFirstName=driver.findElement(By.xpath("//td[2]")).getText();
        String uiLastName=driver.findElement(By.xpath("//td[3]")).getText();
        String uiDepartmentName=driver.findElement(By.xpath("//td[4]")).getText();

        String apiFirsName=response.jsonPath().getString("firstName");
        String apiLastName=response.jsonPath().getString("lastName");
        String apiDepartmentName=response.jsonPath().getString("department.departmentName");

        Assert.assertEquals(apiFirsName,uiFirstName);
        Assert.assertEquals(apiLastName,uiLastName);
        Assert.assertEquals(apiDepartmentName,uiDepartmentName);
    }
    @Given("user creates location with Post api call with data")
    public void user_creates_location_with_Post_api_call_with_data(io.cucumber.datatable.DataTable dataTable) {
       Map<String,Object> locationPostData= dataTable.asMap(String.class,Object.class);
        response =given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
               .and().accept("application/json")
               .and().contentType("application/json")
               .and().body(locationPostData)
               .when().post("/api/location/");
        response.then().log().all();
        locationId=response.jsonPath().getString("locationId");
    }

    @When("user gets created location")
    public void user_gets_created_location() {
        response =given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept("application/json")
                .when().get("/api/location/"+locationId);
        response.then().log().all();
    }

    @Then("user validates in get call data matches with created data")
    public void user_validates_in_get_call_data_matches_with_created_data() {
        Assert.assertEquals(locationPostData.get("locationCity").toString(),response.jsonPath().getString("locationCity"));
        Assert.assertEquals(locationPostData.get("locationCountry").toString(),response.jsonPath().getString("locationCountry"));
        Assert.assertEquals(locationPostData.get("locationState").toString(),response.jsonPath().getString("locationState"));

    }
    @When("user selects {string} department")
    public void user_selects_department(String departmentName) {
        BrowserUtils.selectDropdownByText(homePage.departments, departmentName);

    }

    @When("user stores employee data in selected department")
    public void user_stores_employee_data_in_selected_department() {
        List<WebElement> firstNameElements = driver.findElements(By.xpath("//td[2]"));

        for (WebElement element : firstNameElements) {
            firstNames.add(element.getText());

        }
    }
    @When("sends gey employees api call fot {string} department")
    public void sends_gey_employees_api_call_fot_department(String string) {
        response=given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept("application/json")
                .get("/api/departments/60/employees");
        response.then().log().all();


    }

    @Then("user validates ui data matches with api data in {string} department employee")
    public void user_validates_ui_data_matches_with_api_data_in_department_employee(String string) {

        //uiFirstName
        //apiFirstNames-> response
        List<String> apiFirstNames =response.jsonPath().getList("firstName");


        Assert.assertEquals(firstNames, apiFirstNames);
    }

    @Given("user creates job with POST call with data")
    public void user_creates_job_with_POST_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        //POst
        Map<String,Object> postLocationData= dataTable.asMap(String.class,Object.class);

        job.setJobId(postLocationData.get("jobId").toString());
        job.setTitle(postLocationData.get("title").toString());
        job.setSalary(Double.valueOf(postLocationData.get("salary").toString()));


        response=given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body(job)
                .when().post("/api/jobs/");
        jobId=job.getJobId();
    }

    @When("user gets created job")
    public void user_gets_created_job() {
        //GET
        //URL+Headers+Get method
        response=given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept("application/json")
                .when().get("/api/jobs/"+jobId);


    }

    @Then("user validates created data matches with data in get response")
    public void user_validates_created_data_matches_with_data_in_get_response() {
        //Job
        //Response
        Job responseJob=response.as(Job.class);
        Assert.assertEquals(job.toString(),responseJob.toString());
    }

    @Then("user validates UI data matches with data in DB for {string} department")
    public void user_validates_UI_data_matches_with_data_in_DB_for_department(String departmentName) throws SQLException {
        JDBCUtils.establishConnection();
        List<Map<String,Object>> dbData=JDBCUtils.runQuery("select e.employee_id,e.first_name,e.last_name, d.department_name\n" +
                "from employees e join departments d\n" +
                "on e.department_id=d.department_id\n" +
                "where d.department_name='"+departmentName+"'");
        for(int i =0;i<dbData.size();i++){
            Assert.assertEquals(dbData.get(i).get("first_name"),firstNames.get(i));
        }
    }
    @When("user clicks on edit button for employee with {int} id")
    public void user_clicks_on_edit_button_for_employee_with_id(Integer int1) {
        homePage.editEmployee1.click();

    }

    String updatedName;
    @When("user updates firstName for employee with {string}")
    public void user_updates_firstName_for_employee_with(String updatedName) throws InterruptedException {
        this.updatedName=updatedName;
        Thread.sleep(2000);
        homePage.editFirstNameBox.clear();
        homePage.editFirstNameBox.sendKeys(updatedName);
        homePage.saveButton.click();

    }

    @Then("user validates employee name is updated in")
    public void user_validates_employee_name_is_updated_in() {
        Assert.assertEquals(updatedName,homePage.employee1Name.getText());

    }

    @Then("user validated employee name is updated API get call")
    public void user_validated_employee_name_is_updated_API_get_call() {
        response=given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept(ContentType.JSON)
                .when().get("/api/employees/100");
        response.then().statusCode(200);

        //response body->Employee
        Employee employee=response.body().as(Employee.class);

        Assert.assertEquals(updatedName,employee.getFirstName());

    }
}