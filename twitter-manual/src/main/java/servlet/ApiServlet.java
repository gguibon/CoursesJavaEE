package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LiikeService;
import beans.Message;
import beans.MessageService;
import launch.Main;

@WebServlet(name = "api", urlPatterns = { "api" })
public class ApiServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;



	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String sessionUser = (String) session.getAttribute("user");
		
		MessageService ms = Main.messageService2;
		LiikeService ls = Main.liikeService;
		List<String> paramNames = Collections.list(request.getParameterNames());
		
		// On indique que le flux est du JSON
        response.setContentType("application/json; charset=UTF-8");
         
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("user", sessionUser);
		
		if(paramNames.contains("action")) {
			String action = request.getParameter("action");
	
	        if ("removeMessage".equals(action)) {
	        	System.out.println("remove message");
	            ms.removeMessage( request.getParameter("id") );
	            System.out.println(request.getParameter("id"));
	        } else if (action.equals("Add Message")) {
	        	Message message = new Message();
	        	message.setContent(request.getParameter("content"));
	        	message.setAuthor(sessionUser);
	        	message.setDate(LocalDateTime.now().toString());
	        	ms.addMessage(message);
	        	obj.add("message", message.toJson());
	            response.getWriter().write(obj.build().toString());
			} else if (action.equals("quit")) {
	        	try {
					Main.hibernateUtil.quit();
					System.exit(0);
				} catch (Exception e) {e.printStackTrace();}
			}else if (action.equals("like")) {
				Message msn = ms.getMessage( request.getParameter("id") );
				ls.toggleLiike(msn, msn.getId(), sessionUser);
				obj.add("messages", ms.getMessagesJson());
		        response.getWriter().write(obj.build().toString());
			}else {
//	        	System.out.println("heyyy "+action);
				obj.add("messages", ms.getMessagesJson());
		        response.getWriter().write(obj.build().toString());
	        }
		}
				
        
        
        
    }

}
