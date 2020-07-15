package com.springtrackmytime;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ComponentScan(basePackages = {"com.springtrackmytime"})
public class Welcome implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@RequestMapping("/welcome")
	protected void welcome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		System.out.println(session);
		
		if(session==null) {
			
			System.out.println("1");
			response.sendRedirect("Login.jsp");
		}
		
		else {			
				response.sendRedirect("/Dashboard");
		}
	}
	

}
