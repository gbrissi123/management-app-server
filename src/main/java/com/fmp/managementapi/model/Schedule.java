package com.fmp.managementapi.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
	private Long id;
	
	@Column(nullable = false)
	private Date startDate;

	@Column(nullable = false)
	private Date endDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStart() {
		return startDate;
	}

	public void setStart(Date start) {
		this.startDate = start;
	}

	public Date getEnd() {
		return endDate;
	}

	public void setEnd(Date end) {
		this.endDate = end;
	}
}
