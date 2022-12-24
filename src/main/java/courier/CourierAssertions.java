package courier;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierAssertions {
    public void assertCreatedSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .body("ok", equalTo(true))
                .and()
                .statusCode(201);
    }

    public void assertCreationCourierFieldFailed(ValidatableResponse response) {
        response.assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }

    public void assertCreatingCourierRecurringLogin(ValidatableResponse response) {
        response.assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

    public void assertSuccessfullyLoginCourier(ValidatableResponse response) {
        response.assertThat()
                .body("id", notNullValue())
                .and()
                .statusCode(200);
    }

    public int assertSuccessfullyLoginCourierReturnId(ValidatableResponse response) {
        return  response.assertThat()
                .body("id", notNullValue())
                .and()
                .statusCode(200)
                .extract().path("id");
    }

    public void  assertNoCreatedCourier(ValidatableResponse response) {
        response.assertThat()
                .body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

    public void assertNoAuthorizationLoginCourier(ValidatableResponse response) {
        response.assertThat()
                .body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    public void assertNoAuthorizationCourierIncorrect(ValidatableResponse response) {
        response.assertThat()
                .body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }
}
