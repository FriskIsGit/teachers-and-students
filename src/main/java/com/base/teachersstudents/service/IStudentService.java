package com.base.teachersstudents.service;

import com.base.teachersstudents.entities.Student;

interface IStudentService{
    void registerStudent(Student student);
    void deleteStudent(long id);
}
