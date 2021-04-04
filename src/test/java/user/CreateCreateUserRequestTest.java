package user;

import dto.CreateUserRequestDto;
import dto.CreateUserResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateCreateUserRequestTest extends UserBaseTest {

    CreateUserRequestDto createUserRequestDto;

    @Test
    @DisplayName("Создание пользователя со всеми параметрами")
    public void checkCreateUser() {
        createUserRequestDto = CreateUserRequestDto.builder()
                .email("Al@gmail.com")
                .firstName("Ivan")
                .id(123)
                .lastName("Попов")
                .password("Password")
                .phone("89398339")
                .username("Ivan")
                .userStatus(121)
                .build();

        CreateUserResponseDto response = userService.createUser(createUserRequestDto)
                .then()
                .log().all()
                .extract().body().as(CreateUserResponseDto.class);

        Assertions.assertEquals("200", response.getCode().toString());
        Assertions.assertEquals("123", response.getMessage());
    }

    @Test
    @DisplayName("Создание пользователя только по имени")
    public void checkCreateUser1() {
        createUserRequestDto = CreateUserRequestDto.builder()
                .username("Solomon")
                .build();

        userService.createUser((createUserRequestDto))
                .then()
                .log().all()
                .statusCode(200);
    }

}
