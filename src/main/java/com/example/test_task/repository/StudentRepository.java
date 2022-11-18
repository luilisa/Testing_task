package com.example.test_task.repository;

import com.example.test_task.entity.Discipline;
import com.example.test_task.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
