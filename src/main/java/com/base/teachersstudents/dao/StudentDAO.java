package com.base.teachersstudents.dao;

import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StudentDAO implements IStudentDAO{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getStudents(){
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
        studentRepository.deleteById(id);
    }

    @Override
    public Student retrieveByNameAndLastname(String name, String lastname){
        Optional<Student> possiblyStudent = studentRepository.findAll().stream().filter(student -> student.getName().equals(name) && student.getLastname().equals(lastname)).findAny();
        return possiblyStudent.orElse(null);
    }

    @Override
    public List<Student> retrieveByName(String name){
        return studentRepository.findAll().stream().filter(
                student -> student.getName().equals(name)
        ).collect(Collectors.toList());
    }

    @Override
    public List<Student> retrieveByLastname(String lastname){
        return studentRepository.findAll().stream().filter(
                student -> student.getLastname().equals(lastname)
        ).collect(Collectors.toList());
    }

    @Override
    public void saveStudent(Student student){
        if(student == null){
            return;
        }
        studentRepository.save(student);
    }
}