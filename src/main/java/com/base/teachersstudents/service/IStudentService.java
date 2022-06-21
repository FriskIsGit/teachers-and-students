package com.base.teachersstudents.service;

import com.base.teachersstudents.entities.Student;

interface IStudentService{
    void saveStudent(Student student);
    Student retrieveStudentById(long id);
    void deleteStudentById(long id);
}
