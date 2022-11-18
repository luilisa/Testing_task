package com.example.test_task.controller;

import com.example.test_task.entity.Group;
import com.example.test_task.entity.Journal;
import com.example.test_task.entity.Student;

import com.example.test_task.exception.ResourceNotFoundException;
import com.example.test_task.repository.GroupRepository;
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
public class StudentController {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final JournalRepository journalRepository;


    public StudentController(StudentRepository studentRepository, GroupRepository groupRepository, JournalRepository journalRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.journalRepository = journalRepository;
    }

    @GetMapping("/students")
    public List<? extends Object> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Integer studentId)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }


    @DeleteMapping("/students/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Integer studentId)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        studentRepository.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/groups/{group_id}/students/{student_id}")
    public Student addStudentToGroup(@PathVariable(value = "group_id") Integer groupId,
                                   @PathVariable(value = "student_id") Integer studentId) throws ResourceNotFoundException {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found for this id :: " + groupId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
        student.setGroup(group);
        return studentRepository.save(student);
    }

    @PutMapping("/journals/{journal_id}/students/{student_id}")
    public Student addStudentToJournal(@PathVariable(value = "journal_id") Integer journalId,
                                       @PathVariable(value = "student_id") Integer studentId) throws ResourceNotFoundException {
        Journal journal = journalRepository.findById(journalId)
                .orElseThrow(() -> new ResourceNotFoundException("Journal not found for this id :: " + journalId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
        student.setJournal(journal);
        return studentRepository.save(student);
    }

    @PutMapping("/students/{student_id}")
    public Student studentsGrade(@PathVariable(value = "student_id") Integer studentId, @RequestParam(value = "grade") Integer grade) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
        student.setGrade(grade);
        return studentRepository.save(student);
    }
}
