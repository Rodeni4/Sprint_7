package courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CreatingCourierTest {
    protected final CourierGenerator generator = new CourierGenerator();
    private Courier courier;
    private  CourierClient client;
    private  CourierAssertions courierAssertions;
    private  Courier identicalCourier;
    private int courierId;

    @Before
    public void setUp() {
        client = new CourierClient();
        courierAssertions = new CourierAssertions();
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Курьера можно создать, возвращает 201 код ответа, возвращает ok: true")
    public void creatingCourier() {
        courier = generator.randomCourier();
        ValidatableResponse creationResponse = client.createCourier(courier);
        courierAssertions.assertCreatedSuccessfully(creationResponse);

    }

    @Test
    @DisplayName("Создание курьера без поля логин")
    @Description("Запрос возвращает ошибку: Недостаточно данных для создания учетной записи")
    public void creatingCourierNoFieldLogin() {
        courier = generator.randomCourierNoFieldLogin();
        ValidatableResponse creationResponse = client.createCourier(courier);
        courierAssertions.assertCreationCourierFieldFailed(creationResponse);
    }

    @Test
    @DisplayName("Создание курьера без поля пароль")
    @Description("Запрос возвращает ошибку: Недостаточно данных для создания учетной записи")
    public void creatingCourierNoFieldPassword() {
        courier = generator.randomCourierNoFieldPassword();
        ValidatableResponse creationResponse = client.createCourier(courier);
        courierAssertions.assertCreationCourierFieldFailed(creationResponse);
    }

    @Test
    @DisplayName("Создание курьера без поля имя")
    @Description("Курьера можно создать без поля имя")
    public void  creatingCourierNoFieldFirstName() {
        courier = generator.randomCourierNoFieldFirstName();
        ValidatableResponse creationResponse = client.createCourier(courier);
        courierAssertions.assertCreatedSuccessfully(creationResponse);
    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    @Description("Запрос возвращает ошибку: Этот логин уже используется. Попробуйте другой.")
    public void creatingTwoIdenticalCouriers() {
        courier = generator.randomCourier();
        client.createCourier(courier);
        identicalCourier = courier;
        ValidatableResponse creationResponse = client.createCourier(identicalCourier);
        courierAssertions.assertCreatingCourierRecurringLogin(creationResponse);
    }

    @Test
    @DisplayName("Создание курьера с повторяющимся логином:")
    @Description("Запрос возвращает ошибку: Этот логин уже используется. Попробуйте другой.")
    public void creatingCourierRecurringLogin() {
        courier = generator.randomCourier();
        client.createCourier(courier);
        identicalCourier = new Courier(courier.getLogin(), "1234",  "saske");
        ValidatableResponse creationResponse = client.createCourier(identicalCourier);
        courierAssertions.assertCreatingCourierRecurringLogin(creationResponse);
    }

    @After
    public void deleteCourier() {
        if (courierId != 0) {
            client.deleteCourier(courierId);
        }
    }
}
