package com.base.teachersstudents.service;

import com.base.teachersstudents.TeachersStudentsApplication;
import com.base.teachersstudents.entities.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TeachersStudentsApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class StudentServiceTest{
    @Autowired
    private StudentService studentService;

    @Test
    public void sortedAscendinglyById(){
        assertNotNull(studentService);

        List<Student> sortedStudents = studentService.getAllSortedAscendinglyBy("id");
        if(sortedStudents.size() > 0){
            assertTrue(isSortedByAge(sortedStudents));
        }
        assertTrue(isSortedById(sortedStudents));
    }
    @Test
    public void sortingAscendinglyByAge(){
        assertNotNull(studentService);

        List<Student> students = studentService.getAllSortedAscendinglyBy("age");
        if(students.size() > 0){
            assertTrue(isSortedByAge(students));
        }
    }
    @Test
    public void retrieveByNull(){
        assertNotNull(studentService);

        Student nullNameStudent = new Student(null, "Woodward", "anymail@mail.org", "CS",19);
        studentService.saveStudent(nullNameStudent);
        List<Student> students = studentService.getStudentsByName(null);
        assertEquals(0, students.size());
    }
    @Test
    public void retrieveByName(){
        assertNotNull(studentService);

        final String BY_NAME = "John";
        Student student1 = new Student(BY_NAME, "Huffman", "jhuff@mail.org", "Logic",21);
        Student student2 = new Student("Malcolm", "Calderon", "malcolmcaldron@mail.org", "Medicine",24);
        Student student3 = new Student("Rolland", "Codger", "oldcodger@mail.org", "Journalism",23);

        long matchingCount = studentService.getStudentsByName(BY_NAME).size();
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        studentService.saveStudent(student3);

        List<Student> matchingStudents = studentService.getStudentsByName(BY_NAME);
        assertEquals(matchingCount + 1, matchingStudents.size());
        matchingStudents.forEach(student -> assertEquals(BY_NAME, student.getName()));
    }

    private static boolean isSortedById(List<Student> students){
        Long previousId = (long) -1;
        for (Student student : students){
            if(previousId <= student.getId()){
                previousId = student.getId();
            }
            else
                return false;
        }
        return true;
    }
    private static boolean isSortedByAge(List<Student> students){
        int previousAge = -1;
        for (Student student : students){
            if(previousAge <= student.getAge()){
                previousAge = student.getAge();
            }
            else
                return false;
        }
        return true;
    }
}
