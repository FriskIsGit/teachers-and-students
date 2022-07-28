package com.base.teachersstudents.controller;

import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.entities.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface IEntityController{
    ResponseEntity<Teacher> teacherById(long id);
    ResponseEntity<Student> studentById(long id);

    List<Teacher> teachersByName(String name);
    List<Student> studentsByName(String name);

    Set<Teacher> teachersOfStudent(long id);
    Set<Student> studentsOfTeacher(long id);


    void deleteTeacherById(long id);
    void deleteStudentById(long id);

    long teacherCount();
    long studentCount();
}
