package com.base.teachersstudents.controller;

import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.entities.Teacher;
import com.base.teachersstudents.service.StudentService;
import com.base.teachersstudents.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/api/v1")
@RestController
//equivalent to @Controller and @ResponseBody
public class EntityController implements IEntityController{
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public List<Student> allStudents() {
        return studentService.getAllSortedAscendinglyBy("id");
    }
    //TODO @RequestParam(value = "name")

    @GetMapping("/teachers/count")
    public long teacherCount(){
        return teacherService.teacherCount();
    }

    @GetMapping("/students/count")
    public long studentCount(){
        return studentService.studentCount();
    }

    @GetMapping("/teachers")
    public List<Teacher> allTeachers() {
        return teacherService.getAllSortedAscendinglyBy("id");
    }

    @GetMapping("/teachers/{name}")
    public List<Teacher> teachersByName(@PathVariable String name){
        return teacherService.getTeachersByName(name);
    }

    @GetMapping("/students/{name}")
    public List<Student> studentsByName(@PathVariable String name){
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/teacher/{id}")
    public Teacher teacherById(@PathVariable long id){
        return teacherService.getTeacherById(id);
    }

    @GetMapping("/student/{id}")
    public Student studentById(@PathVariable long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/student/{id}/teachers")
    public Set<Teacher> teachersOfStudent(@PathVariable long id){
        Student s = studentService.getStudentById(id);
        if(s == null){
            return Collections.emptySet();
        }
        return s.getTeachers();
    }

    @GetMapping("/teacher/{id}/students")
    public Set<Student> studentsOfTeacher(@PathVariable long id){
        Teacher t = teacherService.getTeacherById(id);
        if(t == null){
            return Collections.emptySet();
        }
        return t.getStudents();
    }

    @GetMapping(path = {"/", ""})
    public String APIv1Base(){
        return "Welcome to API v1 <br>" +
                "Available endpoints: <br>" +
                "/students<br>" +
                "/teachers<br>" +
                "/students/count<br>" +
                "/teachers/count<br>" +
                "/students/{name}<br>" +
                "/teachers/{name}<br>" +
                "/student/{id}/teachers<br>" +
                "/teacher/{id}/students<br>" +
                "/student/{id}<br>" +
                "/teacher/{id}<br>";
    }

    //expected header  Content-Type: application/json
    @PostMapping(path = "/student", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void postStudent(@RequestBody Student student){
        studentService.saveStudent(student);
        System.out.println("Posted student with id: " + student.getId());
    }

    @PostMapping(path = "/teacher", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void postTeacher(@RequestBody Teacher teacher){
        teacherService.saveTeacher(teacher);
        System.out.println("Posted teacher with id: " + teacher.getId());
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudentById(@PathVariable long id){
        studentService.deleteStudentById(id);
    }

    @DeleteMapping("/teacher/{id}")
    public void deleteTeacherById(@PathVariable long id){
        teacherService.deleteTeacherById(id);
    }
}
