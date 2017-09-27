package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletCondition", urlPatterns = { "/condition" })
public class ServletCondition extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, String[]> parameters = req.getParameterMap();
		List<String> params = new ArrayList<String>(parameters.keySet());
		
		if (params.contains("prenom")) {

			String paramPrenom = req.getParameter("prenom");
			if (paramPrenom.length() != 0) {
				this.getServletContext().getRequestDispatcher("/seconde.jsp").forward(req, resp);
			} else {
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
			}

		} else {
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}

}
