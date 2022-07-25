package com.base.teachersstudents.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "students")
@Entity
public class Student{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name, lastname, email, major;
    //hiding sensitive data, excluding from serialization
    @JsonBackReference("age")
    private int age;
    //preventing endless recursive serialization
    @JsonBackReference("teachers_students_set")
    @JoinTable(
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private final Set<Teacher> teachers = new HashSet<>();

    public Student(String name, String lastname, String email, String major, int age){
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.major = major;
        this.age = age;
    }
    //Empty constructor to avoid 'No default constructor for entity' exception
    private Student(){
    }

    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getMajor(){
        return major;
    }

    public void setMajor(String major){
        this.major = major;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public Set<Teacher> getTeachers(){
        return teachers;
    }

    @Override
    public String toString(){
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", major='" + major + '\'' +
                ", age=" + age +
                '}';
    }
}