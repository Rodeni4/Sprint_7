package courier;

import java.util.UUID;

public class CourierGenerator {
    public Courier defaultCourier() {
        return new Courier("ninja38", "1234",  "saske");
    }
    public Courier randomCourier() {
        return new Courier("ninja" + generateString(),
                "pass" + generateString(),
                "Name" + generateString());
    }

    public Courier randomCourierNoFieldLogin() {
        return new Courier(null,
                "pass" + generateString(),
                "Name" + generateString());
    }

    public Courier randomCourierNoFieldPassword() {
        return new Courier("ninja" + generateString(),
                null,
                "Name" + generateString());
    }

    public Courier randomCourierNoFieldFirstName() {
        return new Courier("ninja" + generateString(),
                "pass" + generateString(),
                null);
    }

    public static String generateString() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
