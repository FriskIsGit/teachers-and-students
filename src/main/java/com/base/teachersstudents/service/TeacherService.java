package com.base.teachersstudents.service;

import com.base.teachersstudents.dao.TeacherDAO;
import com.base.teachersstudents.entities.Teacher;
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
public class TeacherService implements ITeacherService{
    @Autowired
    private TeacherDAO teacherDAO;

    @Override
    public boolean saveTeacher(Teacher teacher){
        if(teacher == null){
            System.err.println("Null teacher");
            return false;
        }
        if(!isNameValid(teacher.getName())){
            System.err.println("Invalid name");
            return false;
        }
        if(!isLastnameValid(teacher.getLastname())){
            System.err.println("Invalid lastname");
            return false;
        }
        if(!isEmailValid(teacher.getEmail())){
            System.err.println("Invalid email");
            return false;
        }
        if(!isAgeValid(teacher.getAge())){
            System.err.println("Invalid age");
            return false;
        }
        teacherDAO.saveTeacher(teacher);
        return true;
    }

    @Override
    public Teacher getTeacherById(long id){
        return teacherDAO.findById(id);
    }

    @Override
    public List<Teacher> getTeachersByName(String name){
        if(name == null){
            return Collections.emptyList();
        }
        return teacherDAO.retrieveByName(name);
    }

    @Override
    public List<Teacher> getTeachersByLastname(String lastname){
        if(lastname == null){
            return Collections.emptyList();
        }
        return teacherDAO.retrieveByLastname(lastname);
    }

    @Override
    public Teacher getTeacherByNameAndLastname(String name, String lastname){
        if(name == null || lastname == null){
            return null;
        }
        return teacherDAO.retrieveByNameAndLastname(name, lastname);
    }

    @Override
    public void deleteTeacherById(long id){
        teacherDAO.deleteById(id);
    }

    @Override
    public long teacherCount(){
        return teacherDAO.teacherCount();
    }

    @Override
    public List<Teacher> getAllSortedAscendinglyBy(String fieldName){
        if(fieldName == null || !fieldExistsInTeacher(fieldName)){
            return Collections.emptyList();
        }
        return teacherDAO.retrieveByAscending(fieldName);
    }

    @Override
    public List<Teacher> getAllSortedDescendinglyBy(String fieldName){
        if(fieldName == null || !fieldExistsInTeacher(fieldName)){
            return Collections.emptyList();
        }
        return teacherDAO.retrieveByDescending(fieldName);
    }

    @Override
    public Page<Teacher> getTeachersPagedAscendinglyBy(int page, int size, String fieldName){
        if(page < 0 || size < 1 || fieldName == null || !fieldExistsInTeacher(fieldName)){
            return Page.empty();
        }
        return getTeachersPagedBy(page, size, fieldName, Sort.Direction.ASC);
    }

    @Override
    public Page<Teacher> getTeachersPagedDescendinglyBy(int page, int size, String fieldName){
        if(page < 0 || size < 1 || fieldName == null || !fieldExistsInTeacher(fieldName)){
            return Page.empty();
        }
        return getTeachersPagedBy(page, size, fieldName, Sort.Direction.DESC);
    }
    private Page<Teacher> getTeachersPagedBy(int page, int size, String fieldName, Sort.Direction direction){
        return teacherDAO.retrievePage(PageRequest.of(
                page,
                size,
                Sort.by(direction, fieldName))
        );
    }

    private boolean fieldExistsInTeacher(String fieldName){
        Field[] teacherFields = Teacher.class.getDeclaredFields();
        for (Field field : teacherFields){
            if(field.getName().equals(fieldName)){
                return true;
            }
        }
        return false;
    }
}
