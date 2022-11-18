package com.example.test_task.repository;

import com.example.test_task.entity.Discipline;
import com.example.test_task.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Integer> {
}
