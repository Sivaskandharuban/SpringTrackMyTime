package com.springtrackmytime;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
//import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.objectify.ObjectifyService;

/**
 * Servlet implementation class SignUp
 */
//@WebServlet("/SignUp")
@Controller
@ComponentScan(basePackages = {"com.springtrackmytime"})
public class SignUp extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	HashMap<String, String> logs = new HashMap<String, String>();
	private Pattern pattern;
	private Matcher matcher;


	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public SignUp() {
		pattern = Pattern.compile(EMAIL_REGEX);
	}

	public boolean validate(final String email) {
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	@RequestMapping("/SignUp")
	protected void SignUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setHeader("Cache-Control","no-cache");
		  response.setHeader("Cache-Control","no-store");
		  response.setHeader("Pragma","no-cache");
		  response.setDateHeader ("Expires", 0);
		  
		PrintWriter out = response.getWriter();
		
		StringBuilder stringBuilder = new StringBuilder();
		Scanner scanner = new Scanner(request.getInputStream());
		while (scanner.hasNextLine()) {
		stringBuilder.append(""+scanner.nextLine()+"\n");
		}
		String body = stringBuilder.toString();
		
		ObjectMapper mapper = new ObjectMapper();
		
		HashMap<String, String> map = mapper.readValue(body, HashMap.class);
		
		String userName = map.get("userName");
		System.out.println(userName.isEmpty());
		String mailId = map.get("mailId");
		String password = map.get("password");

//		String userName = request.getParameter("name");
//		String mailId = request.getParameter("mail");
//		System.out.println(mailId);
//		String Password = request.getParameter("pass");

		System.out.println("before");
		
//		
		
		UserData duplicate = ObjectifyService.ofy().load().type(UserData.class).filter("mailId",mailId).first().now();
		System.out.println(duplicate);

		if (duplicate != null) {
			response.setStatus(400);					
			out.print("<font color='red'>*Mail ID already exists</font>");	
			
		}
		
		else if (userName.isEmpty()) {
			System.out.println("after");
			
			response.setStatus(400);
			System.out.println("response got");
			out.print("<font color='red'>*User name should not be empty</font>");
		}
		
		else if (validate(mailId)==false) {
			response.setStatus(400);
			out.print("<font color='red'>*invalid mail format</font>");			
		}


		else if (password.length() < 8) {
			response.setStatus(400);
			out.print("<font color='red'>*Password has not met the criteria</font>");
		}

		else {
			
			UserData user = new UserData(userName, mailId, password);
			System.out.println(userName + " " + mailId + " "+password);

			ObjectifyService.ofy().save().entity(user);
			
//			HttpSession session = request.getSession();
//			session.setAttribute("mailId", mailId);
//			session.setAttribute("lastEntry", user.getLastEntry());
//			session.setAttribute("userId",user.getId());
//			session.setAttribute("clockin",false);
//			

//			context.setAttribute("Logs", save);

//			logs.put(userName, Password);
			out.print("<font color='green'>Account Created Successfully, Please Login</font>");
			
			response.sendRedirect("Login.jsp");

//			RequestDispatcher rs = request.getRequestDispatcher("TMT.html");
//			rs.forward(request, response);
			
//					}
	}

}
}
