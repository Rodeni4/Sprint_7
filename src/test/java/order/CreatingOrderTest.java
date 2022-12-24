package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class CreatingOrderTest {
    private final String[] color;
    private OrderClient client;
    private OrderAssertions orderAssertions;
    private int track;

    public CreatingOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет самоката: {0}")
    public static Object[][] getData() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GRAY"}},
                {new String[]{"BLACK", "GRAY"}},
                {new String[]{}}
        };
    }

    @Before
    public void setUp() {
        client = new OrderClient();
        orderAssertions = new OrderAssertions();
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Тело ответа содержит track")

    public void creatingOrder() {
        Order order = new OrderGenerator().defaultOrder(color);
        ValidatableResponse creationResponse = client.orderCreate(order);
        track = orderAssertions.assertCreatedSuccessfullyOrder(creationResponse);
    }

    @After
    public void deleteTestOrder() {
        client.deleteOrder(track);
    }
}
