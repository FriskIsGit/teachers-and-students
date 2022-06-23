package com.base.teachersstudents.service;

import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.entities.Teacher;

import java.util.List;

interface IStudentTeacherService{
    void addManyTeachersToStudent(Student student, Teacher... teachers);
    void addManyTeachersToStudent(Student student, List<Teacher> teacherList);
    void addTeacherToManyStudents(Teacher teacher, Student... students);
    void addTeacherToManyStudents(Teacher teacher, List<Student> studentList);
}