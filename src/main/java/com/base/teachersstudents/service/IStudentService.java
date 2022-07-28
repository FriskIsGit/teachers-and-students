package com.base.teachersstudents.service;

import com.base.teachersstudents.entities.Student;
import org.springframework.data.domain.Page;

import java.util.List;

interface IStudentService{
    boolean saveStudent(Student student);
    Student getStudentById(long id);
    List<Student> getStudentsByName(String name);
    List<Student> getStudentsByLastname(String lastname);
    Student getStudentByNameAndLastname(String name, String lastname);
    List<Student> getAllSortedAscendinglyBy(String fieldName);
    List<Student> getAllSortedDescendinglyBy(String fieldName);
    Page<Student> getStudentsPagedAscendinglyBy(int page, int size, String fieldName);
    Page<Student> getStudentsPagedDescendinglyBy(int page, int size, String fieldName);
    void deleteStudentById(long id);
    long studentCount();
}
