package bean;

public class Order {

    private Security security;
    private int quantity;
    private int price;
    private User user;
    private String type;

    public Order() {
    }

    public Order(Order order, int quantity) {
        this.type = order.type;
        this.security = order.security;
        this.quantity = quantity;
        this.price = order.price;
        this.user = order.user;
    }


    @Override
    public String toString() {
        return this.security.getName() + " " + this.type + "\n" + user.getID() + ": " + user.getNickname() + " Q: " + this.quantity + " P: " + this.price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getters

    public Security getSecurity() {
        return this.security;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getPrice() {
        return this.price;
    }

    public User getUser() {
        return this.user;
    }

    public String getType() {
        return this.type;
    }

}
