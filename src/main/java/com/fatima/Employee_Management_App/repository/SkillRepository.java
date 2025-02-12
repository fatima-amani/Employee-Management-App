package com.fatima.Employee_Management_App.repository;

import com.fatima.Employee_Management_App.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}

