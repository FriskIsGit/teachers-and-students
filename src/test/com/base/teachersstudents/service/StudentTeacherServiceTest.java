package com.base.teachersstudents.service;

import com.base.teachersstudents.TeachersStudentsApplication;
import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.entities.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
public class StudentTeacherServiceTest{
    private final static Locale LOCALE = Locale.ENGLISH;

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
    public void retrieveByNull(){
        assertNotNull(studentService);
        Student nullNameStudent = new Student(null, "Woodward", "anymail@mail.org", "CS",19);
        studentService.saveStudent(nullNameStudent);
        List<Student> students = studentService.getStudentsByName(null);
        assertEquals(0, students.size());
    }
    @Test
    public void retrievalsTest(){
        assertNotNull(teacherService);
        assertNotNull(studentService);

        final String BY_NAME = "Juliet";
        Teacher teacher1 = new Teacher("Sarai", "Dunlap", "saraidon@x.y", "Comedy",27);
        Teacher teacher2 = new Teacher("Ta", "Ul", "a@x.y", "Bio Engineering",34);

        Student student1 = new Student(BY_NAME, "Small", "smoljuliet@mail.org", "CS",19);
        Student student2 = new Student("Deja", "Hayes", "dejavu@mail.org", "IT",20);
        Student student3 = new Student("Fernanda", "Puppet", "fernandiepop@mail.org", "Pop music",19);

        int studentsBefore = studentService.getStudentsByName(BY_NAME).size();
        teacherService.saveTeacher(teacher1);
        teacherService.saveTeacher(teacher2);

        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        studentService.saveStudent(student3);

        List<Student> studentList = studentService.getStudentsByName(BY_NAME);
        assertEquals(studentsBefore + 1, studentList.size());

        Student firstRetrieved =  studentService.getStudentByNameAndLastname("Deja", "Hayes");
        assertNotNull(firstRetrieved);
    }
    @Test
    public void sortingTest(){
        assertNotNull(teacherService);
        assertNotNull(studentService);

        List<Student> students = studentService.getAllSortedAscendinglyBy("age");
        if(students.size() > 0){
            assertTrue(isSortedByAge(students));
        }

        List<Teacher> teachers = teacherService.getAllSortedDescendinglyBy("name");
        if(teachers.size() > 0){
            assertTrue(isSortedDescendinglyByName(teachers));
        }
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
    public void sortedAscendinglyTest(){
        assertNotNull(studentService);
        List<Student> sortedStudents = studentService.getAllSortedAscendinglyBy("id");
        assertTrue(isSortedById(sortedStudents));
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
    private static String Nothing(){

        return null;
    }
}
