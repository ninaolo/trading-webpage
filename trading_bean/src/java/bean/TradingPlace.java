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
		    //order.setPrice(price);
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
		//String nickname=order.getNickname();
		//String text=order.getText();
		//sql = "INSERT INTO orders VALUES ('"+nickname+"','"+text+"')";
		//stmt.executeUpdate(sql);
		stmt.close();
		conn.close();
    }



    public ArrayList getSecurities() {
		return securities;
    }
    
       
}
