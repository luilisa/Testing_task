package com.example.test_task.controller;



import com.example.test_task.entity.Group;
import com.example.test_task.entity.Student;
import com.example.test_task.exception.ResourceNotFoundException;
import com.example.test_task.repository.GroupRepository;
import com.example.test_task.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class GroupController {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public GroupController(GroupRepository groupRepository, StudentRepository studentRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable(value = "id") Integer groupId)
            throws ResourceNotFoundException {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found for this id :: " + groupId));
        return ResponseEntity.ok().body(group);
    }

    @PostMapping("/groups")
    public Group createGroup(@RequestBody Group group) {
        return groupRepository.save(group);
    }


    @DeleteMapping("/group/{id}")
    public Map<String, Boolean> deleteGroup(@PathVariable(value = "id") Integer groupId)
            throws ResourceNotFoundException {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found for this id :: " + groupId));

        groupRepository.delete(group);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/groups")
    public List<? extends Object> getAllGroups() {
        return groupRepository.findAll();
    }


}
