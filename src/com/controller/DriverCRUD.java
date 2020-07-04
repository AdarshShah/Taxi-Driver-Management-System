package com.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import com.bean.Qualification;
import com.bean.Training;
import com.bean.TrainingSession;
import com.enumeration.enumTraining;

/**
 * Servlet implementation class DriverCRUD
 */
@SuppressWarnings("deprecation")
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

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Session s = sf.openSession();
		String function = request.getParameter("function");
		RequestDispatcher rd = null;
		if (function != null) {
			switch (function) {
			case "add":
				addDriver(request.getParameter("name"), request.getParameter("license"),
						request.getParameter("contact"), s, request, response);
				break;
			case "update":
				updateDriver(request.getParameter("driverId"), request.getParameter("name"),
						request.getParameter("license"), request.getParameter("contact"), s, request, response);
				break;
			case "delete":
				deleteDriver(request.getParameter("driverId").trim(), s, request, response);
				break;
			case "qualification":
				s.close();
				request.getSession().setAttribute("driverId", request.getParameter("driverId"));
				rd = request.getRequestDispatcher("/Qualification");
				rd.forward(request, response);
				return;
			case "training":
				s.close();
				request.getSession().setAttribute("driverId", request.getParameter("driverId"));
				rd = request.getRequestDispatcher("/Training");
				rd.forward(request, response);
				return;
			case "search":
				Query q = s.createQuery("from Driver where license=:l ");
				q.setParameter("l",request.getParameter("license"));
				if(q.list().isEmpty()) {
					request.setAttribute("error","Driver not found");
					break;
				}
				request.setAttribute("driver",q.list().get(0));
				rd = request.getRequestDispatcher("/jsp/displayDriver.jsp");
				rd.forward(request, response);
				return;
			case "expire":
				
				DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, 30);
				
				Query q1 = s.createQuery("from Driver");
				Set<Qualification> qual = new HashSet<Qualification>();
				Set<Training> train = new HashSet<Training>();
				
				
				Set<Driver> drivers = new HashSet<Driver>(q1.list());
				
				drivers.stream().forEach(dr->dr.getQualifications().stream().forEach((qu)->{ qu.setDriver(dr); if(qu.getDateOfExpiry().getTime()<=c.getTime().getTime()) {qual.add(qu);}}));
				drivers.stream().forEach(dr->dr.getTrainings().stream().forEach((tr)->{tr.setDriver(dr);if(tr.getDateOfExpiry().getTime()<=c.getTime().getTime()) {train.add(tr);}}));
				
				request.setAttribute("qualifications",qual);
				request.setAttribute("trainings",train);
				
				rd = request.getRequestDispatcher("/jsp/licenseExpiry.jsp");
				rd.forward(request, response);
				return;
			}
		}

		String tsfunc = request.getParameter("tsfunc");
		if (tsfunc != null) {
			switch (tsfunc) {
			case "add":
				addTrainingSession(request.getParameter("type"), request.getParameter("sessiondate"), s, request,
						response);
				break;
			case "delete":
				deleteTrainingSession(request.getParameter("tsid"), s, request,
						response);
				break;
			case "manage":
				s.close();
				request.getSession().setAttribute("tsId", request.getParameter("tsid"));
				rd = request.getRequestDispatcher("/TrainingSession");
				rd.forward(request, response);
				return;
			}
		}
		
		Query q = s.createQuery("from Driver");
		Set<Driver> l = new HashSet<Driver>(q.list());
		request.setAttribute("drivers", l);

		Query c1 = s.createQuery("from TrainingSession");
		Set<Driver> ts = new HashSet<Driver>(c1.list());
		request.setAttribute("trainingSessions", ts);
		rd = request.getRequestDispatcher("/jsp/driverindex.jsp");
		rd.forward(request, response);
	
	}

	private void deleteTrainingSession(String tsid, Session s, HttpServletRequest request,
			HttpServletResponse response) {
		TrainingSession ts = (TrainingSession)s.get(TrainingSession.class, Integer.parseInt(tsid));
		for(Driver d : ts.getCandidates()) {
			d.getTrainingSessions().remove(ts);
			s.saveOrUpdate(d);
		}
		ts.getCandidates().clear();
		s.saveOrUpdate(ts);
		s.beginTransaction().commit();		
		s.delete(ts);
		s.beginTransaction().commit();
	}

	private void addTrainingSession(String type, String date, Session s, HttpServletRequest request,
			HttpServletResponse response) {
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		TrainingSession ts = new TrainingSession();
		ts.setTraining(enumTraining.valueOf(type));
		ts.setCandidates(new HashSet<Driver>());
		try {
			ts.setSessionDate(df.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Transaction t = s.beginTransaction();
		s.saveOrUpdate(ts);
		t.commit();
	}

	private void updateDriver(String id, String driverName, String license, String contact, Session s,
			HttpServletRequest request, HttpServletResponse response) {

		Driver d = new Driver(Integer.parseInt(id), driverName, license, contact);
		Transaction t = s.beginTransaction();
		s.saveOrUpdate(d);
		t.commit();
	}

	private void deleteDriver(String parameter, Session s, HttpServletRequest request, HttpServletResponse response) {
		Transaction t1 = s.beginTransaction();
		Driver d = (Driver) s.get(Driver.class, Integer.parseInt(parameter));
		
		for(TrainingSession ts : d.getTrainingSessions()) {
			ts.getCandidates().remove(d);
			s.saveOrUpdate(ts);
		}
		
		
		for(com.bean.Qualification q : d.getQualifications()) {
			q.setDriver(null);
			s.saveOrUpdate(q);
		}
		
		
		for(Training tn : d.getTrainings()) {
			tn.setDriver(null);
			s.saveOrUpdate(tn);
		}
		d.setTrainings(new HashSet<Training>());;
		d.setTrainingSessions(new HashSet<TrainingSession>());
		d.setQualifications(new HashSet<com.bean.Qualification>());
		s.saveOrUpdate(d);
		t1.commit();
		Transaction t = s.beginTransaction();
		s.delete(d);
		t.commit();
	}

	@SuppressWarnings("unchecked")
	private void addDriver(String driverName, String license, String contact, Session s, HttpServletRequest request,
			HttpServletResponse response) {

		Transaction t = s.beginTransaction();
		Driver d = new Driver(driverName, license, contact);
		d.setQualifications(new HashSet<com.bean.Qualification>());
		d.setTrainings(new HashSet<Training>());
		d.setTrainingSessions(new HashSet<TrainingSession>());
		
		Query q = s.createQuery("from Driver");
		Set<Driver> l = new HashSet<Driver>(q.list());
		
		if (l.contains(d)) {
			request.setAttribute("message", "Invalid Operation");
		} else {
			s.save(d);
		}
		t.commit();
	}

}
