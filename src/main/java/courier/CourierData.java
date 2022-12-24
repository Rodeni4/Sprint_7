package courier;

public class CourierData {
    private String login;
    private String password;

    public CourierData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierData from(Courier courier) {
        return new CourierData(courier.getLogin(), courier.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
