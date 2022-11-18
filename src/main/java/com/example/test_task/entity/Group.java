package com.example.test_task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer id;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Student> students;

    public Group(Integer id, List<Student> students) {
        this.id = id;
        this.students = students;
    }

    public Group() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
