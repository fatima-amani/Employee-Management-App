package com.fatima.Employee_Management_App.repository;

import com.fatima.Employee_Management_App.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}

