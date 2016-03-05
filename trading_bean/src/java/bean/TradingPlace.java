package bean;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.ServletException;
import java.util.ArrayList;

public class TradingPlace {
    
    private ArrayList orders;
    private ArrayList trades;
    private ArrayList securities;

    public TradingPlace() throws SQLException, NamingException {
		orders = new ArrayList();
		trades = new ArrayList();
		securities = new ArrayList();

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
		rs.close();
		stmt.close();
		conn.close();
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

    public ArrayList getOrders(Security security) throws SQLException, NamingException {
    	ArrayList ordersForSecurity = new ArrayList();

    	Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/ninaolo");
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM orders WHERE name = " + security.getName();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
		    String name = rs.getString("name");
		    String type = rs.getString("type");
		    Order order = new Order();
		    order.setType(type);
		    ordersForSecurity.add(order);
		}
		rs.close();
		stmt.close();
		conn.close();
		return ordersForSecurity;	
    }
    
       
}
