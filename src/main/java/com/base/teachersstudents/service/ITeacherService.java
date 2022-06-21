package com.base.teachersstudents.service;

import com.base.teachersstudents.entities.Teacher;

interface ITeacherService{
    void saveTeacher(Teacher teacher);
    Teacher retrieveTeacherById(long id);
    void deleteTeacherById(long id);
}
