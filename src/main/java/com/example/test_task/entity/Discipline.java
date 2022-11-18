package com.example.test_task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="discipline")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discipline_id")
    private Integer id;
    @Column(name = "discipline_name")
    private String name;
    @OneToMany(mappedBy = "discipline")
    @JsonIgnore
    private List<Course> courses;

    public Discipline(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public Discipline() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

}
