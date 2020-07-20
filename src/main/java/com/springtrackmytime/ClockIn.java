package com.springtrackmytime;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.objectify.ObjectifyService;

/**
 * Servlet implementation class TimeConversion
 */
//@WebServlet("/ClockIn")
@Controller
@ComponentScan(basePackages = {"com.springtrackmytime"})
public class ClockIn implements Serializable {
	private static final long serialVersionUID = 1L;
	
	Long id = 0L;
	boolean newUser = true;

	@RequestMapping("/ClockIn")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Cache-Control","no-cache");
		  response.setHeader("Cache-Control","no-store");
		  response.setHeader("Pragma","no-cache");
		  response.setDateHeader ("Expires", 0);
		
//		String mailId = request.getParameter("mail");
//		System.out.println(mailId);
//		Long startTime = Long.parseLong(request.getParameter("startTime"));
//		System.out.println(startTime);
//		Long endTime = Long.parseLong(request.getParameter("endTime"));
//		System.out.println(endTime);
//		
//		if(endTime==0) {
//		
//		TimeData td = new TimeData(mailId,startTime,endTime,id++);
//		
//		ObjectifyService.ofy().save().entity(td);
//		}
//		else {
//			TimeData td = new TimeData(mailId,startTime,endTime,id);
//			
//			ObjectifyService.ofy().save().entity(td);
//		}
		SimpleDateFormat sdf = new SimpleDateFormat();
		
		PrintWriter out = response.getWriter();
		
		Long startTime= System.currentTimeMillis();
		Long endTime = 0L;
		HttpSession session = request.getSession(false);
		System.out.println("New user " + newUser);
		System.out.println(session);
		System.out.println("in");
		if(newUser) {
		session.setAttribute("clockIn",false);
		newUser=false;
		System.out.println("out");
		}
		
		
		
	if(session==null){
		response.setStatus(400);
	out.println("session not existed please login");
	response.sendRedirect("Login.jsp");
	}
	
	else if(session.getAttribute("clockIn")==null) {
		String mailId = (String) session.getAttribute("mailId");
		
		Long userId = (Long) session.getAttribute("userId");
		
		UserData user = ObjectifyService.ofy().load().type(UserData.class).id(userId).now();
		TimeData timeEntry = new TimeData(mailId,startTime,endTime);		
		ObjectifyService.ofy().save().entity(timeEntry);
		
		user.setLastEntry(id);
		user.setClockin(true);
		session.setAttribute("entryId", id);
		session.setAttribute("clockIn", true);

		ObjectifyService.ofy().save().entity(user);
		
		
		
        sdf.setTimeZone(TimeZone.getTimeZone((String) session.getAttribute("timeZone")));
        String utcStartTime =  sdf.format(new Date(startTime));
        System.out.println(utcStartTime);
        out.print(utcStartTime);
		
	}
	
	else{
		Boolean clockedIn=(Boolean) session.getAttribute("clockIn");
		if(clockedIn){
			response.setStatus(400);
			out.println("already clockedIn");
		}
		else{
//			UserData key = new UserData();
//			Create new entry and store;
			
			String mailId = (String) session.getAttribute("mailId");
			
			Long userId = (Long) session.getAttribute("userId");
			
			UserData user = ObjectifyService.ofy().load().type(UserData.class).id(userId).now();
			TimeData timeEntry = new TimeData(mailId,startTime,endTime);		
			ObjectifyService.ofy().save().entity(timeEntry);
			
			user.setLastEntry(id);
			user.setClockin(true);
			session.setAttribute("entryId", id);
			session.setAttribute("clockIn",true);
			ObjectifyService.ofy().save().entity(user);
			
			
			
	        sdf.setTimeZone(TimeZone.getTimeZone((String) session.getAttribute("timeZone")));
	        String utcStartTime =  sdf.format(new Date(startTime));
	        System.out.println(utcStartTime);
	        out.print(utcStartTime);
		}
	}


	}
	

}
