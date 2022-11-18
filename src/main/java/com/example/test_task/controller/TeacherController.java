package com.example.test_task.controller;

import com.example.test_task.entity.Course;
import com.example.test_task.entity.Teacher;
import com.example.test_task.exception.ResourceNotFoundException;
import com.example.test_task.repository.CourseRepository;
import com.example.test_task.repository.DisciplineRepository;
import com.example.test_task.repository.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class TeacherController {

    private final TeacherRepository teacherRepository;
    private final DisciplineRepository disciplineRepository;
    private final CourseRepository courseRepository;

    public TeacherController(TeacherRepository teacherRepository, DisciplineRepository disciplineRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.disciplineRepository = disciplineRepository;
        this.courseRepository = courseRepository;
    }


    @GetMapping("/teachers")
    public List<? extends Object> getAllTeachers(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            return teacherRepository.findAllByNameEquals(name);
        }
        return teacherRepository.findAll();
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable(value = "id") Integer teacherId)
            throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherId));
        return ResponseEntity.ok().body(teacher);
    }

    @PostMapping("/teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }


    @DeleteMapping("/teachers/{id}")
    public Map<String, Boolean> deleteTeacher(@PathVariable(value = "id") Integer teacherId)
            throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherId));

        teacherRepository.delete(teacher);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
