package order;

public class OrderGenerator {
    public Order defaultOrder(String[] color) {
        return  new Order( "Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                "4",
                "+7 800 355 35 35",
                "5",
                "2020-06-06",
                "Saske, come back to Konoha",
                color);
    }
}
