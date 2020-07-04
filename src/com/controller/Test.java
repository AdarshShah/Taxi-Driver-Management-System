package com.controller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bean.DayLog;
import com.bean.Driver;

public class Test {
	public static void main(String[] args) {
		Configuration conf = new Configuration().configure("/hibernate.cfg.xml");
		SessionFactory sf = conf.buildSessionFactory();
		int driverId=1;
		Session session = sf.openSession();
		Driver driver = (Driver)session.get(Driver.class,driverId);
		Query q = session.createQuery("FROM DayLog where isDayStarted=:a");
		q.setBoolean("a", true);
		q.setInteger("d", driverId);
		System.out.println(((DayLog)(q.list().get(0))).getDriver().getDriverId());
		session.close();
	}
}
