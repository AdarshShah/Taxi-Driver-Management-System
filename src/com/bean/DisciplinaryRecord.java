package com.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.enumeration.enumDisciplinaryRecord;

@Entity
public class DisciplinaryRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int drId;
	@ManyToOne
	private Driver driver;
	private Date date;
	private enumDisciplinaryRecord disciplinaryRecord;
	public int getDrId() {
		return drId;
	}
	public void setDrId(int drId) {
		this.drId = drId;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public enumDisciplinaryRecord getDisciplinaryRecord() {
		return disciplinaryRecord;
	}
	public void setDisciplinaryRecord(enumDisciplinaryRecord disciplinaryRecord) {
		this.disciplinaryRecord = disciplinaryRecord;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + drId;
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
		DisciplinaryRecord other = (DisciplinaryRecord) obj;
		if (drId != other.drId)
			return false;
		return true;
	}
	
	
}
