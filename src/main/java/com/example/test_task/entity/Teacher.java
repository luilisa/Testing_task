package com.example.test_task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer id;

    @Column(name = "teacher_name")
    private String name;
    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Course> courses;

    public Teacher() {
    }

    public Teacher(Integer id, String name, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


}
