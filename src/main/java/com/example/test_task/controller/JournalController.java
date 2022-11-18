package com.example.test_task.controller;

import com.example.test_task.entity.Course;
import com.example.test_task.entity.Group;
import com.example.test_task.entity.Journal;
import com.example.test_task.entity.Student;
import com.example.test_task.exception.ResourceNotFoundException;
import com.example.test_task.repository.CourseRepository;
import com.example.test_task.repository.JournalRepository;
import com.example.test_task.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class JournalController {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final JournalRepository journalRepository;

    public JournalController(StudentRepository studentRepository, CourseRepository courseRepository, JournalRepository journalRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.journalRepository = journalRepository;
    }

    @GetMapping("/journals")
    public List<? extends Object> getAllJournals() {
        return journalRepository.findAll();
    }

    @GetMapping("/journals/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable(value = "id") Integer journalId)
            throws ResourceNotFoundException {
        Journal journal = journalRepository.findById(journalId)
                .orElseThrow(() -> new ResourceNotFoundException("Journal not found for this id :: " + journalId));
        return ResponseEntity.ok().body(journal);
    }

    @PostMapping("/journals")
    public Journal createJournal(@RequestBody Journal journal) {
        return journalRepository.save(journal);
    }


    @DeleteMapping("/journals/{id}")
    public Map<String, Boolean> deleteJournal(@PathVariable(value = "id") Integer journalId)
            throws ResourceNotFoundException {
        Journal journal = journalRepository.findById(journalId)
                .orElseThrow(() -> new ResourceNotFoundException("Journal not found for this id :: " + journalId));

        journalRepository.delete(journal);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/journals/{journal_id}/course/{course_id}")
    public Journal assignCourseToJournal(@PathVariable(value = "course_id") Integer courseId,
                                     @PathVariable(value = "journal_id") Integer journalId) throws ResourceNotFoundException {
        Journal journal = journalRepository.findById(journalId)
                .orElseThrow(() -> new ResourceNotFoundException("Journal not found for this id :: " + journalId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));
        journal.setCourse(course);
        return journalRepository.save(journal);
    }
}
