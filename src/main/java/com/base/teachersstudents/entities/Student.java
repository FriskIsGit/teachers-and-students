package com.base.teachersstudents.entities;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Table(name = "students")
@Entity
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="student_gen")
    @SequenceGenerator(name = "student_gen", sequenceName = "student_seq", allocationSize=1)
    private Long id;

    private String name, lastname, email, major;
    private int age;

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
