package com.example.test_task.repository;

import com.example.test_task.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
}
