package utilities;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiTest {
    public static void main(String[] args) {
        /*
        GET employee with the employee id 100

        Request:
        1.URL
        2.Method type "GET"
        3.Headers- Accept="application/json"

        Response:
        1.Status code
        2.Body

         */

        Response response = given().baseUri("https://qeenv-apihr-arslan.herokuapp.com")
                .and().accept("application/json")
                .when().get("/api/employees/100");

        System.out.println(response.statusCode());
        System.out.println(response.body().asString());

        /*
        POST Location

         Request:
        1.URL
        2.Headers- Accept="application/json"; Content-Type="application/json"
        3.Payload
        2.Method type "POST"

        Response:
        1.Status code 201
        2.Body
         */

        Response postResponse = given().baseUri("https://qeenv-apihr-arslan.herokuapp.com")
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body("{\n" +
                        "  \"locationCity\": \"San Diego\",\n" +
                        "  \"locationCountry\": \"US\",\n" +
                        "  \"locationId\": 777,\n" +
                        "  \"locationState\": \"California\"\n" +
                        "}")
                .when().post("/api/location");

        System.out.println(postResponse.statusCode());
        System.out.println(postResponse.body().asString());


         /*
        POST Department

         Request:
        1.URL
        2.Headers- Accept="application/json"; Content-Type="application/json"
        3.Payload
        2.Method type "POST"

        Response:
        1.Status code 201
        2.Body
         */

        Response postDepartment = given().baseUri("https://qeenv-apihr-arslan.herokuapp.com")
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body("{\n" +
                        "  \"departmentId\": 999,\n" +
                        "  \"departmentName\": \"Crew Department\",\n" +
                        "  \"location\": {\n" +
                        "    \"locationCity\": \"in ut\",\n" +
                        "    \"locationCountry\": \"proident sit\",\n" +
                        "    \"locationId\": 91246028,\n" +
                        "    \"locationState\": \"quis aliqua tempor et magna\"\n" +
                        "  }\n" +
                        "}")
                .when().post("/api/departments");

        System.out.println(postDepartment.statusCode());
        System.out.println(postDepartment.body().asString());

         /*
        DELETE Department

         Request:
        1.URL
        2.Method type "DELETE"

        Response:
        1.Status code 204
         */

        Response deleteDepartment=given().baseUri("https://qeenv-apihr-arslan.herokuapp.com")
                .when().delete("/api/departments/999");

        System.out.println(deleteDepartment.statusCode());

    }
}