package courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class LoginCourierTest {
    private CourierClient client;
    private CourierAssertions courierAssertions;
    private Courier courier;
    private CourierData courierData;
    private int courierId;

    @Before
    public void setUp() {
        client = new CourierClient();
        courierAssertions = new CourierAssertions();
        courier = new CourierGenerator().randomCourier();
        client.createCourier(courier);
    }

    @Test
    @DisplayName("Авторизация зарегистрированного курьера")
    @Description("курьер может авторизоваться")
    public void authorizationCourier() {
        courierData = CourierData.from(courier);
        ValidatableResponse authorizationResponse = client.loginCourier(courierData);
         courierAssertions.assertSuccessfullyLoginCourier(authorizationResponse);
    }

    @Test
    @DisplayName("Авторизация курьера, получение id")
    @Description("Успешный запрос возвращает id")
    public void authorizationCourierReturnId() {
        courierData = CourierData.from(courier);
        ValidatableResponse authorizationResponse = client.loginCourier(courierData);
        courierId = courierAssertions.assertSuccessfullyLoginCourierReturnId(authorizationResponse);
        assert courierId > 0;
    }

    @Test
    @DisplayName("Авторизация не зарегистрированного курьера")
    @Description("Запрос возвращает ошибку: Учетная запись не найдена")
    public void authorizationAnUnregisteredCourier() {
        courier = new CourierGenerator().randomCourier();
        courierData = CourierData.from(courier);
        ValidatableResponse authorizationResponse = client.loginCourier(courierData);
        courierAssertions.assertNoCreatedCourier(authorizationResponse);
    }

    @Test
    @DisplayName("Авторизация зарегистрированного курьера без логина")
    @Description("Запрос возвращает ошибку: Недостаточно данных для входа")
    public void noAuthorizationCourierNoLogin() {
        courierData = new CourierData(null, courier.getPassword());
        ValidatableResponse authorizationResponse = client.loginCourier(courierData);
        courierAssertions.assertNoAuthorizationLoginCourier(authorizationResponse);
    }

    @Test
    @DisplayName("Авторизация зарегистрированного курьера без пароля")
    @Description("Запрос возвращает ошибку: Недостаточно данных для входа")
    public void  noAuthorizationCourierNoPassword() {
        courierData = new CourierData(courier.getLogin(), null);
        ValidatableResponse authorizationResponse = client.loginCourier(courierData);
        courierAssertions.assertNoAuthorizationLoginCourier(authorizationResponse);
    }

    @Test
    @DisplayName("Авторизация зарегистрированного курьера с неправильным логином")
    @Description("Запрос возвращает ошибку: Учетная запись не найдена")
    public void noAuthorizationCourierIncorrectLogin() {
        courierData = new CourierData("abracadabra", courier.getPassword());
        ValidatableResponse authorizationResponse = client.loginCourier(courierData);
        courierAssertions.assertNoAuthorizationCourierIncorrect(authorizationResponse);
    }

    @Test
    @DisplayName("Авторизация зарегистрированного курьера с неправильнымп аролем")
    @Description("Запрос возвращает ошибку: Учетная запись не найдена")
    public void noAuthorizationCourierIncorrectPassword() {
        courierData = new CourierData(courier.getLogin(), "abracadabra");
        ValidatableResponse authorizationResponse = client.loginCourier(courierData);
        courierAssertions.assertNoAuthorizationCourierIncorrect(authorizationResponse);
    }
    @After
    public void deleteCourier() {
        if (courierId != 0) {
            client.deleteCourier(courierId);
        }
    }
}
