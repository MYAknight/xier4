package bean;

public class user {

    String user_name;
    String password;
    String phone;

    @Override
    public String toString() {
        return "user{" +
                "user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public user() {
    }

    public user(String user_name, String password, String phone) {
        this.user_name = user_name;
        this.password = password;
        this.phone = phone;
    }
}
