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
    public void addManyTeachersToStudent(Student student, Teacher... teachers){
        addManyTeachersToStudentImpl(student, Arrays.stream(teachers));
    }

    @Override
    public void addManyTeachersToStudent(Student student, List<Teacher> teacherList){
        addManyTeachersToStudentImpl(student, teacherList.stream());
    }

    private void addManyTeachersToStudentImpl(Student student, Stream<Teacher> teacherStream){
        Set<Teacher> teachersBelongingToStudent = student.getTeachers();
        teacherStream.forEach(
                teacher -> {
                    teacherService.saveTeacher(teacher);
                    teachersBelongingToStudent.add(teacher);
                }
        );
        //merge or add student
        studentService.saveStudent(student);
    }

    @Override
    public void addTeacherToManyStudents(Teacher teacher, Student... students){
        addTeacherToManyStudentsImpl(teacher, Arrays.stream(students));
    }

    @Override
    public void addTeacherToManyStudents(Teacher teacher, List<Student> studentList){
        addTeacherToManyStudentsImpl(teacher, studentList.stream());
    }
    private void addTeacherToManyStudentsImpl(Teacher teacher, Stream<Student> studentStream){
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
