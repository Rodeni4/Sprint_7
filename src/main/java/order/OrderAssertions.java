package order;

import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.notNullValue;

public class OrderAssertions {

    public int assertCreatedSuccessfullyOrder(ValidatableResponse response) {
        return response.assertThat()
                .body("track", notNullValue())
                .and()
                .statusCode(201)
                .extract().path("track");
    }

    public void assertCreatedSuccessfullyOrdersList(ValidatableResponse response) {
        response.assertThat()
                .body("orders", notNullValue())
                .and()
                .statusCode(200);
    }
}
