import java.io.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.ArrayList;

public class TradeController extends HttpServlet {
    
    public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws IOException{
	
	PrintWriter out = response.getWriter();
	ServletContext sc = getServletContext();
	try{
	    if(sc.getAttribute("forum")==null){
		sc.setAttribute("forum", new bean.Forum());
	    }
	}
		catch(SQLException e){		
	    out.println(e.getMessage());
	    }
	
		catch(NamingException ne){		
	    out.println(ne.getMessage());
		}

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
	
	
	
	if(request.getParameter("email")!=null){
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
	
	if(request.getParameter("text")!=null){
	    bean.User u = (bean.User)session.getAttribute("user");
	    bean.Forum f = (bean.Forum)sc.getAttribute("forum");
	    bean.Post p = new bean.Post();
	    p.setText(request.getParameter("text"));
	    p.setNickname(u.getNickname());
	    try{
		f.addPost(p);
		RequestDispatcher rd = sc.getRequestDispatcher("/Forum_view.jsp");

		rd.forward(request, response);
	    }
	    catch(ServletException e1){
		out.println(e1.getMessage());
	    }
	    catch(SQLException e){
		out.println(e.getMessage());
	    }
	    catch(NamingException ne){
		out.println(ne.getMessage());
	    }
	}
	out.close();
    }
}
