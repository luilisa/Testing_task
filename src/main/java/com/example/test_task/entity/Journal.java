package com.example.test_task.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "journal")
public class Journal{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_id")
    private Integer id;

    @OneToMany(mappedBy = "journal")
    List<Student> students;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course", referencedColumnName = "course_id")
    Course course;


    public Journal(Integer id, List<Student> students, Course course) {
        this.id = id;
        this.students = students;
        this.course = course;
    }

    public Journal() {
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
