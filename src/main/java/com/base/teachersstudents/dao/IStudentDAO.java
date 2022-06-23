package com.base.teachersstudents.dao;

import com.base.teachersstudents.entities.Student;

import java.util.List;

interface IStudentDAO{
    long studentCount();
    void saveStudent(Student student);
    List<Student> getAllStudents();
    Student findById(long id);
    void deleteById(long id);
    Student retrieveByNameAndLastname(String name, String lastname);
    List<Student> retrieveByName(String name);
    List<Student> retrieveByLastname(String lastname);
}
