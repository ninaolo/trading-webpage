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
    private ArrayList securities;
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
		    orders.put(security,new ArrayList<Order>());
		    trades.put(security,new ArrayList<Trade>());
		    security.setName(name);
		    //p.setText(amount);
		    securities.add(security);
		}

		rs.close();
		stmt.close();
		conn.close();
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
    	orders.get(security).add(order); // nullpointer

    	Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/ninaolo");
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "INSERT INTO orders (name, type, price, amount, uid) VALUES ('"
            + order.getSecurity().getName() + "', '"
            + order.getType() + "', "
            + order.getPrice() + ", "
            + order.getQuantity() + ", "
            + order.getUser().getID() + ")";
		stmt.executeUpdate(sql);
		stmt.close();
		conn.close();
    }

    public Security getSecurity(String name){
    	/*for(Security s : securities) {
    		if(s.getName().equals(name)){
    			return s;
    		}
    	}*/
    	return null;
    }


    public Trade getPossibleTrade(Security security){
    	/*ArrayList security_orders = getOrders(security);
    	Order buyOrder = null;
    	Order sellOrder = null;
    	Order tempOrder;
    	Trade trade;
    	for(int buy = 0;buy<security_orders.size();buy++){
    		tempOrder = security_orders.get(i);
    		if(buyOrder==null&&tempOrder.getType().equals("Buy")){
    			buyOrder = tempOrder;
    		}
    		for(int sell=0;sell<security_orders.size();sell++){
    			tempOrder = security_orders.get(i);
				if(sellOrder==null&&tempOrder.getType().equals("Sell")){
    				buyOrder = tempOrder;
    		}
    		if(possibleTrade(sellOrder,buyOrder)){
    			if(sellOrder.getQuantity()==buyOrder.getQuantity()){
    				trade = new Trade();
    				trade.setOrder(buyOrder,sellOrder);
    				security_orders.remove(Math.max(buy,sell));
    				security_orders.remove(Math.min(buy,sell));
    				return trade;
    			} 

    		} else{
    			sellOrder = null;
    		}
    		}
    		buyOrder = null;

    	}*/
    	return null;
    }

    public boolean possibleTrade(Order sell,Order buy){
    	if(sell==null||buy==null){
    		return false;
    	}
    	return ((sell.getQuantity()==buy.getQuantity())&&(sell.getPrice()==buy.getPrice()));

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
