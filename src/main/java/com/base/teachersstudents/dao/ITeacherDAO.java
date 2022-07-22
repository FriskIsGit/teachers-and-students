package com.base.teachersstudents.dao;

import com.base.teachersstudents.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface ITeacherDAO{
    long teacherCount();
    void saveTeacher(Teacher teacher);
    List<Teacher> getAllTeachers();
    Teacher findById(long id);
    void deleteById(long id);
    void deleteTeacher(Teacher teacher);
    Teacher retrieveByNameAndLastname(String name, String lastname);
    List<Teacher> retrieveByName(String name);
    List<Teacher> retrieveByLastname(String lastname);
    List<Teacher> retrieveAllByNameAndLastname(String name, String lastname);
    List<Teacher> retrieveBySubject(String subject);
    List<Teacher> retrieveByAscending(String fieldName);
    List<Teacher> retrieveByDescending(String fieldName);
    Page<Teacher> retrievePage(Pageable pageable);
}
