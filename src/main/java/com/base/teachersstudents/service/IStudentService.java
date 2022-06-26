package com.base.teachersstudents.service;

import com.base.teachersstudents.entities.Student;

import java.util.List;

interface IStudentService{
    void saveStudent(Student student);
    Student getStudentById(long id);
    List<Student> getStudentsByName(String name);
    List<Student> getStudentsByLastname(String lastname);
    Student getStudentByNameAndLastname(String name, String lastname);
    void deleteStudentById(long id);
}
