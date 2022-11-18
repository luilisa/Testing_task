package com.example.test_task.controller;

import com.example.test_task.entity.Course;
import com.example.test_task.entity.Discipline;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000/")
public class CourseController {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final DisciplineRepository disciplineRepository;

    public CourseController(CourseRepository courseRepository, TeacherRepository teacherRepository, DisciplineRepository disciplineRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.disciplineRepository = disciplineRepository;
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable(value = "id") Integer courseId)
            throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));
        return ResponseEntity.ok().body(course);
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @DeleteMapping("/courses/{id}")
    public Map<String, Boolean> deleteCourses(@PathVariable(value = "id") Integer courseId)
            throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));
        courseRepository.delete(course);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/courses")
    public List<? extends Object> getAllCourses(@RequestParam (value = "teacherId", required = false) Integer teacherId) {
        if (teacherId != null) {
            return courseRepository.findAllByTeacherId(teacherId);
        }
        return courseRepository.findAll();
    }

    @PutMapping("/teachers/{teacher_id}/courses/{course_id}")
    public Course assignTeacherToCourse(@PathVariable(value = "teacher_id") Integer teacherId,
                                         @PathVariable (value = "course_id")Integer courseId) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));
        course.setTeacher(teacher);
        return courseRepository.save(course);
    }

    @PutMapping("/disciplines/{discipline_id}/courses/{course_id}")
    public Course assignDisciplineToCourse(@PathVariable(value = "discipline_id") Integer disciplineId,
                                        @PathVariable(value = "course_id") Integer courseId) throws ResourceNotFoundException {
        Discipline discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new ResourceNotFoundException("Discipline not found for this id :: " + disciplineId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));
        course.setDiscipline(discipline);
        return courseRepository.save(course);
    }
}
