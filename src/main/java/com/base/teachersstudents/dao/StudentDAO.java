package com.base.teachersstudents.dao;

import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentDAO implements IStudentDAO{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @Override
    public Student findById(long id){
        Optional<Student> possiblyStudent = studentRepository.findById(id);
        //if present .get(), else null
        return possiblyStudent.orElse(null);
    }

    @Override
    public void deleteById(long id){
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }
    }

    @Override
    public void deleteStudent(Student student){
        studentRepository.delete(student);
    }

    @Override
    public Student retrieveByNameAndLastname(String name, String lastname){
        return studentRepository.findFirstByNameAndLastname(name, lastname);
    }

    @Override
    public List<Student> retrieveByName(String name){
        return studentRepository.findByName(name);
    }

    @Override
    public List<Student> retrieveByLastname(String lastname){
        return studentRepository.findByLastname(lastname);
    }

    @Override
    public List<Student> retrieveByMajor(String major){
        return studentRepository.findByMajor(major);
    }

    @Override
    public long studentCount(){
        return studentRepository.count();
    }

    @Override
    public void saveStudent(Student student){
        studentRepository.save(student);
    }
}
