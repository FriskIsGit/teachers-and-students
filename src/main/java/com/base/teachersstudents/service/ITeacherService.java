package com.base.teachersstudents.service;

import com.base.teachersstudents.entities.Teacher;

interface ITeacherService{
    void registerTeacher(Teacher teacher);
    void deleteTeacher(long id);
}
