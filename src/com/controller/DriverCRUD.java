package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.Driver;

/**
 * Servlet implementation class DriverCRUD
 */
@WebServlet("/DriverCRUD")
public class DriverCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final SessionFactory sf;
	static {
		Configuration conf = new Configuration().configure("/hibernate.cfg.xml");
		sf = conf.buildSessionFactory();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DriverCRUD() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Session s = sf.openSession();
		String function = request.getParameter("function");

		if (function != null) {
			switch (function) {
			case "add":
				addDriver(request.getParameter("name"), request.getParameter("license"),
						request.getParameter("contact"), s, request, response);
				break;
			case "update":
				updateDriver(request.getParameter("driverId"), request.getParameter("name"), request.getParameter("license"),
						request.getParameter("contact"), s, request, response);
				break;
			case "delete":
				deleteDriver(request.getParameter("driverId").trim(), s, request, response);
				break;
			}
		}
		s.clear();
		Criteria c = s.createCriteria(Driver.class);
		List<Driver> l = c.list();
		request.setAttribute("drivers", l);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/driverscrud.jsp");
		rd.forward(request, response);
		s.close();
	}

	private void updateDriver(String id, String driverName, String license, String contact, Session s,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Driver d = new Driver(Integer.parseInt(id), driverName, license, contact);
		Transaction t = s.beginTransaction();
		s.saveOrUpdate(d);
		t.commit();
	}

	private void deleteDriver(String parameter, Session s, HttpServletRequest request, HttpServletResponse response) {
		Transaction t = s.beginTransaction();
		Driver d = (Driver) s.get(Driver.class, Integer.parseInt(parameter));
		s.delete(d);
		t.commit();
	}

	private void addDriver(String driverName, String license, String contact, Session s, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Transaction t = s.beginTransaction();
		Driver d = new Driver(driverName, license, contact);
		Criteria c = s.createCriteria(Driver.class);
		List<Driver> l = c.list();
		if(l.contains(d)) {
			request.setAttribute("message","Invalid Operation");
		}else {
		s.save(d);
		}
		t.commit();
	}

}
