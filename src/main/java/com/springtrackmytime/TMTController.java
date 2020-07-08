//package com.springtrackmytime;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Scanner;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.codehaus.jackson.map.ObjectMapper;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.googlecode.objectify.ObjectifyService;
//
//@Controller
//@ComponentScan(basePackages = {"com.springtrackmytime"})
//public class TMTController {
//	
//	
//	
//	@RequestMapping("/login")
//	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException  {
//		
//		
//			
//	}
//	
//	@RequestMapping("/Signout")
//	public void signout(HttpServletRequest request, HttpServletResponse response)  {
//		
//			try {
//				response.sendRedirect("Signout.java");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//	}
//	
//	@RequestMapping("/ClockIn")
//	public void ClockIn(HttpServletRequest request, HttpServletResponse response)  {
//		
//			try {
//				response.sendRedirect("/ClockIn.java");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//	}
//	
//	@RequestMapping("/ClockOut")
//	public void ClockOut(HttpServletRequest request, HttpServletResponse response)  {
//		
//			try {
//				response.sendRedirect("/ClockOut.java");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//	}
//	
//	@RequestMapping("/SignUp")
//	public String SignUp(HttpServletRequest request, HttpServletResponse response)  {
//		
//		response.setHeader("Cache-Control","no-cache");
//		  response.setHeader("Cache-Control","no-store");
//		  response.setHeader("Pragma","no-cache");
//		  response.setDateHeader ("Expires", 0);
//		  
//		PrintWriter out = response.getWriter();
//		
//		StringBuilder stringBuilder = new StringBuilder();
//		Scanner scanner = new Scanner(request.getInputStream());
//		while (scanner.hasNextLine()) {
//		stringBuilder.append(""+scanner.nextLine()+"\n");
//		}
//		String body = stringBuilder.toString();
//		
//		ObjectMapper mapper = new ObjectMapper();
//		
//		HashMap<String, String> map = mapper.readValue(body, HashMap.class);
//		
//		String userName = map.get("userName");
//		System.out.println(userName.isEmpty());
//		String mailId = map.get("mailId");
//		String password = map.get("password");
//
////		String userName = request.getParameter("name");
////		String mailId = request.getParameter("mail");
////		System.out.println(mailId);
////		String Password = request.getParameter("pass");
//
//		System.out.println("before");
//		
////		
//		
//		UserData duplicate = ObjectifyService.ofy().load().type(UserData.class).filter("mailId",mailId).first().now();
//		System.out.println(duplicate);
//
//		if (duplicate != null) {
//			response.setStatus(400);					
//			out.print("<font color='red'>*Mail ID already exists</font>");	
//			
//		}
//		
//		else if (userName.isEmpty()) {
//			System.out.println("after");
//			
//			response.setStatus(400);
//			System.out.println("response got");
//			out.print("<font color='red'>*User name should not be empty</font>");
//		}
//		
//		else if (validate(mailId)==false) {
//			response.setStatus(400);
//			out.print("<font color='red'>*invalid mail format</font>");			
//		}
//
//
//		else if (password.length() < 8) {
//			response.setStatus(400);
//			out.print("<font color='red'>*Password has not met the criteria</font>");
//		}
//
//		else {
//			
//			UserData user = new UserData(userName, mailId, password);
//
//			ObjectifyService.ofy().save().entity(user);
//			
////			HttpSession session = request.getSession();
////			session.setAttribute("mailId", mailId);
////			session.setAttribute("lastEntry", user.getLastEntry());
////			session.setAttribute("userId",user.getId());
////			session.setAttribute("clockin",false);
////			
//
////			context.setAttribute("Logs", save);
//
////			logs.put(userName, Password);
//			out.print("<font color='green'>Account Created Successfully, Please Login</font>");
//			
//			response.sendRedirect("Jsp/Login.jsp");
//
////			RequestDispatcher rs = request.getRequestDispatcher("TMT.html");
////			rs.forward(request, response);
//			
////					}
//	}
//			
//	}
//
//
//}
