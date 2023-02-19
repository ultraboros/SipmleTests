package api;

import api.Specifications;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class TestReqresIn {
    private static final String URL = "https://reqres.in/";

    @Test
    public void checkFieldsIsNotEmpty() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        Response response = RestAssured.given()
                .when()
                .get("api/users?page=2")
                .then()
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .body("support.url", notNullValue())
                .body("support.text", notNullValue())
                .extract().response();

    }

   @Test
    public void CheckInTheIdFieldOnlyDigit() {

       Specifications.installSpecifications(Specifications.requestSpecification(URL),
               Specifications.responseSpecification(200));

        Response response = RestAssured.get("api/users?page=2");

        List <Integer> ids = response.jsonPath().getList("data.id");

        Assert.assertTrue(ids.stream().allMatch(x->x.toString().matches("^[0-9]+$")));
   }

   @Test
    public void checkInTheFirstNameFieldOnlyLetters() {

       Specifications.installSpecifications(Specifications.requestSpecification(URL),
               Specifications.responseSpecification(200));

       Response response = RestAssured.get("api/users?page=2");

       List <String> firstNames = response.jsonPath().getList("data.first_name");

       Assert.assertTrue(firstNames.stream().allMatch(x->x.matches("^[a-zA-Z]+$")));
   }

   @Test
    public void checkInTheLastNameFieldOnlyLetters() {

       Specifications.installSpecifications(Specifications.requestSpecification(URL),
               Specifications.responseSpecification(200));

       Response response = RestAssured.get("api/users?page=2");

       List <String> firstNames = response.jsonPath().getList("data.last_name");

       Assert.assertTrue(firstNames.stream().allMatch(x->x.matches("^[a-zA-Z]+$")));

    }

    @Test
    public void checkEmailFieldEndsWithReqresIn() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        Response response = RestAssured.get("api/users?page=2");

        List<String> emails = response.jsonPath().getList("data.email");

        Assert.assertTrue(emails.stream().allMatch(x->x.endsWith("@reqres.in")));

    }

    @Test
    public void checkUrlInAvatarStartsWithHttpsAndEndsWithJpg() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        Response response = RestAssured.get("api/users?page=2");

        List<String> avatars = response.jsonPath().getList("data.avatar");

        Assert.assertTrue(avatars.stream().allMatch(x->x.startsWith("https://")));
        Assert.assertTrue(avatars.stream().allMatch(x->x.endsWith(".jpg")));

    }

    @Test
    public void checkUrlInSupportStartsWithHttps() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        Response response = RestAssured.get("api/users?page=2");

        List<String> avatarUrls = response.jsonPath().getList("data.avatar");

        for (int i = 0; i < avatarUrls.size(); i++) {

            Assert.assertTrue(avatarUrls.get(i).startsWith("https://"));

        }

    }

    @Test
    public void checkThePresenceOfIdInAvatarField() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        Response response = RestAssured.get("api/users?page=2");

        List<Integer> ids = response.jsonPath().getList("data.id");
        List<String> avatars = response.jsonPath().getList("data.avatar");

        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
        }

    }

    @Test
    public void checkIdInAscOrder() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        Response response = RestAssured.get("api/users?page=2");

        List<Integer> ids = response.jsonPath().getList("data.id");
        List<Integer> sortedIds = ids.stream().sorted().toList();

        Assert.assertEquals(sortedIds, ids);

    }

    @Test
    public void checkSearchByExistingId() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        Response response = RestAssured.given()
                .when()
                .get("/api/users/2")
                .then()
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .body("support.url", notNullValue())
                .body("support.text", notNullValue())
                .extract().response();

    }

    @Test
    public void checkSearchByNonExistentId() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(404));

        Response response = RestAssured.get("api/users/23");

    }


    @Test
    public void checkSuccessfulRegistration() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        Map<String, String> user = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");

        Response response = RestAssured.given()
                .body(user)
                .when()
                .post("api/register")
                .then()
                .extract().response();

        int userId = response.jsonPath().get("id");
        String userToken = response.jsonPath().get("token");

        Assert.assertEquals(4, userId);
        Assert.assertEquals("QpwL5tke4Pnpja7X4", userToken);

    }

    @Test
    public void checkUnsuccessfulRegistration() {
        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(400));

        Map <String, String> user = new HashMap<>();
        user.put("email", "sydney@fife");

        Response response = RestAssured.given()
                .body(user)
                .when()
                .post("api/register")
                .then()
                .extract().response();

        String errorMessage = response.jsonPath().get("error");

        Assert.assertEquals("Missing password", errorMessage);

    }

    @Test
    public void checkSuccessfulUpdateUser() {

        Specifications.installSpecifications(Specifications.requestSpecification(URL),
                Specifications.responseSpecification(200));

        Map<String, String> user = new HashMap<>();
        user.put("name", "morpheus");
        user.put("job", "zion resident");

        Response response = RestAssured.given()
                .body(user)
                .when()
                .put("/api/users/2")
                .then()
                .extract().response();

        String userName = response.jsonPath().get("name");
        String userJob = response.jsonPath().get("job");

        Assert.assertEquals("morpheus", userName);
        Assert.assertEquals("zion resident", userJob);
    }

}
