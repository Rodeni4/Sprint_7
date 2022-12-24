package order;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient {
    public static final String BASE_URL = "http://qa-scooter.praktikum-services.ru/";
    public static final String ORDER_PATH = "/api/v1/orders";
    public static final String DELETE_ORDER_PATH = "/api/v1/orders/cancel";
    @Step("Создание заказа")
    public ValidatableResponse orderCreate(Order order) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse ordersListCreate() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .when()
                .get(ORDER_PATH)
                .then().log().all();
    }

    @Step("Удалить заказ по треку")
    public void deleteOrder(int track) {
        given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(track)
                .when()
                .put(DELETE_ORDER_PATH)
                .then();
    }
}
