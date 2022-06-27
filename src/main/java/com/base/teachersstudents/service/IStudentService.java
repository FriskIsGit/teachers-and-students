package com.base.teachersstudents.service;

import com.base.teachersstudents.entities.Student;

import java.util.List;

interface IStudentService{
    void saveStudent(Student student);
    Student getStudentById(long id);
    List<Student> getStudentsByName(String name);
    List<Student> getStudentsByLastname(String lastname);
    Student getStudentByNameAndLastname(String name, String lastname);
    List<Student> getAllSortedAscendinglyBy(String fieldName);
    List<Student> getAllSortedDescendinglyBy(String fieldName);
    void deleteStudentById(long id);
}
