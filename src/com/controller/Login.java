package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.Admin;

/**
 * Servlet implementation class Login
 */
@SuppressWarnings("deprecation")
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final SessionFactory sf;   
	private static final long serialVersionUID = 1L;
	static {
		Configuration conf = new Configuration().configure("/hibernate.cfg.xml");
		sf = conf.buildSessionFactory();			
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		s.saveOrUpdate(new Admin("admin1", "abc123"));
		s.saveOrUpdate(new Admin("admin2", "pass123"));
		t.commit();
		s.close();
	}
	
	public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Session session = sf.openSession();
		Admin admin = new Admin(request.getParameter("username").trim(), request.getParameter("password").trim());
		if(admin.equals((Admin)session.get(Admin.class, admin.getLogin()))) {
			HttpSession ss = request.getSession();
			ss.setAttribute("username", admin.getLogin());
			ss.setAttribute("password",admin.getPassword());
			ss.setMaxInactiveInterval(15*60);
			response.sendRedirect("/TaxiDriverSystem/DriverCRUD");
		}else {
			request.setAttribute("error","Invalid Login");
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/adminLogin.jsp");
			rd.forward(request, response);
		}		
		session.close();
	}

}
