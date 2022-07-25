package com.base.teachersstudents.dao;

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
public class StudentDAOTest{
    @Autowired
    StudentDAO dao;

    @Test
    public void insertsAndRetrievals(){
        assertNotNull(dao);
        String name = "Layla", lastname = "Collymore";
        Student teacher = new Student(name, lastname, "laylacollie@ma.au", "Philosophy", 19);

        int initialSize = dao.retrieveAllByNameAndLastname(name, lastname).size();
        dao.saveStudent(teacher);
        List<Student> retrieved = dao.retrieveAllByNameAndLastname(name, lastname);
        boolean condition = retrieved.size() == initialSize + 1;
        assertTrue(condition);

        Long targetId = teacher.getId();
        assertNotNull(targetId);
        boolean exists = retrieved.stream().anyMatch(aStudent -> aStudent.getId().equals(targetId));
        assertTrue(exists);
    }
    @Test
    public void insertRetrieveAndDelete(){
        assertNotNull(dao);
        String name = "Adam", lastname = "Anderson";
        Student student = new Student(name, lastname, "aandy@gmail.com", "Business", 18);

        long count = dao.studentCount();
        dao.saveStudent(student);
        boolean condition = dao.studentCount() == count + 1;
        assertTrue(condition);

        Long targetId = student.getId();
        assertNotNull(targetId);

        Student theSameTeacher = dao.findById(targetId);
        assertNotNull(theSameTeacher);

        count = dao.studentCount();
        dao.deleteById(targetId);
        assertEquals(dao.studentCount(), count - 1);
    }
}
