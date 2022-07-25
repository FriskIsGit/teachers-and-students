package com.base.teachersstudents.dao;

import com.base.teachersstudents.TeachersStudentsApplication;
import com.base.teachersstudents.entities.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//if import org.junit.jupiter.api.Test; is annotated an exception will be thrown
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TeachersStudentsApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class TeacherDAOTest{
    @Autowired
    TeacherDAO dao;

    @Test
    public void insertsAndRetrievals(){
        assertNotNull(dao);
        String name = "Ashley", lastname = "Ford";
        Teacher teacher = new Teacher(name, lastname, "ashley1273@m.com", "Physics", 23);

        int initialSize = dao.retrieveAllByNameAndLastname(name, lastname).size();
        dao.saveTeacher(teacher);
        List<Teacher> retrieved = dao.retrieveAllByNameAndLastname(name, lastname);
        boolean condition = retrieved.size() == initialSize + 1;
        assertTrue(condition);

        Long targetId = teacher.getId();
        assertNotNull(targetId);
        boolean exists = retrieved.stream().anyMatch(aTeacher -> aTeacher.getId().equals(targetId));
        assertTrue(exists);
    }
    @Test
    public void insertRetrieveAndDelete(){
        assertNotNull(dao);
        String name = "Rita", lastname = "Flores";
        Teacher teacher = new Teacher(name, lastname, "riotflor@mail.com", "Dance", 24);

        long count = dao.teacherCount();
        dao.saveTeacher(teacher);
        boolean condition = dao.teacherCount() == count + 1;
        assertTrue(condition);

        Long targetId = teacher.getId();
        assertNotNull(targetId);

        Teacher theSameTeacher = dao.findById(targetId);
        assertNotNull(theSameTeacher);

        count = dao.teacherCount();
        dao.deleteById(targetId);
        assertEquals(dao.teacherCount(), count - 1);

        Teacher deleted = dao.findById(targetId);
        assertNull(deleted);
    }
}
