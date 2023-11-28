package com.fmp.managementapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fmp.managementapi.model.Activity;
import com.fmp.managementapi.model.Project;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
	List<Activity> findByProject(Project project);
}