package com.base.teachersstudents.service;

import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

//resolves relationships between students and teachers by merging students who own the relation
@Service
public class StudentTeacherService implements IStudentTeacherService{
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Override
    public void addTeachersToStudent(Student student, Teacher... teachers){
        addTeachersToStudentImpl(student, Arrays.stream(teachers));
    }

    @Override
    public void addTeachersToStudent(Student student, List<Teacher> teacherList){
        addTeachersToStudentImpl(student, teacherList.stream());
    }

    private void addTeachersToStudentImpl(Student student, Stream<Teacher> teacherStream){
        Set<Teacher> teachersOfStudent = student.getTeachers();
        teacherStream.forEach(
                teacher -> {
                    teacherService.saveTeacher(teacher);
                    teachersOfStudent.add(teacher);
                }
        );
        //merge or add student
        studentService.saveStudent(student);
    }

    @Override
    public void addStudentsToTeacher(Teacher teacher, Student... students){
        addStudentsToTeacherImpl(teacher, Arrays.stream(students));
    }

    @Override
    public void addStudentsToTeacher(Teacher teacher, List<Student> studentList){
        addStudentsToTeacherImpl(teacher, studentList.stream());
    }
    private void addStudentsToTeacherImpl(Teacher teacher, Stream<Student> studentStream){
        teacherService.saveTeacher(teacher);
        studentStream.forEach(
                student -> {
                    student.getTeachers().add(teacher);
                    //merge or add each student
                    studentService.saveStudent(student);
                }
        );
    }
}
