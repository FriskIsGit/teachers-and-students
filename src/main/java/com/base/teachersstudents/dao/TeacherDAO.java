package com.base.teachersstudents.dao;

import com.base.teachersstudents.entities.Teacher;
import com.base.teachersstudents.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TeacherDAO implements ITeacherDAO{
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(long id){
        Optional<Teacher> possiblyTeacher = teacherRepository.findById(id);
        //if present .get(), else null
        return possiblyTeacher.orElse(null);
    }

    @Override
    public void deleteById(long id){
        if(teacherRepository.existsById(id)){
            teacherRepository.deleteById(id);
        }
    }

    @Override
    public Teacher retrieveByNameAndLastname(String name, String lastname){
        Optional<Teacher> possiblyTeacher = teacherRepository.findAll().stream().filter(
                teacher -> teacher.getName().equals(name) && teacher.getLastname().equals(lastname)
        ).findAny();
        return possiblyTeacher.orElse(null);
    }

    @Override
    public List<Teacher> retrieveByName(String name){
        return teacherRepository.findAll().stream().filter(
                teacher -> teacher.getName().equals(name)
        ).collect(Collectors.toList());
    }

    @Override
    public List<Teacher> retrieveByLastname(String lastname){
        return teacherRepository.findAll().stream().filter(
                teacher -> teacher.getLastname().equals(lastname)
        ).collect(Collectors.toList());
    }

    @Override
    public long teacherCount(){
        return teacherRepository.count();
    }

    @Override
    public void saveTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }
}
