package com.base.teachersstudents.service;

import com.base.teachersstudents.dao.StudentDAO;
import com.base.teachersstudents.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static com.base.teachersstudents.service.Constraints.*;

@Service
public class StudentService implements IStudentService{
    @Autowired
    private StudentDAO studentDAO;

    @Override
    public void saveStudent(Student student){
        if(student == null){
            System.err.println("Null student");
            return;
        }
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
    public Student getStudentById(long id){
        return studentDAO.findById(id);
    }

    @Override
    public List<Student> getStudentsByName(String name){
        if(name == null){
            return Collections.emptyList();
        }
        return studentDAO.retrieveByName(name);
    }

    @Override
    public List<Student> getStudentsByLastname(String lastname){
        if(lastname == null){
            return Collections.emptyList();
        }
        return studentDAO.retrieveByLastname(lastname);
    }

    @Override
    public Student getStudentByNameAndLastname(String name, String lastname){
        if(name == null || lastname == null){
            return null;
        }
        return studentDAO.retrieveByNameAndLastname(name, lastname);
    }

    @Override
    public void deleteStudentById(long id){
        studentDAO.deleteById(id);
    }

    @Override
    public List<Student> getAllSortedAscendinglyBy(String fieldName){
        if(fieldName == null || !fieldExistsInStudent(fieldName)){
            return Collections.emptyList();
        }
        return studentDAO.retrieveByAscending(fieldName);
    }

    @Override
    public List<Student> getAllSortedDescendinglyBy(String fieldName){
        if(fieldName == null || !fieldExistsInStudent(fieldName)){
            return Collections.emptyList();
        }
        return studentDAO.retrieveByDescending(fieldName);
    }

    @Override
    public Page<Student> getStudentsPagedAscendinglyBy(int page, int size, String fieldName){
        if(page < 0 || size < 1 || fieldName == null || !fieldExistsInStudent(fieldName)){
            return Page.empty();
        }
        return studentDAO.retrievePage(PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.ASC, fieldName))
        );
    }

    @Override
    public Page<Student> getStudentsPagedDescendinglyBy(int page, int size, String fieldName){
        if(page < 0 || size < 1 || fieldName == null || !fieldExistsInStudent(fieldName)){
            return Page.empty();
        }
        return studentDAO.retrievePage(PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, fieldName))
        );
    }

    private boolean fieldExistsInStudent(String fieldName){
        Field[] studentFields = Student.class.getDeclaredFields();
        for (Field field : studentFields){
            if(field.getName().equals(fieldName)){
                return true;
            }
        }
        return false;
    }
}
