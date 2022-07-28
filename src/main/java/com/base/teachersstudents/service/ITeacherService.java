package com.base.teachersstudents.service;

import com.base.teachersstudents.entities.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;

interface ITeacherService{
    boolean saveTeacher(Teacher teacher);
    Teacher getTeacherById(long id);
    List<Teacher> getTeachersByName(String name);
    List<Teacher> getTeachersByLastname(String lastname);
    Teacher getTeacherByNameAndLastname(String name, String lastname);
    List<Teacher> getAllSortedAscendinglyBy(String fieldName);
    List<Teacher> getAllSortedDescendinglyBy(String fieldName);
    Page<Teacher> getTeachersPagedAscendinglyBy(int page, int size, String fieldName);
    Page<Teacher> getTeachersPagedDescendinglyBy(int page, int size, String fieldName);
    void deleteTeacherById(long id);
    long teacherCount();
}
