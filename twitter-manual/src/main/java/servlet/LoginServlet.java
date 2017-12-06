package servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import beans.UserService;
import launch.Main;

@WebServlet(name = "LoginServlet", urlPatterns = { "login" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	UserService userService = Main.userService;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {userService.checkData();} catch (Exception e) {e.printStackTrace();}
		
		List<String> paramNames = Collections.list(request.getParameterNames());
		for(String name : paramNames) {
			System.out.println(name);
		}

		
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null && userService.userExists((String)session.getAttribute("user")) ) {
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}else if(paramNames.contains("id")) {
			session.setAttribute("user", request.getParameter("id"));
			
			
			String name = request.getParameter("id");
			String password = request.getParameter("pwd");
			System.out.println(name + "\t" + password);

			if (userService.userLogin(name, password)) {
				System.out.println("Oui! " + session.getAttribute("user"));
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}else {
				this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
		
		}else {
		this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		List<String> paramNames = Collections.list(request.getParameterNames());
		
		
		if (paramNames.contains("id")) {
			
			HttpSession session = request.getSession();
			session.setAttribute("user", request.getParameter("id"));
			
			
			String name = request.getParameter("id");
			String password = request.getParameter("pwd");
			System.out.println(name + "\t" + password);

			if (userService.userLogin(name, password)) {
				System.out.println("Oui!");
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}else {
				try {
					User user = new User();
					user.setName(name);
					user.setPassword(password);
					userService.addUser(user);
				}catch(Exception e) {
					e.printStackTrace();
					this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				}
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}else {
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

}
