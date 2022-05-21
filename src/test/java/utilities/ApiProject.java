package utilities;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiProject {
    public static void main(String[] args) {
        Response postResponse = given().baseUri("https://restful-booker.herokuapp.com")
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body(" {\n" +
                        "    \"firstname\" : \"Di\",\n" +
                        "    \"lastname\" : \"Din\",\n" +
                        "    \"totalprice\" : 111,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .when().post("/booking");

        System.out.println(postResponse.statusCode());
        System.out.println(postResponse.body().asString());




        Response getResponse = given().baseUri("https://restful-booker.herokuapp.com/booking/22")
                .and().accept("application/json")
                .when().get("/booking/22");

        System.out.println(getResponse.statusCode());
        System.out.println(getResponse.body().asString());



        Response deleteResponse=given().baseUri("https://restful-booker.herokuapp.com/booking/22")
                .when().delete("/booking/22");

        System.out.println(deleteResponse.statusCode());

    }
}
