package com.base.teachersstudents.service;

import com.base.teachersstudents.TeachersStudentsApplication;
import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.entities.Teacher;
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
public class StudentTeacherServiceTest{

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentTeacherService studentTeacherService;

    @Test
    public void savedWithCommonService(){
        Teacher teacher1 = new Teacher("Lauren", "Sawes", "lauriesawes@x.y", "Math",27);

        Student student1 = new Student("Mark", "Tawer", "marktawer@mail.org", "CS",19);
        Student student2 = new Student("Johny", "Jason", "johnjson@mail.org", "IT",20);
        Student student3 = new Student("Jeffrey", "Richards", "jeffreyrichie@mail.org", "Art",19);

        assertNotNull(studentTeacherService);
        assertNotNull(studentService);
        assertNotNull(teacherService);

        long initialStudents = studentService.studentCount();
        long initialTeachers = teacherService.teacherCount();

        studentTeacherService.addStudentsToTeacher(teacher1, student1, student2, student3);
        assertEquals(initialStudents + 3, studentService.studentCount());
        assertEquals(initialTeachers + 1, teacherService.teacherCount());

        //clean up
        studentService.deleteStudentById(student1.getId());
        studentService.deleteStudentById(student2.getId());
        studentService.deleteStudentById(student3.getId());
        teacherService.deleteTeacherById(teacher1.getId());

        assertEquals(initialStudents, studentService.studentCount());
        assertEquals(initialTeachers, teacherService.teacherCount());
    }

    @Test
    public void savedWithSeparateServicesThenDelete(){
        assertNotNull(teacherService);
        assertNotNull(studentService);

        long teacherCount = teacherService.teacherCount();
        long studentCount = studentService.studentCount();

        Teacher teacher1 = new Teacher("Gabriel", "Sampson", "simpsongab@x.y", "History",27);
        Teacher teacher2 = new Teacher("Mitchell", "Ewing", "michelleewing@x.y", "Psychology",34);
        Student student1 = new Student("Daniela", "Annabell", "annadaniel@mail.org", "Transport",19);
        //1
        teacherService.saveTeacher(teacher1);
        teacherService.saveTeacher(teacher2);
        //2
        student1.getTeachers().add(teacher1);
        student1.getTeachers().add(teacher2);
        //3
        studentService.saveStudent(student1);
        assertEquals(teacherCount + 2, teacherService.teacherCount());
        assertEquals(studentCount + 1, studentService.studentCount());

        //cleaning up - since both entities reference one another, only the references should be removed along with the student
        studentService.deleteStudentById(student1.getId());
        assertEquals(teacherCount + 2, teacherService.teacherCount());
        assertEquals(studentCount, studentService.studentCount());
        //return initial state
        teacherService.deleteTeacherById(teacher1.getId());
        teacherService.deleteTeacherById(teacher2.getId());
        assertEquals(teacherCount, teacherService.teacherCount());
    }

    @Test
    public void retrievalsTest(){
        assertNotNull(studentService);

        final String BY_NAME = "Juliet";
        Student student1 = new Student(BY_NAME, "Small", "smoljuliet@mail.org", "CS",19);
        Student student2 = new Student("Deja", "Hayes", "dejavu@mail.org", "IT",20);
        Student student3 = new Student("Fernanda", "Puppet", "fernandiepop@mail.org", "Pop music",19);

        int studentsBefore = studentService.getStudentsByName(BY_NAME).size();

        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        studentService.saveStudent(student3);

        List<Student> studentList = studentService.getStudentsByName(BY_NAME);
        assertEquals(studentsBefore + 1, studentList.size());

        Student firstRetrieved =  studentService.getStudentByNameAndLastname("Deja", "Hayes");
        assertNotNull(firstRetrieved);
    }
    @Test
    public void saveStudentsByPersistingTeacher(){
        assertNotNull(teacherService);
        assertNotNull(studentService);

        Teacher aTeacher = new Teacher("Kennedy", "Leonard", "a@x.y", "Technology",35);
        Student student1 = new Student("Ruby", "Mann", "rubymann@mail.org", "Jazz",22);
        Student student2 = new Student("Mikayla", "Hale", "dejavu@mail.org", "Science",23);

        long teachersBefore = teacherService.teacherCount();
        long studentsBefore = studentService.studentCount();
        //Students are not merged until the teacher is merged, also they'll not be persisted if teacher or his student wasn't in the db initially.
        //If this statement appears confusing use StudentTeacherService to resolve many-to-many relationships
        teacherService.saveTeacher(aTeacher);
        aTeacher.getStudents().add(student1);
        aTeacher.getStudents().add(student2);
        teacherService.saveTeacher(aTeacher);

        assertEquals(teachersBefore + 1, teacherService.teacherCount());
        assertEquals(studentsBefore + 2, studentService.studentCount());
    }

    @Test
    public void retrieveTeachersOfStudent(){
        assertNotNull(studentTeacherService);
        assertNotNull(studentService);

        Teacher teacher1 = new Teacher("Tony", "Joseph", "josptt@x.y", "HR",35);
        Teacher teacher2 = new Teacher("Steve", "Moody", "stewinbadmood@x.y", "Aviation",35);
        Teacher teacher3 = new Teacher("Mallory", "Houston", "mallorieh@x.y", "Culture",35);

        Student aStudent = new Student("Chloe", "Ortiz", "chloeornot@mail.org", "Legal",22);
        studentTeacherService.addTeachersToStudent(aStudent, teacher1, teacher2, teacher3);
        Long id = aStudent.getId();
        assertNotNull(id);

        Student retrievedStudent = studentService.getStudentById(id);
        assertNotNull(retrievedStudent);

        assertEquals(3, retrievedStudent.getTeachers().size());
    }
}
