package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.Driver;

/**
 * Servlet implementation class TrainingSession
 */
@WebServlet("/TrainingSession")
public class TrainingSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final SessionFactory sf;
	static {
		Configuration conf = new Configuration().configure("/hibernate.cfg.xml");
		sf = conf.buildSessionFactory();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
		com.bean.TrainingSession ts = (com.bean.TrainingSession) session.get(com.bean.TrainingSession.class,
				Integer.parseInt((String) request.getSession().getAttribute("tsId")));

		String function = request.getParameter("function");
		if (function != null) {
			switch (function) {
			case "remove":
				removeCandidate(session,ts,request,response);
				break;
			case "add":
				addCandidate(session,ts,request,response);
				break;
			}
		}

		Transaction tr = session.beginTransaction();
		Query q = session.createQuery("From Driver");
		List<Driver> l = q.list();
		tr.commit();
		Set<Driver> s = l.stream().filter(d -> !d.getTrainingSessions().contains(ts)).collect(Collectors.toSet());
		request.setAttribute("drivers", s);
		request.getSession().setAttribute("trainingSession", ts);
		session.close();
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/trainingsession.jsp");
		rd.forward(request, response);
	}

	private void addCandidate(Session session, com.bean.TrainingSession ts, HttpServletRequest request, HttpServletResponse response) {
		int driverId = Integer.parseInt(request.getParameter("did"));
		Driver driver = (Driver)session.get(Driver.class, driverId);
		driver.getTrainingSessions().add(ts);
		ts.getCandidates().add(driver);
		Transaction t = session.beginTransaction();
		session.saveOrUpdate(driver);
		session.saveOrUpdate(ts);
		t.commit();
	}

	private void removeCandidate(Session session, com.bean.TrainingSession ts, HttpServletRequest request, HttpServletResponse response) {
		int driverId = Integer.parseInt(request.getParameter("did"));
		Driver driver = (Driver)session.get(Driver.class, driverId);
		driver.getTrainingSessions().remove(ts);
		ts.getCandidates().remove(driver);
		Transaction t = session.beginTransaction();
		session.saveOrUpdate(driver);
		session.saveOrUpdate(ts);
		t.commit();
	}

}
