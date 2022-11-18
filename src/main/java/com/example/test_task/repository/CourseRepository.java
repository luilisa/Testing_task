package com.example.test_task.repository;

import com.example.test_task.entity.Course;
import com.example.test_task.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findAllByTeacherId(Integer courseId);
}
