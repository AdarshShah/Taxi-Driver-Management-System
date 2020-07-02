package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.Admin;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter(urlPatterns = { "/AdminFilter","/jsp/driverscrud.jsp" ,"/jsp/qualificationcrud.jsp"}, servletNames = { "DriverCRUD" })
public class AdminFilter implements Filter {
	private static final SessionFactory sf;
	static {
		Configuration conf = new Configuration().configure("/hibernate.cfg.xml");
		sf = conf.buildSessionFactory();			
	}
    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession(false);
		if(session==null) {
			req.setAttribute("error","Please enter credentials");
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/adminLogin.jsp");
			rd.forward(request, response);
		}else {
			Session sess = sf.openSession();
			Admin admin = new Admin(session.getAttribute("username").toString(),session.getAttribute("password").toString());
			if(admin.equals((Admin)sess.get(Admin.class, admin.getLogin()))) {
				chain.doFilter(request, response);
			}else {
				req.setAttribute("error","Invalid credentials");
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/adminLogin.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
