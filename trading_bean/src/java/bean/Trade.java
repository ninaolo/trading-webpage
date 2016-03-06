package bean;
import java.util.Date;

public class Trade {

    private Order buyOrder;
    private Order sellOrder;
    private Date tradeDate;
    private int quantity;

    public Trade() {
        this.tradeDate = new Date();
    }

    // Setters
    public void setOrders(Order buyOrder, Order sellOrder) {
        this.buyOrder = buyOrder;
        this.sellOrder = sellOrder;
    }

    public Order getRest(){
        if(this.buyOrder.getQuantity()==this.sellOrder.getQuantity()){
            return null;
        }
        else{
            Order order;
            int quantity = this.buyOrder.getQuantity()-this.sellOrder.getQuantity();
            if(quantity>0){
                order = new Order(this.buyOrder,quantity);
            } else{
                order = new Order(this.sellOrder,-quantity);
            }
            return order;
        }
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getters

    public Security getSecurity() {
        return this.buyOrder.getSecurity();
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getPrice() {
        return this.buyOrder.getPrice();
    }

    public User getBuyUser() {
        return this.buyOrder.getUser();
    }

    public User getSellUser() {
        return this.sellOrder.getUser();
    }

    public Date getDate() {
        return this.tradeDate;
    }
         
}
