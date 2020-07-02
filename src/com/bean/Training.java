package com.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

import com.enumeration.enumTraining;

@Entity
public class Training {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int tid;
	
	@ManyToOne
	@ForeignKey(name="fk_driverId")
	Driver driver;
	Date dateOfExpiry;
	enumTraining training;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Date getDateOfExpiry() {
		return dateOfExpiry;
	}
	public void setDateOfExpiry(Date dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
	public enumTraining getTraining() {
		return training;
	}
	public void setTraining(enumTraining training) {
		this.training = training;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tid;
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
		Training other = (Training) obj;
		if (tid != other.tid)
			return false;
		return true;
	}
	
	
}
