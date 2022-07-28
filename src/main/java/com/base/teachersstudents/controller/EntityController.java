package com.base.teachersstudents.controller;

import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.entities.Teacher;
import com.base.teachersstudents.service.StudentService;
import com.base.teachersstudents.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RequestMapping("/api/v1")
@RestController
//equivalent to @Controller and @ResponseBody
public class EntityController implements IEntityController{
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;

    @GetMapping("/teachers/count")
    public long teacherCount(){
        return teacherService.teacherCount();
    }

    @GetMapping("/students/count")
    public long studentCount(){
        return studentService.studentCount();
    }

    @GetMapping("/teachers")
    public List<Teacher> teachersByName(@RequestParam(value = "name", required = false) String name){
        if(name == null){
            return teacherService.getAllSortedAscendinglyBy("id");
        }
        return teacherService.getTeachersByName(name);
    }

    @GetMapping("/students")
    public List<Student> studentsByName(@RequestParam(value = "name", required = false) String name){
        if(name == null){
            return studentService.getAllSortedAscendinglyBy("id");
        }
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<Teacher> teacherById(@PathVariable long id){
        Teacher teacher = teacherService.getTeacherById(id);
        if(teacher == null){
            return ResponseEntity.notFound().build();
        }
        System.out.println("Teacher retrieved");
        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> studentById(@PathVariable long id){
        Student student = studentService.getStudentById(id);
        if(student == null){
            return ResponseEntity.notFound().build();
        }
        System.out.println("Student retrieved");
        return ResponseEntity.ok(student);
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
                "/students?name={name}<br>" +
                "/teachers/?name={name}<br>" +
                "/student/{id}/teachers<br>" +
                "/teacher/{id}/students<br>" +
                "/student/{id}<br>" +
                "/teacher/{id}<br>";
    }

    //expected header  Content-Type: application/json
    @PostMapping(path = "/student", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> postStudent(@RequestBody Student student){
        if(studentService.saveStudent(student)){
            System.out.println("Posted student with id: " + student.getId());
            return ResponseEntity.status(201).body("Student created");
        }
        System.out.println("Failed to post student");
        return ResponseEntity.unprocessableEntity().build();

    }

    @PostMapping(path = "/teacher", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> postTeacher(@RequestBody Teacher teacher){
        if(teacherService.saveTeacher(teacher)){
            System.out.println("Posted teacher with id: " + teacher.getId());
            return ResponseEntity.status(201).body("Teacher created");
        }
        System.out.println("Failed to post teacher");
        return ResponseEntity.unprocessableEntity().build();
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
