package bean;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.HashMap;

public class TradingPlace {
    
    //private ArrayList orders;
    //private ArrayList trades;
    private ArrayList<Security> securities;
    private HashMap<Integer, User> users;
    private Integer userIdCount = 0;
    private HashMap<Security, ArrayList<Order>> orders;
    private HashMap<Security, ArrayList<Trade>> trades;


    public TradingPlace() throws SQLException, NamingException {
		orders = new HashMap<Security,ArrayList<Order>>();
		trades = new HashMap<Security,ArrayList<Trade>>();
		securities = new ArrayList();
		users = new HashMap<Integer, User>();

		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/ninaolo");
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT id, name FROM securities";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
		    String name = rs.getString("name");
		    //String amount = rs.getString("amount");
		    Security security = new Security();
		    security.setName(name);
		    //p.setText(amount);
		    securities.add(security);
		}

        for(int i=0;i<securities.size();i++){
            Security s = securities.get(i);
            sql = "SELECT name, type, price, amount, uid FROM orders WHERE name="+"'"+s.getName()+"'";
            rs = stmt.executeQuery(sql);
            ArrayList<Order> temp = new ArrayList<Order>();
            Order tempOrder;
            while(rs.next()){
                tempOrder = new Order();
                tempOrder.setSecurity(s);
                tempOrder.setQuantity(Integer.parseInt(rs.getString("quantity")));
                tempOrder.setPrice(Integer.parseInt(rs.getString("price")));
                tempOrder.setType(rs.getString("type"));
                temp.add(tempOrder);
            }
            orders.put(s,temp);
        }

		rs.close();
		stmt.close();
		conn.close();
    }

    public static void addAllOrders(){

    }


    public void addUser(User user) {
    	userIdCount++; // Unique ID for everyone
    	users.put(userIdCount, user);
    	user.setID(userIdCount);
    }
    
    public void addSecurity(Security security) throws SQLException, NamingException {
		securities.add(security);
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/ninaolo");
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement();
		String sql;
		String name = security.getName();
		//String text=order.getText();
		sql = "INSERT INTO securities (name) VALUES ('"+ name +"')";
		stmt.executeUpdate(sql);
		stmt.close();
		conn.close();
    }

    public void addOrder(Order order) throws SQLException, NamingException {
    	Security security = order.getSecurity();

        ArrayList<Order> temp = orders.get(security);
        temp.add(order);
        orders.put(security,temp);

    	Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/ninaolo");
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "INSERT INTO orders (name, type, price, amount, uid) VALUES ('"
            + order.getSecurity().getName() + "', '"
            + order.getType() + "', "
            + order.getPrice() + ", "
            + order.getQuantity() + ")";
        //    + order.getUser().getID() + ")";
		stmt.executeUpdate(sql);
		stmt.close();
		conn.close();


        ArrayList<Trade> tempTrades = trades.get(security);
        Trade trade = getPossibleTrade(security);
        while(trade!=null){
            tempTrades.add(trade);
            trade = getPossibleTrade(security);
        }
        trades.put(security,tempTrades);
    }

    public Security getSecurity(String name){
    	for(int i = 0;i<securities.size();i++) {
            Security s = securities.get(i);
    		if(s.getName().equals(name)){
    			return s;
    		}
    	}
    	return null;
    }


    public Trade getPossibleTrade(Security security){
    	ArrayList<Order> security_orders = orders.get(security);
    	Order buyOrder = null;
    	Order sellOrder = null;
    	Order tempOrder;
    	Trade trade;
        int sell = -1;
        int buy = -1;
        for(int i=0;i<security_orders.size();i++){
            if(security_orders.get(i).getType().equals("Sell")){
                sell = i;
            }
            else{
                buy = i;
            }
        }

        if(sell==-1||buy==-1){
            return null;
        }

        buyOrder = security_orders.get(buy);
        sellOrder = security_orders.get(sell);
        trade = new Trade();
        trade.setOrders(buyOrder,sellOrder);
        Order newOrder = trade.getRest();
        security_orders.remove(Math.max(buy,sell));
        security_orders.remove(Math.min(buy,sell));
        if(newOrder!=null){
            security_orders.add(newOrder);
        }
        orders.put(security,security_orders);
        return trade;
    }


    public void executeOrder(Order order) throws SQLException, NamingException {
    	/*orders.add(order);
    	Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/ninaolo");
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "";
		stmt.executeUpdate(sql);
		stmt.close();
		conn.close();*/
    }




    public ArrayList getSecurities() {
		return securities;
    }


    public ArrayList getOrders(Security security){
    	return orders.get(security);
    }
    
       
}
