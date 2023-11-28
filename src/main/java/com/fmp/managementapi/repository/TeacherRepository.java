package com.fmp.managementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.fmp.managementapi.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	UserDetails findByUsername(String username);
}
