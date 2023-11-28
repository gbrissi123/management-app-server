package com.fmp.managementapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fmp.managementapi.model.Activity;
import com.fmp.managementapi.model.StudentAttendance;
import com.fmp.managementapi.repository.ActivityRepository;
import com.fmp.managementapi.repository.StudentAttendanceRepository;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/activities")
public class ActivityController {
	@Autowired
	private ActivityRepository activityRepository;

	@Autowired 
	private StudentAttendanceRepository studentAttendanceRepository;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	private List<Activity> getActivities() {
		return activityRepository.findAll();
	}

	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	private Activity updateActivity(@RequestBody Activity activity) {
		List<StudentAttendance> studentsAttendance = studentAttendanceRepository.saveAll(activity.getStudentsAttendance());
		activity.setStudentsAttendance(studentsAttendance);
		
		return activityRepository.save(activity);
	}
}
