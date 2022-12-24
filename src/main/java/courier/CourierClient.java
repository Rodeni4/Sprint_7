package courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient {
    public static final String BASE_URL = "http://qa-scooter.praktikum-services.ru/";
    private static final String CREATE_COURIER_PATH = "/api/v1/courier";
    public static final String LOGIN_COURIER_PATH = "/api/v1/courier/login";
    public static final String DELETE_COURIER_PATH = "/api/v1/courier/";

    @Step("Создание курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(courier)
                .when()
                .post(CREATE_COURIER_PATH)
                .then().log().all();
    }

    @Step("Логин курьера")
    public ValidatableResponse loginCourier(CourierData courierData) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(courierData)
                .when()
                .post(LOGIN_COURIER_PATH)
                .then().log().all();
    }

    @Step("Удалить курьера по id")
    public void deleteCourier(int courierId) {
        given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .when()
                .delete(DELETE_COURIER_PATH + courierId)
                .then();
    }
}
