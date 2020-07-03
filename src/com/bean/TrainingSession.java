package com.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.enumeration.enumTraining;

@Entity
public class TrainingSession {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int tsId;
	enumTraining training;
	Date sessionDate;
	@ManyToMany(fetch = FetchType.EAGER)
	Set<Driver> candidates;
	public Set<Driver> getCandidates() {
		return candidates;
	}
	public void setCandidates(Set<Driver> candidates) {
		this.candidates = candidates;
	}
	public int getTsId() {
		return tsId;
	}
	public void setTsId(int tsId) {
		this.tsId = tsId;
	}
	public enumTraining getTraining() {
		return training;
	}
	public void setTraining(enumTraining training) {
		this.training = training;
	}
	public Date getSessionDate() {
		return sessionDate;
	}
	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tsId;
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
		TrainingSession other = (TrainingSession) obj;
		if (tsId != other.tsId)
			return false;
		return true;
	}
	
	
}
