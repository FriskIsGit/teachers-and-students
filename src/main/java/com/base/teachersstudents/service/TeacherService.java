package com.base.teachersstudents.service;

import com.base.teachersstudents.dao.TeacherDAO;
import com.base.teachersstudents.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.base.teachersstudents.service.Constraints.*;

@Component
public class TeacherService implements ITeacherService{

    @Autowired
    private TeacherDAO teacherDAO;

    @Override
    public void registerTeacher(Teacher teacher){
        if(isNameInvalid(teacher.getName())){
            System.err.println("Invalid name");
            return;
        }
        if(isLastnameInvalid(teacher.getLastname())){
            System.err.println("Invalid lastname");
            return;
        }
        if(isEmailInvalid(teacher.getEmail())){
            System.err.println("Invalid email");
            return;
        }
        if(isAgeInvalid(teacher.getAge())){
            System.err.println("Invalid age");
            return;
        }
        teacherDAO.saveTeacher(teacher);
    }

    @Override
    public void deleteTeacher(long id){
        teacherDAO.deleteById(id);
    }
}
