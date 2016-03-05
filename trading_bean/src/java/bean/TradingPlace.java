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
    private ArrayList users;
    private HashMap<Security,ArrayList<Order>> orders;
    private HashMap<Security,ArrayList<Trade>> trades;


    public TradingPlace() throws SQLException, NamingException {
		orders = HashMap<Security,ArrayList<Order>>();
		trades = HashMap<Security,ArrayList<Trade>>();
		securities = new ArrayList();
		users = new ArrayList();

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
		    orders.add(security,new ArrayList<Order>());
		    trades.add(security,new ArrayList<Trade>());
		    security.setName(name);
		    //p.setText(amount);
		    securities.add(security);
		}






		rs.close();
		stmt.close();
		conn.close();
    }


    public void addUser(User user){
    	users.add(user);
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
    	orders.get(security).add(order);

    	Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/ninaolo");
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "";
		stmt.executeUpdate(sql);
		stmt.close();
		conn.close();
    }

    public Security getSecurity(String name){
    	for(Security s:securities){
    		if(s.name.equals(name)){
    			return s;
    		}
    	}
    	return null;
    }




    public Trade getPossibleTrade(Security security){
    	ArrayList security_orders = getOrders(security);
    	Order buyOrder = null;
    	Order sellOrder = null;
    	Order tempOrder;
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
    			///CONTINUE HERE TO TIIREED TO THIIINK
    		}
    		}

    	}
    }

    public boolean possibleTrade(Order sell,Order buy){
    	if(sell==null||buy==null){
    		return false;
    	}
    	return ((sell.getQuantity()>=buy.getQuantity())&&(sell.getPrice()==buy.getPrice()));

    }








    public void executeOrder(Order order) throws SQLException, NamingException {
    	orders.add(order);
    	Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/ninaolo");
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "";
		stmt.executeUpdate(sql);
		stmt.close();
		conn.close();
    }




    public ArrayList getSecurities() {
		return securities;
    }


    public ArrayList getOrders(Security security){
    	return orders.get(security);
    }
    
       
}
