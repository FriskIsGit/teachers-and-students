package com.base.teachersstudents.service;

import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.entities.Teacher;

import java.util.List;

interface IStudentTeacherService{
    void addTeachersToStudent(Student student, Teacher... teachers);
    void addTeachersToStudent(Student student, List<Teacher> teacherList);
    void addStudentsToTeacher(Teacher teacher, Student... students);
    void addStudentsToTeacher(Teacher teacher, List<Student> studentList);
}