package com.base.teachersstudents.service;

import com.base.teachersstudents.entities.Teacher;

import java.util.List;

interface ITeacherService{
    void saveTeacher(Teacher teacher);
    Teacher getTeacherById(long id);
    List<Teacher> getTeachersByName(String name);
    List<Teacher> getTeachersByLastname(String lastname);
    Teacher getTeacherByNameAndLastname(String name, String lastname);
    List<Teacher> getAllSortedAscendinglyBy(String fieldName);
    List<Teacher> getAllSortedDescendinglyBy(String fieldName);
    void deleteTeacherById(long id);
}
