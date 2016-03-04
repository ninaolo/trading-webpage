import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class TradeController extends HttpServlet{
    
    public void doGet(HttpServletRequest request, HttpServletResponse response){
	
	String message = "";

	if(request.getParameter("action").equals("addSecurity")){
	    // Kod för att addera ett slags värdepapper;
	    message = "addSecurity";
	}
	
	if(request.getParameter("action").equals("addOrder")){
	    // Kod för att lägga en köp eller säljorder
	    // samt eventuellt skapa en trade
	    message = "addOrder";
	}
	    
	if(request.getParameter("action").equals("viewTrades")){
	    // Kod för att lägga en köp eller säljorder
	    message = "viewTrades";
	}
	
	try{
	    RequestDispatcher rd =
		request.getRequestDispatcher("/trading.jsp");
	    rd.forward(request, response);
	}
	catch(ServletException e){
	    System.out.print(e.getMessage());
	}
	catch(IOException e){
	    System.out.print(e.getMessage());
	}
    }
} 
