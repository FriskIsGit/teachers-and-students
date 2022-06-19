package com.base.teachersstudents.service;

import com.base.teachersstudents.dao.StudentDAO;
import com.base.teachersstudents.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.base.teachersstudents.service.Constraints.*;

@Service
public class StudentService implements IStudentService{
    @Autowired
    StudentDAO studentDAO;

    @Override
    public void registerStudent(Student student){
        if(isNameInvalid(student.getName())){
            System.err.println("Invalid name");
            return;
        }
        if(isLastnameInvalid(student.getLastname())){
            System.err.println("Invalid lastname");
            return;
        }
        if(isEmailInvalid(student.getEmail())){
            System.err.println("Invalid email");
            return;
        }
        if(isAgeInvalid(student.getAge())){
            System.err.println("Invalid age");
            return;
        }
        studentDAO.saveStudent(student);
    }

    @Override
    public void deleteStudent(long id){
        studentDAO.deleteById(id);
    }
}
