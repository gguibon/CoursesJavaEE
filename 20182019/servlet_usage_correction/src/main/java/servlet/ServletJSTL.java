package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletJSTL", urlPatterns = { "/jstl" })
public class ServletJSTL extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pays = req.getParameter("pays");
		Map<String, String> nationalites = new HashMap<String, String>();
		nationalites.put("france", "française");
		nationalites.put("liban", "libanaise");
		nationalites.put("japon", "japonaise");
		
		req.setAttribute("nationalite", nationalites.get(pays));
		
		this.getServletContext().getRequestDispatcher( "/jstl.jsp" ).forward( req, resp );
		
	}

}
