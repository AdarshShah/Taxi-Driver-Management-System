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
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.DisciplinaryRecord;
import com.bean.Driver;
import com.enumeration.enumDisciplinaryRecord;
import com.enumeration.enumTraining;

/**
 * Servlet implementation class Complaint
 */
@WebServlet("/Complaint")
public class Complaint extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final SessionFactory sf;
	static {
		Configuration conf = new Configuration().configure("/hibernate.cfg.xml");
		sf = conf.buildSessionFactory();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Complaint() {
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
		Driver d = (Driver) session.get(Driver.class,
				Integer.parseInt(request.getSession().getAttribute("driverId").toString()));
		String tfunc = request.getParameter("tfunc");
		if (tfunc != null) {
			switch (tfunc) {
			case "add":
				addComplaint(session, d, request, response);
				break;
			case "update":
				updateComplaint(session, d, request, response);
				break;
			case "delete":
				deleteComplaint(session, d, request, response);
				break;

			}
		}
		d = (Driver) session.get(Driver.class,
				Integer.parseInt(request.getSession().getAttribute("driverId").toString()));
		session.close();
		request.setAttribute("driver", d);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/complaintcrud.jsp");
		rd.forward(request, response);
	}

	private void deleteComplaint(Session session, Driver d, HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("drid"));
		DisciplinaryRecord t = (DisciplinaryRecord)session.get(DisciplinaryRecord.class, id);
		d.getDisciplinaryRecords().remove(t);
		Transaction tr = session.beginTransaction();
		session.saveOrUpdate(t);
		session.saveOrUpdate(d);
		session.delete(t);
		tr.commit();
	
	}

	private void updateComplaint(Session session, Driver d, HttpServletRequest request, HttpServletResponse response) {
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		int id = Integer.parseInt(request.getParameter("drid"));
		DisciplinaryRecord t = (DisciplinaryRecord)session.get(DisciplinaryRecord.class, id);
		try {
			t.setDate(df.parse(request.getParameter("date")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.setDisciplinaryRecord(enumDisciplinaryRecord.valueOf(request.getParameter("type")));
		session.getTransaction().begin();
		session.saveOrUpdate(t);
		session.getTransaction().commit();	
		
	}

	private void addComplaint(Session session, Driver d, HttpServletRequest request, HttpServletResponse response) {
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

		DisciplinaryRecord t = new DisciplinaryRecord();
		t.setDriver(d);
		try {
			t.setDate(df.parse(request.getParameter("date")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.setDisciplinaryRecord(enumDisciplinaryRecord.valueOf(request.getParameter("type")));
		d.getDisciplinaryRecords().add(t);
		session.getTransaction().begin();
		session.saveOrUpdate(t);
		session.saveOrUpdate(d);
		session.getTransaction().commit();
	}

}
