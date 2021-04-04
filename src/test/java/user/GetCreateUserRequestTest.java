package user;

import dto.CreateUserRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetCreateUserRequestTest extends UserBaseTest {
    CreateUserRequestDto createUserRequestDto;

    @Test
    @DisplayName("Получение данных пользователя по имени")
    public void getUser() {
        createUserRequestDto = CreateUserRequestDto.builder()
                .id(123)
                .username("Ivan")
                .firstName("Ivan")
                .lastName("Попов")
                .email("Al@gmail.com")
                .password("Password")
                .phone("89398339")
                .userStatus(121)
                .build();

        CreateUserRequestDto response = userService.getUser("Ivan")
                .then()
                .log().all()
                .extract().body().as(CreateUserRequestDto.class);

        Assertions.assertEquals(createUserRequestDto, response);
    }

    @Test
    @DisplayName("Получение данных о несуществующем пользователе")
    public void getUser1() {
        userService.getUser("Vadim")
                .then()
                .log().all()
                .statusCode(404)
                .body("message", equalTo("User not found"));
    }
}