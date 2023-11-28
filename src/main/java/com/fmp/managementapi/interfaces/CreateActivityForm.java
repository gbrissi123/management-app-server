package com.fmp.managementapi.interfaces;

import com.fmp.managementapi.model.Project;

public class CreateActivityForm {
	private Project project;
	private String description;
	private String name;
	private DateRange schedule;
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DateRange getSchedule() {
		return schedule;
	}
	public void setSchedule(DateRange schedule) {
		this.schedule = schedule;
	}
}
