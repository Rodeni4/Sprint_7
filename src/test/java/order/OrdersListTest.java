package order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;


public class OrdersListTest {
    private OrderClient client;
    private OrderAssertions orderAssertions;

    @Before
    public void setUp() {
        client = new OrderClient();
        orderAssertions = new OrderAssertions();
    }
    @Test
    @DisplayName("Список заказов")
    @Description("в тело ответа возвращается список заказов")
    public void ordersList() {
        ValidatableResponse creationResponse = client.ordersListCreate();
        orderAssertions.assertCreatedSuccessfullyOrdersList(creationResponse);
    }
}
