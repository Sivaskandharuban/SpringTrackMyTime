package com.springtrackmytime;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.googlecode.objectify.ObjectifyService;

@Controller
@ComponentScan(basePackages = {"com.springtrackmytime"})
public class TimeZone {
	@RequestMapping("/timeZone")
	protected void changingTimeZone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(false);
		String mailId = (String) session.getAttribute("mailId");
		session.getAttribute("clockin");
		
		
		
		String timeZone = request.getParameter("timeZone");
//		TimeData currentTimeZone = new TimeData(timeZone);
		System.out.println(timeZone);
		
		session.setAttribute("timeZone", timeZone);
		System.out.println(session.getAttribute("timeZone"));
		
		UserData zone = ObjectifyService.ofy().load().type(UserData.class).filter("mailId",mailId).first().now();	
		if(zone.getTimeZone()==null) {
		zone.setTimeZone("UTC");
		session.setAttribute("timeZone", "UTC");
		ObjectifyService.ofy().save().entity(zone);
		}
		else {
			zone.setTimeZone(timeZone);
			ObjectifyService.ofy().save().entity(zone);
		}
		
	}

}
