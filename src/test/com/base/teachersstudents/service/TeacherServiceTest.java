package com.base.teachersstudents.service;

import com.base.teachersstudents.TeachersStudentsApplication;
import com.base.teachersstudents.entities.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.Collator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TeachersStudentsApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class TeacherServiceTest{
    private final static Locale LOCALE = Locale.ENGLISH;
    @Autowired
    private TeacherService teacherService;

    @Test
    public void saveAndDelete(){
        assertNotNull(teacherService);

        Teacher teacher1 = new Teacher("Sarai", "Dunlap", "saraidon@x.y", "Comedy",27);
        Teacher teacher2 = new Teacher("Ta", "Ul", "a@x.y", "Bio Engineering",34);
        long countBefore = teacherService.teacherCount();
        teacherService.saveTeacher(teacher1);
        teacherService.saveTeacher(teacher2);
        assertEquals(countBefore + 2, teacherService.teacherCount());

        teacherService.deleteTeacherById(teacher1.getId());
        teacherService.deleteTeacherById(teacher2.getId());
        assertEquals(countBefore, teacherService.teacherCount());
    }
    @Test
    public void sortingTest(){
        assertNotNull(teacherService);

        List<Teacher> teachers = teacherService.getAllSortedDescendinglyBy("name");
        if(teachers.size() > 0){
            assertTrue(isSortedDescendinglyByName(teachers));
        }
    }
    @Test
    public void paginationTest(){
        assertNotNull(teacherService);

        long teachersBefore = teacherService.teacherCount();
        if(teachersBefore < 10){
            for (int i = 0; i < 10-teachersBefore; i++){
                String randFN = randomSeq(3);
                String randLN = randomSeq(3);
                Teacher teacher = new Teacher(
                        randFN,
                        randLN,
                        randFN+randLN+"@mail.com",
                        randomSeq(4),
                        randomNum(18,25));
                teacherService.saveTeacher(teacher);
            }
        }
        //retrieves second page of size 5
        Page<Teacher> pagedTeachers = teacherService.getTeachersPagedDescendinglyBy(1, 5, "name");
        List<Teacher> teachers = pagedTeachers.stream().collect(Collectors.toList());

        assertEquals(5, teachers.size());
        assertTrue(isSortedDescendinglyByName(teachers));
    }

    @Test
    public void updateTeacherTest(){
        assertNotNull(teacherService);

        Teacher aTeacher = new Teacher("Tony", "Montana", "antoniomontana@x.y", "Business",34);
        teacherService.saveTeacher(aTeacher);
        Long id = aTeacher.getId();
        assertNotNull(id);

        final String NEW_EMAIL = "replacedemail@amail.ca";
        Teacher retrievedTeacher = teacherService.getTeacherById(id);
        retrievedTeacher.setEmail(NEW_EMAIL);
        teacherService.saveTeacher(retrievedTeacher);

        //retrieve again and check if email was changed
        retrievedTeacher = teacherService.getTeacherById(id);
        assertEquals(NEW_EMAIL, retrievedTeacher.getEmail());
    }
    private static boolean isSortedDescendinglyByName(List<Teacher> teachers){
        if(teachers.size() == 1){
            return true;
        }
        String previousName = "";
        boolean first = true;
        Collator collator = Collator.getInstance(LOCALE);
        for (Teacher teacher : teachers){
            String currentName = teacher.getName();
            if(first){
                first = false;
                previousName = currentName;
                continue;
            }
            if(collator.compare(previousName, currentName) >= 0){
                previousName = currentName;
            }
            else
                return false;
        }
        return true;
    }
    public static int randomNum(int min, int max){
        return (int)(Math.random()*(max-min+1))+min;
    }
    private static String randomSeq(int len){
        StringBuilder seq = new StringBuilder();
        seq.append((char)randomNum(65, 90));
        for (int i = 0; i < len; i++){
            seq.append((char)randomNum(97, 122));
        }
        return seq.toString();
    }
}
