package com.example.test_task.controller;

import com.example.test_task.entity.Discipline;
import com.example.test_task.entity.Teacher;
import com.example.test_task.exception.ResourceNotFoundException;
import com.example.test_task.repository.DisciplineRepository;
import com.example.test_task.repository.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000/")
public class DisciplineController {
    private final DisciplineRepository disciplineRepository;
    private final TeacherRepository teacherRepository;

    public DisciplineController(DisciplineRepository disciplineRepository, TeacherRepository teacherRepository) {
        this.disciplineRepository = disciplineRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/disciplines/{id}")
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable(value = "id") Integer disciplineId)
            throws ResourceNotFoundException {
        Discipline discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new ResourceNotFoundException("discipline not found for this id :: " + disciplineId));
        return ResponseEntity.ok().body(discipline);
    }

    @PostMapping("/disciplines")
    public Discipline createDiscipline(@RequestBody Discipline discipline) {
        return disciplineRepository.save(discipline);
    }


    @DeleteMapping("/disciplines/{id}")
    public Map<String, Boolean> deleteDiscipline(@PathVariable(value = "id") Integer disciplineId)
            throws ResourceNotFoundException {
        Discipline discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new ResourceNotFoundException("Discipline not found for this id :: " + disciplineId));

        disciplineRepository.delete(discipline);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/disciplines")
    public List<? extends Object> getAllDisciplines() {
        return disciplineRepository.findAll();
    }


}
