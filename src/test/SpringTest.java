import com.base.teachersstudents.TeachersStudentsApplication;
import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.entities.Teacher;
import com.base.teachersstudents.service.StudentService;
import com.base.teachersstudents.service.StudentTeacherService;
import com.base.teachersstudents.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TeachersStudentsApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class SpringTest {
    static{
        Logger.getLogger(SpringTest.class.getName()).setLevel(Level.OFF);
    }
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    StudentTeacherService studentTeacherService;

    // write test cases here
    @Test
    public void savedWithCommonService(){
        Teacher teacher1 = new Teacher("Lauren", "Sawes", "lauriesawes@x.y", "Math",27);

        Student student1 = new Student("Mark", "Tawer", "marktawer@mail.org", "CS",19);
        Student student2 = new Student("Johny", "Jason", "jhnjson@mail.org", "IT",20);
        Student student3 = new Student("Sudden", "Third", "student@mail.org", "Unexpected",19);

        studentTeacherService.addStudentsToTeacher(teacher1, student1, student2, student3);
    }
    @Test
    public void savedWithSeparateServices(){
        Teacher teacher1 = new Teacher("Lauren", "Sawes", "lauriesawes@x.y", "Math",27);
        Teacher teacher2 = new Teacher("Ta", "Ul", "a@x.y", "Bio",34);

        Student student1 = new Student("Mark", "Tawer", "marktawer@mail.org", "CS",19);
        //1
		teacherService.saveTeacher(teacher1);
		teacherService.saveTeacher(teacher2);
		//2
		student1.getTeachers().add(teacher1);
		student1.getTeachers().add(teacher2);
		//3
		studentService.saveStudent(student1);
    }
    @Test
    public void retrievalsTest(){
        Teacher teacher1 = new Teacher("Lauren", "Sawes", "lauriesawes@x.y", "Math",27);
        Teacher teacher2 = new Teacher("Ta", "Ul", "a@x.y", "Bio",34);

        Student student1 = new Student("Mark", "Tawer", "marktawer@mail.org", "CS",19);
        Student student2 = new Student("Johny", "Jason", "jhnjson@mail.org", "IT",20);
        Student student3 = new Student("Sudden", "Third", "student@mail.org", "Unexpected",19);
		teacherService.saveTeacher(teacher1);
		teacherService.saveTeacher(teacher2);

		studentService.saveStudent(student1);
		studentService.saveStudent(student2);
		studentService.saveStudent(student3);

        List<Student> studentList = studentService.getStudentsByName("Tawer");
        System.out.println("By lastname size: " + studentList.size());
        printStudents(studentList);

        Student firstRetrieved =  studentService.getStudentByNameAndLastname("Johny", "Jason");
        printStudent(firstRetrieved);
    }
    @Test
    public void retrieveByNull(){
        Student nullNameStudent = new Student(null, "AnyLastname", "marktawer@mail.org", "CS",19);
        studentService.saveStudent(nullNameStudent);
        List<Student> students = studentService.getStudentsByName(null);
        if(students.size() == 0){
            System.out.println("No students found");
            return;
        }
        printStudents(students);
    }
    @Test
    public void sortingTest(){
        List<Student> students = studentService.getAllSortedAscendinglyBy("age");
        printStudents(students);
        System.out.println();
        List<Teacher> teachers = teacherService.getAllSortedDescendinglyBy("name");
        printTeachers(teachers);
    }
    @Test
    public void retrieveStudentsByTeacher(){
        Teacher aTeacher = teacherService.getTeacherById(7);
        System.out.println("Size: " + aTeacher.getStudents().size());
        printStudents(aTeacher.getStudents());
    }
    @Test
    public void retrieveTeachersByStudent(){
        Student aStudent = studentService.getStudentById(7);
        System.out.println("Size: " + aStudent.getTeachers().size());
        printTeachers(aStudent.getTeachers());
    }
    @Test
    public void updateTest(){
        Student aStudent = studentService.getStudentById(4);
        aStudent.setEmail("anotheremail4@gm.ca");
        studentService.saveStudent(aStudent);
    }
    @Test
    public void paginationTest(){
        //retrieves second page of size 5
        Page<Student> pagedStudents = studentService.getStudentsPagedDescendinglyBy(1, 5, "name");
        List<Student> studentList = pagedStudents.stream().collect(Collectors.toList());
        System.out.println("Paged:");
        printStudents(studentList);

        System.out.println("\nSorted:");
        List<Student> sortedStudents = studentService.getAllSortedDescendinglyBy("name");
        printStudents(sortedStudents);
    }
    @Test
    public void sortedAscendinglyTest(){
        List<Student> sortedStudents = studentService.getAllSortedAscendinglyBy("id");
        printStudents(sortedStudents);
    }

    private static void printStudents(Collection<Student> students){
        for(Student s : students){
            System.out.println(s);
        }
    }
    private static void printTeachers(Collection<Teacher> teachers){
        for(Teacher t : teachers){
            System.out.println(t);
        }
    }
    private static void printStudent(Student student){
        System.out.println(student);
    }
    private static void printTeacher(Teacher teacher){
        System.out.println(teacher);
    }
}
