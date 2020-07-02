package com.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int driverId;
	String driverName;
	String license;
	String contact;
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="driverId")
	Set<Qualification> qualifications;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="driverId")
	Set<Training> trainings;
	
	public Set<Training> getTrainings() {
		return trainings;
	}
	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}
	public Set<Qualification> getQualifications() {
		return qualifications;
	}
	public void setQualifications(Set<Qualification> qualifications) {
		this.qualifications = qualifications;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public Driver() {
		super();
	}
	public Driver(int driverId, String driverName, String license, String contact) {
		super();
		this.driverId = driverId;
		this.driverName = driverName;
		this.license = license;
		this.contact = contact;
	}
	public Driver(String driverName, String license, String contact) {
		super();
		this.driverName = driverName;
		this.license = license;
		this.contact = contact;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((license == null) ? 0 : license.hashCode());
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
		Driver other = (Driver) obj;
		if (license == null) {
			if (other.license != null)
				return false;
		} else if (!license.equals(other.license))
			return false;
		return true;
	}
	
	
}
