package com.example.test_task.repository;

import com.example.test_task.entity.Discipline;
import com.example.test_task.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    List<Teacher> findAllByNameEquals(String name);
}
