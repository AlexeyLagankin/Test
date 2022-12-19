package com.example.petstore;


import com.example.petstore.model.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InnoTechAPITest {

    @Test(dataProvider="correctUserDataProvider")
    public void givenUrl_whenSuccessOnGetsResponseAndUserCreated_thenCorrect(User testUser) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("body", testUser);

        RequestSpecification request1 = RestAssured.given();
        request1.contentType(ContentType.JSON);
        request1.body(testUser, ObjectMapperType.JACKSON_2);
        request1.post("http://localhost:8085/user")
                .then().assertThat().statusCode(200)
                .log().all();

        testUser.setPassword("222222222");
        requestBody = new JSONObject();
        requestBody.put("body", testUser);

        RequestSpecification request2 = RestAssured.given();
        request2.contentType(ContentType.JSON);
        request2.body(testUser, ObjectMapperType.JACKSON_2);
        request2.put("http://localhost:8085/user/" + testUser.getUsername())
                .then().assertThat().statusCode(200)
                .log().all();

        RequestSpecification request3 = RestAssured.given();
        request3.contentType(ContentType.JSON);
        request3.body(testUser, ObjectMapperType.JACKSON_2);
        request3.get("http://localhost:8085/user/" + testUser.getUsername())
                .then().assertThat().statusCode(200)
                .log().all();


        RequestSpecification request4 = RestAssured.given();
        request4.contentType(ContentType.JSON);
        request4.body(testUser, ObjectMapperType.JACKSON_2);
        request4.delete("http://localhost:8085/user/" + testUser.getUsername())
                .then().assertThat().statusCode(200)
                .log().all();
    }

    @DataProvider(name="correctUserDataProvider")
    public Object[][] correctUserDataProvider() {
        String phone = "+71111111111";
        String password = "1111111111";
        String firstname = "Alexey";
        String lastname = "Lagankin";
        String email = firstname + "." + lastname + "@gmail.com";

        final User testUser = new User(firstname, firstname, lastname, email, password, phone);

        Object[][] toReturn = {{testUser}};
        return toReturn;
    }

}
