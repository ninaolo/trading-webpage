package bean;
import java.util.Date;

public class Trade {

    private Security security;
    private Date tradeDate;
    private int quantity;
    private User buyer;
    private User seller;
    private int price;

    public Trade() {
        this.tradeDate = new Date();
    }

    public void setParameters(int price,int quantity,User buyer,User seller,Security security){
        this.quantity = quantity;
        this.buyer = buyer;
        this.seller = seller;
        this.security = security;
        this.price = price;

    }


    @Override
    public String toString(){
        return this.security.getName()+" Q: "+this.quantity+" P: "+this.price+"\n"+"Buyer: "+buyer.getNickname()+"  Seller: "+buyer.getNickname();

    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getBuyer(){
        return buyer;
    }

    public User getSeller(){
        return seller;
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

    public Date getDate() {
        return this.tradeDate;
    }
         
}
