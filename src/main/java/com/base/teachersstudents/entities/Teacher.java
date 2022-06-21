package com.base.teachersstudents.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "teachers")
@Entity
public class Teacher{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name, lastname, email, subject;
    private int age;

    @ManyToMany(fetch = FetchType.EAGER,  cascade = CascadeType.MERGE, mappedBy = "teachers")
    private Set<Student> students = new HashSet<>();
    public Teacher(String name, String lastname, String email, String subject, int age){
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.subject = subject;
        this.age = age;
    }
    //Empty constructor to avoid 'No default constructor for entity' exception
    private Teacher(){
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

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public Set<Student> getStudents(){
        return students;
    }
    @Override
    public String toString(){
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", age=" + age +
                '}';
    }
}