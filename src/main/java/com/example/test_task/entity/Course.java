package com.example.test_task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "course")
public class Course{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_teacher_id")
    Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "fk_discipline_id")
    Discipline discipline;

    @JsonIgnore
    @OneToOne(mappedBy = "course")
    private Journal journal;

    public Course(Integer id, Teacher teacher, Discipline discipline, Journal journal) {
        this.id = id;
        this.teacher = teacher;
        this.discipline = discipline;
        this.journal = journal;
    }

    public Course() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }
}
