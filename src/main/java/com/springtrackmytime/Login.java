package com.springtrackmytime;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.googlecode.objectify.ObjectifyService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setHeader("Cache-Control","no-cache");
		  response.setHeader("Cache-Control","no-store");
		  response.setHeader("Pragma","no-cache");
		  response.setDateHeader ("Expires", 0);
		
		PrintWriter out = response.getWriter();		
		
		HttpSession session = request.getSession(false);
		System.out.println("1");
		if(session!=null) {
			System.out.println("2");
			out.println("User already Login");
			response.sendRedirect("/Dashboard");
		}
		
		
		else {
			
			StringBuilder stringBuilder = new StringBuilder();
			Scanner scanner = new Scanner(request.getInputStream());
			while (scanner.hasNextLine()) {
			stringBuilder.append(""+scanner.nextLine()+"\n");
			}
			String body = stringBuilder.toString();
			
			ObjectMapper mapper = new ObjectMapper();
			
			HashMap<String, String> map = mapper.readValue(body, HashMap.class);
			
		String mailId = map.get("mailId");
		String password = map.get("password");

		System.out.println(mailId + " " + password);

		UserData user = ObjectifyService.ofy().load().type(UserData.class).filter("mailId", mailId).first()
				.now();

		System.out.println("Hi " + user);

		if (user == null) {
			response.setStatus(400);
			out.print("<font color = 'red'>Mail Id does not exist, Please signup</font>");
		}

		else if (user.getMailId().equals(mailId)) {
			if (user.getPassword().equals(password)) {

//				response.sendRedirect("index.html");
				session = request.getSession();
				session.setAttribute("mailId", mailId);
				session.setAttribute("lastEntry", user.getLastEntry());
				session.setAttribute("userId",user.getId());
				session.setAttribute("clockin",false);
				out.print("Login Successful");
				System.out.println(mailId+" "+ session.getAttribute(mailId));
				response.sendRedirect("/Dashboard");
				return;

			}

			else {
				response.setStatus(400);
				out.print("<font color = 'red'>Invalid Password</font>");

			}
		}

		else {
			response.setStatus(400);
			out.print("<font color = 'red'>Invalid Mail Id </font>");
		}
	}
	}
	}

