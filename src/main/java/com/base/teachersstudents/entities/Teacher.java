package com.base.teachersstudents.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Teacher{
    @Id
    @GeneratedValue
    Long id;

    String name, lastname, email, subject;
    int age;

    public Teacher(String name, String lastname, String email, String subject, int age){
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.subject = subject;
        this.age = age;
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