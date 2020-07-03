package com.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.enumeration.enumQualification;

@Entity
public class Qualification {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int qid;
	
	@ManyToOne
	Driver driver;
	enumQualification qualification;
	Date dateOfExpiry;
	
	public Qualification() {
		super();
	}
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public enumQualification getQualification() {
		return qualification;
	}
	public void setQualification(enumQualification qualification) {
		this.qualification = qualification;
	}
	public Date getDateOfExpiry() {
		return dateOfExpiry;
	}
	public void setDateOfExpiry(Date dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + qid;
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
		Qualification other = (Qualification) obj;
		if (qid != other.qid)
			return false;
		return true;
	}
	
	
}
