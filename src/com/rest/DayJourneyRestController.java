package com.rest;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.bean.DayLog;
import com.bean.Driver;
import com.bean.JourneyLog;

@Path("driver")
public class DayJourneyRestController {
	private static final long serialVersionUID = 1L;
	private static final SessionFactory sf;
	static {
		Configuration conf = new Configuration().configure("/hibernate.cfg.xml");
		sf = conf.buildSessionFactory();
	}

	
	@POST
	@Path("startday/{driverId}")
    @Produces(MediaType.TEXT_HTML)
    public String startDay(@PathParam("driverId") int driverId) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Driver driver = (Driver)session.get(Driver.class, driverId);
		Query q = session.createQuery("FROM DayLog where isDayStarted=:a");
		q.setBoolean("a", true);
		tr.commit();
		List<DayLog> dlist = (List<DayLog>)q.list();
		dlist = dlist.stream().filter(d->d.getDriver().getDriverId()==driverId).collect(Collectors.toList());
		if(!dlist.isEmpty()) {
			session.close();
			return "day already started";
		}
		DayLog dl = new DayLog();
		dl.setDriver(driver);
		dl.setDayStart(new Date());
		dl.setDayEnd(null);
		dl.setDuration(0);
		dl.setJourneyLogs(new HashSet<JourneyLog>());
		dl.setDayStarted(true);
		driver.getLogs().add(dl);
		tr = (Transaction) session.beginTransaction();
		session.saveOrUpdate(driver);
		session.saveOrUpdate(dl);
		session.close();
		tr.commit();
		return "day started. Enjoy your work.";
    }
	
	@POST
	@Path("endday/{driverId}")
    @Produces(MediaType.TEXT_HTML)
    public String endDay(@PathParam("driverId") int driverId) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Driver driver = (Driver)session.get(Driver.class, driverId);
		Query q = session.createQuery("FROM DayLog where isDayStarted=:a");
		q.setBoolean("a", true);
		List<DayLog> dlist = (List<DayLog>)q.list();
		dlist = dlist.stream().filter(d->d.getDriver().getDriverId()==driverId).collect(Collectors.toList());
		if(dlist.isEmpty()) {
			tr.commit();
			session.close();
			return "day not started";
		}
		tr.commit();
		DayLog dl = dlist.get(0);
		dl.setDayEnd(new Date());
		dl.getJourneyLogs().stream().filter(jl->jl.isJourneyStarted()).forEach(jl->jl.setJourneyEnd(new Date()));
		dl.getJourneyLogs().stream().forEach(jl->dl.setDuration(dl.getDuration()+jl.getDuration()));
		dl.setDayStarted(false);
		driver.getLogs().add(dl);
		tr = session.beginTransaction();
		session.saveOrUpdate(driver);
		session.saveOrUpdate(dl);
		tr.commit();
		session.close();
		return "day ended. Today's duration of work : "+dl.getDuration();
    }
	
	@POST
	@Path("startJourney/{driverId}")
    @Produces(MediaType.TEXT_HTML)
	public String startJourney(@PathParam("driverId") int driverId) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Driver driver = (Driver)session.get(Driver.class, driverId);
		Query q = session.createQuery("FROM DayLog where isDayStarted=:a");
		q.setBoolean("a", true);
		List<DayLog> dlist = (List<DayLog>)q.list();
		dlist = dlist.stream().filter(d->d.getDriver().getDriverId()==driverId).collect(Collectors.toList());
		if(dlist.isEmpty()) {
			tr.commit();
			session.close();
			return "day not started";
		}
		DayLog dl = dlist.get(0);
		q = session.createQuery("from JourneyLog where isJourneyStarted=:a");
		q.setParameter("a",true);
		List<JourneyLog> jlist = (List<JourneyLog>)q.list();
		jlist = jlist.stream().filter(d->d.getDaylog().getDriver().getDriverId()==driverId).collect(Collectors.toList());
		if(!jlist.isEmpty()) {
			tr.commit();
			session.close();
			return "journey is not finished yet";
		}
		tr.commit();
		
		JourneyLog j = new JourneyLog();
		j.setDaylog(dl);
		j.setDuration(0);
		j.setJourneyStart(new Date());
		j.setJourneyEnd(null);
		j.setJourneyStarted(true);
		dl.getJourneyLogs().add(j);
		tr = session.beginTransaction();
		session.saveOrUpdate(j);
		session.saveOrUpdate(dl);
		tr.commit();
		session.close();
		return "journey is started.";
	}
	
	@POST
	@Path("endJourney/{driverId}")
    @Produces(MediaType.TEXT_HTML)
	public String endJourney(@PathParam("driverId") int driverId) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Driver driver = (Driver)session.get(Driver.class, driverId);
		Query q = session.createQuery("FROM DayLog where isDayStarted=:a");
		q.setBoolean("a", true);
		List<DayLog> dlist = (List<DayLog>)q.list();
		dlist = dlist.stream().filter(d->d.getDriver().getDriverId()==driverId).collect(Collectors.toList());
		if(dlist.isEmpty()) {
			tr.commit();
			session.close();
			return "day not started";
		}
		DayLog dl = dlist.get(0);
		q = session.createQuery("from JourneyLog where isJourneyStarted=:a");
		q.setParameter("a",true);
		List<JourneyLog> jlist = (List<JourneyLog>)q.list();
		jlist = jlist.stream().filter(d->d.getDaylog().getDriver().getDriverId()==driverId).collect(Collectors.toList());
		if(jlist.isEmpty()) {
			tr.commit();
			session.close();
			return "journey is not started yet";
		}
		tr.commit();
		
		JourneyLog j = jlist.get(0);
		j.setJourneyEnd(new Date());
		j.setJourneyStarted(false);
		j.setDuration((int) ((j.getJourneyEnd().getTime()-j.getJourneyStart().getTime())/1000));
		dl.getJourneyLogs().add(j);
		tr = session.beginTransaction();
		session.saveOrUpdate(j);
		session.saveOrUpdate(dl);
		tr.commit();
		session.close();
		return "journey is finished. Total duration of work : "+j.getDuration();
	}
	
	@GET
	@Path("getdriver/{license}")
    @Produces(MediaType.TEXT_HTML)
	public String getDriver(@PathParam("license") String license) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Query q = session.createQuery("FROM Driver where license=:a");
		q.setString("a",license);
		List<Driver> dl = (List<Driver>)q.list();
		if(dl.isEmpty()) {
			tr.commit();
			session.close();
			return "-1";
		}
		tr.commit();session.close();
		return ""+dl.get(0).getDriverId()+":"+dl.get(0).getDriverName();
	}
	
}
