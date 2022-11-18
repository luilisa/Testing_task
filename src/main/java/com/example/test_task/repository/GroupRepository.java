package com.example.test_task.repository;

import com.example.test_task.entity.Discipline;
import com.example.test_task.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
