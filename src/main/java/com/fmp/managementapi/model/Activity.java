package com.fmp.managementapi.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activity_id")
	private Long id;

	@ManyToOne()
	private Project project;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true)
	private String description;

	@JoinColumn(name = "schedule_id")
	@OneToOne()
	private Schedule schedule;

	@Column(nullable = true, name = "students_attendance")
	@ManyToMany()
	private List<StudentAttendance> studentsAttendance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public List<StudentAttendance> getStudentsAttendance() {
		return studentsAttendance;
	}

	public void setStudentsAttendance(List<StudentAttendance> studentsAttendance) {
		this.studentsAttendance = studentsAttendance;
	}
}
