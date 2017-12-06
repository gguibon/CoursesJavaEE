package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.json.*;
import javax.servlet.http.HttpSession;

@WebServlet(name = "IndexServlet", urlPatterns = { "index" })
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
//	MessageService ms = Main.messageService;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null)
//			response.sendRedirect("login.jsp");
			this.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );
		else {	
		System.out.println("session user = " + session.getAttribute("user"));
//        request.setAttribute("ms", ms.getMessages());
		this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
		}
		
    }
	

}
