package bean;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.servlet.ServletException;
import java.util.ArrayList;

public class Forum{
    
    private ArrayList posts;

    public Forum()throws SQLException,NamingException{
	posts = new ArrayList();
	Context initCtx = new InitialContext();
	Context envCtx = (Context) initCtx.lookup("java:comp/env");
	DataSource ds = (DataSource)envCtx.lookup("jdbc/ninaolo");
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();
	String sql = "SELECT name, amount FROM orders";
	ResultSet rs = stmt.executeQuery(sql);
	while(rs.next()){
	    String name = rs.getString("name");
	    //String amount = rs.getString("amount");
	    Post p=new Post();
	    p.setNickname(name);
	    //p.setText(amount);
	    posts.add(p);
	}
	rs.close();
	stmt.close();
	conn.close();
    }
    
    
    
    public void addPost(Post p)throws SQLException,NamingException{
	posts.add(p);
	Context initCtx = new InitialContext();
	Context envCtx = (Context) initCtx.lookup("java:comp/env");
	DataSource ds = (DataSource)envCtx.lookup("jdbc/ninaolo");
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();
	String sql;
	String nickname=p.getNickname();
	String text=p.getText();
	//sql = "INSERT INTO orders VALUES ('"+nickname+"','"+text+"')";
	//stmt.executeUpdate(sql);
	stmt.close();
	conn.close();
    }

    public ArrayList getPosts(){
	return posts;
    }
    
       
}
