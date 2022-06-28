package com.base.teachersstudents.dao;

import com.base.teachersstudents.entities.Teacher;
import com.base.teachersstudents.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
    public void deleteTeacher(Teacher teacher){
        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher retrieveByNameAndLastname(String name, String lastname){
        return teacherRepository.findFirstByNameAndLastname(name, lastname);
    }

    @Override
    public List<Teacher> retrieveByName(String name){
        return teacherRepository.findByName(name);
    }

    @Override
    public List<Teacher> retrieveByLastname(String lastname){
        return teacherRepository.findByLastname(lastname);
    }

    @Override
    public List<Teacher> retrieveBySubject(String subject){
        return teacherRepository.findBySubject(subject);
    }

    @Override
    public List<Teacher> retrieveByAscending(String fieldName){
        return teacherRepository.findAll(Sort.by(Sort.Direction.ASC, fieldName));
    }

    @Override
    public List<Teacher> retrieveByDescending(String fieldName){
        return teacherRepository.findAll(Sort.by(Sort.Direction.DESC, fieldName));
    }

    @Override
    public Page<Teacher> retrievePage(Pageable pageable){
        return teacherRepository.findAll(pageable);
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
