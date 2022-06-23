package com.base.teachersstudents.dao;

import com.base.teachersstudents.entities.Teacher;

import java.util.List;

interface ITeacherDAO{
    long teacherCount();
    void saveTeacher(Teacher teacher);
    List<Teacher> getAllTeachers();
    Teacher findById(long id);
    void deleteById(long id);
    Teacher retrieveByNameAndLastname(String name, String lastname);
    List<Teacher> retrieveByName(String name);
    List<Teacher> retrieveByLastname(String lastname);
}
