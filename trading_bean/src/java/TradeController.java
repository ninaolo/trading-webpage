import java.io.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class TradeController extends HttpServlet {

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		ServletContext sc = getServletContext();

		// SET VARIABLES

		try{
			if(sc.getAttribute("trading_place") == null) {
				sc.setAttribute("trading_place", new bean.TradingPlace());
			}
		} catch(SQLException e) {		
			out.println(e.getMessage());
		} catch(NamingException ne) {		
			out.println(ne.getMessage());
		}


		// NEW SESSION

		HttpSession session = request.getSession();
		
		if(session.isNew()){
			session.setAttribute("user", new bean.User());

			RequestDispatcher rd = sc.getRequestDispatcher("/Forum_index.html");
			try{
				rd.forward(request, response);
			}
			catch(ServletException e){
				out.println(e.getMessage());
			}
		}
		
		if(request.getParameter("email")!=null) {
			bean.User u = (bean.User)session.getAttribute("user");
			u.setNickname(request.getParameter("nickname"));
			u.setEmail(request.getParameter("email"));
			RequestDispatcher rd = sc.getRequestDispatcher("/Forum_view.jsp");
			try{
				rd.forward(request, response);
			}
			catch(Exception e){
				out.println(e.getMessage());
			}
		}

		// CHECK ACTIONS

		if(request.getParameter("action").equals("addSecurity") && request.getParameter("security") != "") {
			bean.User u = (bean.User)session.getAttribute("user");
			bean.TradingPlace t = (bean.TradingPlace)sc.getAttribute("trading_place");
			bean.Security s = new bean.Security();
			s.setName(request.getParameter("security"));
			s.setType("stock");

			try {
				t.addSecurity(s);
			} catch(SQLException e) {
				out.println(e.getMessage());
			} catch(NamingException ne){		
				out.println(ne.getMessage());
			}

			RequestDispatcher rd = sc.getRequestDispatcher("/Forum_view.jsp");
			try{
				rd.forward(request, response);
			}
			catch(Exception e){
				out.println(e.getMessage());
			}

		}
		
		if(request.getParameter("action").equals("addOrder") && request.getParameter("price") != "" && request.getParameter("amount") != "") {
		    // Kod för att lägga en köp eller säljorder
		    // samt eventuellt skapa en trade
			String type = request.getParameter("buyOrSell");
			String securityName = request.getParameter("security");
			int price = Integer.parseInt(request.getParameter("price"));
			int amount = Integer.parseInt(request.getParameter("amount"));

			bean.Security s = new bean.Security();
			s.setName(securityName);

			bean.Order o = new bean.Order();
			o.setType(type);
			o.setSecurity(s);
			o.setQuantity(amount);
			o.setPrice(price);

			bean.TradingPlace t = (bean.TradingPlace)sc.getAttribute("trading_place");

			try {
				t.addOrder(o);
			} catch(SQLException e) {
				out.println(e.getMessage());
			} catch(NamingException ne){		
				out.println(ne.getMessage());
			}

			RequestDispatcher rd = sc.getRequestDispatcher("/Forum_view.jsp");
			try {
				rd.forward(request, response);
			} catch(Exception e) {
				out.println(e.getMessage());
			}

		}

		if(request.getParameter("action").equals("viewTrades")){
		    // Kod för att lägga en köp eller säljorder

			bean.TradingPlace t = (bean.TradingPlace)sc.getAttribute("trading_place");

		    //ArrayList orders = t.getOrders();
		    //sc.setAttribute("ordersForSecurity", orders);

		    RequestDispatcher rd = sc.getRequestDispatcher("/Forum_view.jsp");
			try {
				rd.forward(request, response);
			} catch(Exception e) {
				out.println(e.getMessage());
			}
		}

		out.close();


		RequestDispatcher rd = sc.getRequestDispatcher("/Forum_view.jsp");
			try{
				rd.forward(request, response);
			}
			catch(Exception e){
				out.println(e.getMessage());
			}

	}
}
