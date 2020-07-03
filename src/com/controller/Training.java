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

import com.bean.Driver;
import com.enumeration.enumTraining;

/**
 * Servlet implementation class Training
 */
@WebServlet("/Training")
public class Training extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final SessionFactory sf;
	static {
		Configuration conf = new Configuration().configure("/hibernate.cfg.xml");
		sf = conf.buildSessionFactory();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Training() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
		Driver d = (Driver) session.get(Driver.class, Integer.parseInt(request.getSession().getAttribute("driverId").toString()));
		String tfunc = request.getParameter("tfunc");
		if (tfunc != null) {
			switch (tfunc) {
			case "add":
				addTraining(session,d,request,response);
				break;
			case "update":
				updateTraining(session,d,request,response);
				break;
			case "delete":
				deleteTraining(session,d,request,response);
				break;
				
			}
		}
		d = (Driver) session.get(Driver.class, Integer.parseInt(request.getSession().getAttribute("driverId").toString()));
		session.close();
		request.setAttribute("driver", d);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/trainingcrud.jsp");
		rd.forward(request, response);
	}

	private void deleteTraining(Session session, Driver d, HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("tid"));
		com.bean.Training t = (com.bean.Training)session.get(com.bean.Training.class, id);
		d.getTrainings().remove(t);
		Transaction tr = session.beginTransaction();
		session.saveOrUpdate(t);
		session.saveOrUpdate(d);
		session.delete(t);
		tr.commit();
		
	}

	private void updateTraining(Session session, Driver d, HttpServletRequest request, HttpServletResponse response) {
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		int id = Integer.parseInt(request.getParameter("tid"));
		com.bean.Training t = (com.bean.Training)session.get(com.bean.Training.class, id);
		try {
			t.setDateOfExpiry(df.parse(request.getParameter("expirydate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.setTraining(enumTraining.valueOf(request.getParameter("type")));
		session.getTransaction().begin();
		session.saveOrUpdate(t);
		session.getTransaction().commit();	
		
	}

	private void addTraining(Session session, Driver d, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		
		com.bean.Training t = new com.bean.Training();
		t.setDriver(d);
		try {
			t.setDateOfExpiry(df.parse(request.getParameter("expirydate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.setTraining(enumTraining.valueOf(request.getParameter("type")));
		d.getTrainings().add(t);
		session.getTransaction().begin();
		session.saveOrUpdate(t);
		session.saveOrUpdate(d);
		session.getTransaction().commit();
	}
}
