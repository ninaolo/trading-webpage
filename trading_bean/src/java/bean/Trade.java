package bean;
import java.util.Date;

public class Trade {

    private Order buyorder;
    private Order sellorder;
    private Date tradeDate;
    private int quantity;


    public Trade(Order buyorder,Order sellorder) {
        this.buyorder = buyorder;
        this.sellorder = sellorder;
        this.tradeDate = new Date();
    }

    // Getters

    public Security getSecurity() {
        return this.order.getSecurity();
    }



    public int getQuantity() {
        return this.quantity;
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
