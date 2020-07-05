package com.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int driverId;
	private String driverName;
	private String license;
	private String contact;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Qualification> qualifications;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Training> trainings;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<DayLog> logs;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<DisciplinaryRecord> disciplinaryRecords;
	
	public Set<DisciplinaryRecord> getDisciplinaryRecords() {
		return disciplinaryRecords;
	}
	public void setDisciplinaryRecords(Set<DisciplinaryRecord> disciplinaryRecords) {
		this.disciplinaryRecords = disciplinaryRecords;
	}
	public Set<DayLog> getLogs() {
		return logs;
	}
	public void setLogs(Set<DayLog> logs) {
		this.logs = logs;
	}
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<TrainingSession> trainingSessions;
	
	public Set<TrainingSession> getTrainingSessions() {
		return trainingSessions;
	}
	public void setTrainingSessions(Set<TrainingSession> trainingSessions) {
		this.trainingSessions = trainingSessions;
	}
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
