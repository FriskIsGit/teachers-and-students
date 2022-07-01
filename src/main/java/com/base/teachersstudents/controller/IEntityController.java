package com.base.teachersstudents.controller;

import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.entities.Teacher;

import java.util.List;
import java.util.Set;

public interface IEntityController{
    Teacher teacherById(long id);
    Student studentById(long id);

    List<Teacher> teachersByName(String name);
    List<Student> studentsByName(String name);

    Set<Teacher> teachersOfStudent(long id);
    Set<Student> studentsOfTeacher(long id);

    List<Teacher> allTeachers();
    List<Student> allStudents();

    void deleteTeacherById(long id);
    void deleteStudentById(long id);

    long teacherCount();
    long studentCount();
}
