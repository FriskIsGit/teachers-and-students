package com.base.teachersstudents.dao;

import com.base.teachersstudents.entities.Student;

import java.util.List;

interface IStudentDAO{
    void saveStudent(Student student);
    List<Student> getStudents();
    Student findById(long id);
    void deleteById(long id);
    Student retrieveByNameAndLastname(String name, String lastname);
    List<Student> retrieveByName(String name);
    List<Student> retrieveByLastname(String lastname);
}
