package com.example.test_task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer id;

    @Column(name = "student_name")
    private String name;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_group_id")
    Group group;

    @ManyToOne
    @JoinColumn(name = "fk_journal_id")
    @JsonIgnore
    private Journal journal;

    @Column(name = "grade")
    private Integer grade;

    public Student(Integer id, String name, Group group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
