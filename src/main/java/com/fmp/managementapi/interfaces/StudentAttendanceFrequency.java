package com.fmp.managementapi.interfaces;

import com.fmp.managementapi.model.Student;

public class StudentAttendanceFrequency {
	private Student student;
	private double totalAttendances = 0;
	private double successfulAttendances = 0;
	private double frequencyRatio = 0;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public double getTotalAttendances() {
		return totalAttendances;
	}
	public void setTotalAttendances(double totalAttendances) {
		this.totalAttendances = totalAttendances;
		this.frequencyRatio = (this.successfulAttendances / this.totalAttendances) * 100;
	}
	public double getSuccessfulAttendances() {
		return successfulAttendances;
	}
	public void setSuccessfulAttendances(double successfulAttendances) {
		this.successfulAttendances = successfulAttendances;
		this.frequencyRatio = (this.successfulAttendances / this.totalAttendances) * 100;
	}
	public double getFrequencyRatio() {
		return frequencyRatio;
	}

}
