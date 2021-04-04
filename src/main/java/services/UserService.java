package services;


import dto.CreateUserRequestDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserService {
    public static final String BASE_URI = "https://petstore.swagger.io/v2";

    public static final String BASE_PATH = "/user";

    private RequestSpecification spec;


    public UserService() {
        spec = given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON);
    }

    public Response createUser(CreateUserRequestDto createUserRequestDto) {
        return
                given(spec)
                        .body(createUserRequestDto)
                        .when()
                        .log().all()
                        .post();
    }

    public Response getUser(String userName) {
        return
                given()
                        .baseUri(BASE_URI)
                        .basePath(BASE_PATH + "/" + userName)
                        .when().log().all()
                        .get();
    }

}
