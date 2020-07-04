package com.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class DayLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int dlogId;
	private boolean isDayStarted;
	private Date dayStart;
	private Date dayEnd;
	private int duration;
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@ManyToOne
	private Driver driver;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<JourneyLog> journeyLogs;
	
	public Set<JourneyLog> getJourneyLogs() {
		return journeyLogs;
	}
	public void setJourneyLogs(Set<JourneyLog> journeyLogs) {
		this.journeyLogs = journeyLogs;
	}
	public int getDlogId() {
		return dlogId;
	}
	public void setDlogId(int dlogId) {
		this.dlogId = dlogId;
	}
	public boolean isDayStarted() {
		return isDayStarted;
	}
	public void setDayStarted(boolean isDayStarted) {
		this.isDayStarted = isDayStarted;
	}
	public Date getDayStart() {
		return dayStart;
	}
	public void setDayStart(Date dayStart) {
		this.dayStart = dayStart;
	}
	public Date getDayEnd() {
		return dayEnd;
	}
	public void setDayEnd(Date dayEnd) {
		this.dayEnd = dayEnd;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dlogId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DayLog other = (DayLog) obj;
		if (dlogId != other.dlogId)
			return false;
		return true;
	}
	
	
	
}
