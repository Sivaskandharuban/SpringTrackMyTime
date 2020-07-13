package com.springtrackmytime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

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


//@WebServlet("/Dashboard")
@Controller
@ComponentScan(basePackages = {"com.springtrackmytime"})
public class Dashboard {
	private static final long serialVersionUID = 1L;    
       
	@RequestMapping("/Dashboard")
	protected void MainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control","no-cache");
		  response.setHeader("Cache-Control","no-store");
		  response.setHeader("Pragma","no-cache");
		  response.setDateHeader ("Expires", 0);
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		PrintWriter out = response.getWriter();		
		
		HttpSession session = request.getSession(false);
		try {
		if(session==null) {
			System.out.println("Unsuccess");
			out.println("Please Login");
			response.sendRedirect("Login.jsp");
		}		
		
		
		else {		
			
//			StringBuilder stringBuilder = new StringBuilder();
//			Scanner scanner = new Scanner(request.getInputStream());
//			while (scanner.hasNextLine()) {
//			stringBuilder.append(""+scanner.nextLine()+"\n");
//			}
//			String body = stringBuilder.toString();
//			
//			ObjectMapper mapper = new ObjectMapper();
//			
//			HashMap<String, String> map = mapper.readValue(body, HashMap.class);
//			
//		String mailId = map.get("mailId");
//		String password = map.get("password");
						
			System.out.println("2");
			System.out.println(session.getAttribute("mailId"));
			String mailId = session.getAttribute("mailId").toString();
			System.out.println(mailId);
//			
			List<TimeData> list = ObjectifyService.ofy().load().type(TimeData.class).filter("mailId", mailId).order("-startTime").list();
			
//			 Collections.sort(list, Collections.reverseOrder());
			
			String result2 = "";
			
			String lastDate = ""; 
			
			if(list!=null) {
			for(TimeData entry : list) {
				
				String date = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (entry.getStartTime()));
				
				if(!lastDate.equals(date)) {
					lastDate=date;
				result2 +=						
						"<tr><td><u>"+date +"</u></td></tr>";
				}
						
						if(entry.getEndTime()!=0) {
					result2 += "<tr><td>"+ "Add Task Description" + "</td><td>" + "Project Working" + "</td><td>" + sdf.format(new Date(entry.getStartTime())) +"</td><td>" 
			+ sdf.format(new Date(entry.getEndTime()))+"</td><td>"+
			
			((entry.getEndTime()- entry.getStartTime())/1000/60/60<10?"0"+(entry.getEndTime()- entry.getStartTime())/1000/60/60 : (entry.getEndTime()- entry.getStartTime())/1000/60/60) +"h " +
			((entry.getEndTime()- entry.getStartTime())/1000/60<10?"0"+(entry.getEndTime()- entry.getStartTime())/1000/60 : (entry.getEndTime()- entry.getStartTime())/1000/60)+"m" + "</td></tr>";
			System.out.println("Time displayed");
			System.out.println(entry.getEndTime());
						}
						else {
							result2 += "<tr><td>"+ "Add Task Description" + "</td><td>" + "Project Working" + "</td><td>" + sdf.format(new Date(entry.getStartTime())) +"</td><td>" 
									+ "Ongoing"+"</td><td>"+
									
									"</td></tr>";
									System.out.println("Time displayed");
									System.out.println(entry.getEndTime());
						}
			}
			}
			
			String result = "<!DOCTYPE html>\r\n" + 
					"\r\n" + 
					"<html lang = 'en-US'>\r\n" + 
					"\r\n" + 
					"<head>\r\n" + 
					"    <link rel=\"stylesheet\" href=\"Style-TMT.css\">\r\n" + 
					"</head>\r\n" + 
					"\r\n" + 
					"<body class=\"BodyStyle\">\r\n" + 
					"<p id = \"message\" style = \"text-align : center\"></p>\r\n" + 
					"    <div>\r\n" + 
					"<aside class=\"sidenav\">\r\n" + 
					"    <div class=\"side-nav-content\">\r\n" + 
					"        <h2>Track My Time</h2>\r\n" + 
					"        <img src=\"dummy-profile-pic-300x300.jpg\" />\r\n" + 
					"        <label class=\"switch\">\r\n" + 
					"            <input type=\"checkbox\" id = \"check\" onclick=\"set()\">\r\n" + 
					"            <span class=\"slider round\"></span>\r\n" + 
					"          </label>\r\n" + 
					"          \r\n" + 
					"          <div id = \"Signout\">\r\n" + 
					"<input type = \"submit\" value = \"Sign out\" onclick =\" signOut()\">\r\n" + 
					"</form>\r\n" + 
					"</div>\r\n" + 
					"         \r\n" + 
					"        <!-- <div class=\"toggle-btn\"> -->\r\n" + 
					"            <!-- <div class=\"inner-circle\">\r\n" + 
					"              <span>Clockin</span>\r\n" + 
					"            </div> -->\r\n" + 
					"            \r\n" + 
					"        <!-- </div> -->\r\n" + 
					"    </div>\r\n" + 
					"</aside>\r\n" + 
					"\r\n" + 
					" \r\n" + 
					"\r\n" + 
					"<div>\r\n" + 
					"    <h1 class=\"Style\">Welcome to TMT</h1>\r\n" + 
					"    <select name=\"Timezone\" id=\"Timezone\" style= \"float: right; display: block\">\r\n" + 
					"<option value=\"Time Zone\">Default Time Zone(UTC)</option>\r\n" + 
					"    <option value=\"Time Zone\">Indian Time Zone</option>\r\n" + 
					"    <option value=\"Time Zone\">Detect Time Zone</option>\r\n" + 
					"    </select>\r\n" + 
					"    <label for=\"Timezone\" style =\"color: black; text-align: right; float: right; padding-right: 13px;\">\r\n" + 
					"        Choose Timezone\r\n" + 
					"    </label>\r\n" + 
					"</div>\r\n" + 
					"\r\n" + 
					"<div class=\"Log\">\r\n" + 
					"<section >\r\n" + 
					"    <table id = \"clockTable\">\r\n" + 
					"        \r\n" + 
//					"        <tr> \r\n" + 
//					"        <td>            \r\n" + 
//					"            <select name=\"Date\" id=\"Date\">\r\n" + 
//					"                <option value=\"Current_Week\">Select date</option>\r\n" + 
//					"                <option value=\"Last_Week\">Last Week</option>\r\n" + 
//					"                </select>  \r\n" + 
//					"                </td>\r\n" + 
//					"                \r\n" + 
//					"            </tr>\r\n" + 
//					"            \r\n" +
//					"            </tr>\r\n" + 
					"            \r\n" + 
					"\r\n" + 
					"            <tr>\r\n" + 
					"                <td>\r\n" + 
					"                Task description\r\n" + 
					"            </td>\r\n" + 
					"            <td>\r\n" + 
					"                Project Working\r\n" + 
					"            </td>\r\n" + 
					"\r\n" + 
					"            <td>\r\n" + 
					"                Time from \r\n" + 
					"            </td>\r\n" + 
					"\r\n" + 
					"            <td>\r\n" + 
					"                Time to \r\n" + 
					"            </td>\r\n" + 
					"\r\n" + 
					"            <td>\r\n" + 
					"                Total working hours\r\n" + 
					"            </td>\r\n" + 
					"            </tr>\r\n" + 
					"            \r\n" + 
					"             <tr id = \"initial\">\r\n" + 
					"                <td>\r\n" + 
					"                \r\n" + 
					"            </td>\r\n" + 
					"            <td>\r\n" + 
					"                \r\n" + 
					"            </td>\r\n" + 
					"\r\n" + 
					"            <td id = \"timeStarted\">\r\n" + 
					"                \r\n" + 
					"            </td>\r\n" + 
					"\r\n" + 
					"            <td id = \"timeEnded\">\r\n" + 
					"                \r\n" + 
					"            </td>\r\n" + 
					"\r\n" + 
					"            <td id = \"totalTime\">\r\n" + 
					"                \r\n" + 
					"            </td>\r\n" +	
					"            </tr>\r\n" + result2+	
					"            \r\n" + 
					"            \r\n" + 
					"    \r\n" + 
					"    </table>\r\n" + 
					"</section>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<script src=\"Timer.js\"></script>\r\n" + 
					"\r\n" + 
					"</body>\r\n" + 
					"</html>";
			response.setContentType("text/html");
			out.write(result);
	}
		

	}
		catch(NullPointerException e) {
			response.sendRedirect("Login.jsp");
			
	}
	}
	

}

