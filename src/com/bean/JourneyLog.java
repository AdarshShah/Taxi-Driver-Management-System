package com.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class JourneyLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jlogId;
	private Date journeyStart;
	private Date journeyEnd;
	private int duration;
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@ManyToOne
	private DayLog daylog;
	private boolean isJourneyStarted;
	public int getJlogId() {
		return jlogId;
	}
	public void setJlogId(int jlogId) {
		this.jlogId = jlogId;
	}
	public Date getJourneyStart() {
		return journeyStart;
	}
	public void setJourneyStart(Date journeyStart) {
		this.journeyStart = journeyStart;
	}
	public Date getJourneyEnd() {
		return journeyEnd;
	}
	public void setJourneyEnd(Date journeyEnd) {
		this.journeyEnd = journeyEnd;
	}
	public DayLog getDaylog() {
		return daylog;
	}
	public void setDaylog(DayLog daylog) {
		this.daylog = daylog;
	}
	public boolean isJourneyStarted() {
		return isJourneyStarted;
	}
	public void setJourneyStarted(boolean isJourneyStarted) {
		this.isJourneyStarted = isJourneyStarted;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + jlogId;
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
		JourneyLog other = (JourneyLog) obj;
		if (jlogId != other.jlogId)
			return false;
		return true;
	}
	
	
	
}
