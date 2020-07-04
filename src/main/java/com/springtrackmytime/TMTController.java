package com.springtrackmytime;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.objectify.ObjectifyService;

@Controller
@ComponentScan(basePackages = {"com.springtrackmytime"})
public class TMTController {
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
		response.setHeader("Cache-Control","no-cache");
		  response.setHeader("Cache-Control","no-store");
		  response.setHeader("Pragma","no-cache");
		  response.setDateHeader ("Expires", 0);
		
		PrintWriter out = response.getWriter();		
		
		HttpSession session = request.getSession(false);
		System.out.println("111");
		if(session!=null) {
			System.out.println("112");
			String mailId = (String) session.getAttribute("mailId");
			
			if(mailId==null) {
				System.out.println("211");
				session.invalidate();
			}
				
		}
		System.out.println("11");
		if(session!=null) {
			System.out.println("12");
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
	
	@RequestMapping("/Signout")
	public void signout(HttpServletRequest request, HttpServletResponse response)  {
		
			try {
				response.sendRedirect("Signout.java");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	@RequestMapping("/ClockIn")
	public void ClockIn(HttpServletRequest request, HttpServletResponse response)  {
		
			try {
				response.sendRedirect("/ClockIn.java");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	@RequestMapping("/ClockOut")
	public void ClockOut(HttpServletRequest request, HttpServletResponse response)  {
		
			try {
				response.sendRedirect("/ClockOut.java");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	@RequestMapping("/SignUp")
	public String SignUp(HttpServletRequest request, HttpServletResponse response)  {
		
			return "SignUp";
			
	}


}
