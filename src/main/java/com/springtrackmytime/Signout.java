package com.springtrackmytime;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Servlet implementation class Signout
 */
//@WebServlet("/Signout")
@Controller
@ComponentScan(basePackages = {"com.springtrackmytime"})
public class Signout implements Serializable {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @RequestMapping("/Signout")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);	
		response.setHeader("Cache-Control","no-cache");
		  response.setHeader("Cache-Control","no-store");
		  response.setHeader("Pragma","no-cache");
		  response.setDateHeader ("Expires", 0);
		  
		  if(session.getAttribute("clockIn")==null) {
			  session.invalidate();
			  System.out.println(request.getSession());
			  response.setStatus(200);
		  }
		  else if(!(boolean) session.getAttribute("clockIn")) {
			  session.invalidate();
			  System.out.println(request.getSession());
			  System.out.println("Signout successful");
//	        response.sendRedirect("Login.jsp");
		  }
		  else {
			  
			  session.setAttribute("clockIn", true);
			  session = request.getSession(false);
			  session.invalidate();
			  System.out.println(request.getSession());
//			  response.sendRedirect("Login.jsp");
			  System.out.println("Signout with clockin");			  
		  }
	        
	}

}
