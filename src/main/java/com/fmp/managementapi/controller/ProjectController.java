package com.fmp.managementapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fmp.managementapi.interfaces.CreateActivityForm;
import com.fmp.managementapi.interfaces.DateRange;
import com.fmp.managementapi.interfaces.StudentAttendanceFrequency;
import com.fmp.managementapi.model.Activity;
import com.fmp.managementapi.model.Project;
import com.fmp.managementapi.model.Schedule;
import com.fmp.managementapi.model.Student;
import com.fmp.managementapi.model.StudentAttendance;
import com.fmp.managementapi.repository.ActivityRepository;
import com.fmp.managementapi.repository.ProjectRepository;
import com.fmp.managementapi.repository.ScheduleRepository;
import com.fmp.managementapi.repository.StudentAttendanceRepository;
import com.fmp.managementapi.repository.StudentRepository;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/projects")
public class ProjectController {
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private StudentAttendanceRepository studentAttendanceRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Project createProject(@RequestBody Project project) {
		return projectRepository.save(project);
	}

	@PostMapping("activities")
	public Activity createActivity(@RequestBody CreateActivityForm activityForm) {
		DateRange range = activityForm.getSchedule();
		Schedule schedule = new Schedule();
		schedule.setStart(range.getStart());
		schedule.setEnd(range.getEnd());
		
		Schedule dbSchedule = scheduleRepository.save(schedule);
		
		Activity activity = new Activity();
		activity.setProject(activityForm.getProject());
		activity.setName(activityForm.getName());
		activity.setDescription(activityForm.getDescription());
		activity.setSchedule(dbSchedule);
		
		List<StudentAttendance> studentsAttendance = new ArrayList<>();
		List<Student> students =  activityForm.getProject().getStudents();
		for (Student student: students) {
			StudentAttendance studentAttendance = new StudentAttendance();
			studentAttendance.setAbsent(false);
			studentAttendance.setStudent(student);
			
			StudentAttendance dbStudentAttendance = studentAttendanceRepository.save(studentAttendance);
			studentsAttendance.add(dbStudentAttendance);
		}
			
		activity.setStudentsAttendance(studentsAttendance);
		
		return activityRepository.save(activity);
	}
	
	@GetMapping("activities/attendance")
	public List<StudentAttendanceFrequency> getStudentsAttendance(@RequestParam Long projectId) {
		Optional<Project> optProject = projectRepository.findById(projectId);
		Project project = optProject.get();
		List<Activity> activities = activityRepository.findByProject(project);
		
		List<Student> projectStudents = project.getStudents();
		List<StudentAttendanceFrequency> studentsFrequency = new ArrayList<>();
		for(Student student: projectStudents) {
			StudentAttendanceFrequency studentFrequency = new StudentAttendanceFrequency();
			studentFrequency.setStudent(student);
			System.out.println("Estudante: " + student.toString());
			
			for (Activity activity: activities) {
				List<StudentAttendance> studentsAttendance = activity.getStudentsAttendance();
				studentFrequency.setTotalAttendances(studentFrequency.getTotalAttendances() + 1);
				
				for(StudentAttendance attendance: studentsAttendance) {
					if(attendance.getStudent().getId() == student.getId()) {
						if(!attendance.isAbsent()) {
							studentFrequency.setSuccessfulAttendances(studentFrequency.getSuccessfulAttendances() + 1);
							break;
						}
					}
				}
			}
			
			studentsFrequency.add(studentFrequency);
		}
		
		return studentsFrequency;
	}
	
	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	public Project updateProject(@RequestBody Project project) throws Exception {
		if (project.getId() == null)
			throw new Exception();

		return projectRepository.save(project);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteProject(@RequestBody Project project) {
		projectRepository.delete(project);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Project> getProjects() {
		return projectRepository.findAll();
	}
}
