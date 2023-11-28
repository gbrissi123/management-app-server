package com.fmp.managementapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class StudentAttendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_attendance_id")
	private Long id;

	@ManyToOne()
	private Student student;
	
	@Column(nullable = false)
	private boolean isAbsent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public boolean isAbsent() {
		return isAbsent;
	}

	public void setAbsent(boolean isAbsent) {
		this.isAbsent = isAbsent;
	}
}
