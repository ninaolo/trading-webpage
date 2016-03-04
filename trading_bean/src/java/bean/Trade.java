package bean;
import java.util.Date;

public class Trade {

    private Order order;
    private Date tradeDate;

    public Trade() {
       this.tradeDate = new Date();
    }

    // Setters
    public void setOrder(Order order) {
        this.order = order;
    }

    // Getters

    public Security getSecurity() {
        return this.order.getSecurity();
    }

    public int getQuantity() {
        return this.order.getQuantity();
    }

    public int getPrice() {
        return this.order.getPrice();
    }

    public User getUser() {
        return this.order.getUser();
    }

    public Date getDate() {
        return this.tradeDate;
    }

    public String getSecurityType() {
        return this.order.getSecurity().getType();
    }

    public String getOrderType() {
        return this.order.getType();
    }
         
}
