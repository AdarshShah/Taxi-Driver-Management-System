package com.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bean.Driver;
import com.enumeration.enumQualification;

/**
 * Servlet implementation class Qualification
 */
@WebServlet("/Qualification")
public class Qualification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final SessionFactory sf;
	static {
		Configuration conf = new Configuration().configure("/hibernate.cfg.xml");
		sf = conf.buildSessionFactory();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Qualification() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Session session = sf.openSession();
		Driver d = (Driver) session.get(Driver.class, Integer.parseInt(request.getSession().getAttribute("driverId").toString()));
		String qfunc = request.getParameter("qfunc");
		if (qfunc != null) {
			switch (qfunc) {
			case "add":
				addQualification(session,d,request,response);
				break;
			case "update":
				updateQualification(session,d,request,response);
				break;
			case "delete":
				deleteQualification(session,d,request,response);
				break;
				
			}
		}
		d = (Driver) session.get(Driver.class, Integer.parseInt(request.getSession().getAttribute("driverId").toString()));
		session.close();
		request.setAttribute("driver", d);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/qualificationcrud.jsp");
		rd.forward(request, response);
	}

	private void deleteQualification(Session session, Driver d, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("qid"));
		com.bean.Qualification q = (com.bean.Qualification)session.get(com.bean.Qualification.class, id);
		d.getQualifications().remove(q);
		session.getTransaction().begin();
		session.saveOrUpdate(d);
		session.delete(q);
		session.getTransaction().commit();
	}

	private void updateQualification(Session session, Driver d, HttpServletRequest request,
			HttpServletResponse response) {
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		int id = Integer.parseInt(request.getParameter("qid"));
		com.bean.Qualification q = (com.bean.Qualification)session.get(com.bean.Qualification.class, id);
		try {
			q.setDateOfExpiry(df.parse(request.getParameter("expirydate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		q.setQualification(enumQualification.valueOf(request.getParameter("type")));
		session.getTransaction().begin();
		session.saveOrUpdate(q);
		session.getTransaction().commit();		
	}

	private void addQualification(Session session, Driver d, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		
		com.bean.Qualification q = new com.bean.Qualification();
		q.setDriver(d);
		try {
			q.setDateOfExpiry(df.parse(request.getParameter("expirydate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		q.setQualification(enumQualification.valueOf(request.getParameter("type")));
		d.getQualifications().add(q);
		session.getTransaction().begin();
		session.saveOrUpdate(q);
		session.saveOrUpdate(d);
		session.getTransaction().commit();
	}

}
