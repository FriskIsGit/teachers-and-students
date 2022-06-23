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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Level;
import java.util.logging.Logger;

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
    public void studentAndTeacherServiceTest(){
        Teacher teacher1 = new Teacher("Lauren", "Sawes", "lauriesawes@x.y", "Math",27);
        Teacher teacher2 = new Teacher("Ta", "Ul", "a@x.y", "Bio",34);

        Student student1 = new Student("Mark", "Tawer", "marktawer@mail.org", "CS",19);
        Student student2 = new Student("Johny", "Jason", "jhnjson@mail.org", "IT",20);
        Student student3 = new Student("Sudden", "Third", "student@mail.org", "Unexpected",19);

        studentTeacherService.addTeacherToManyStudents(teacher1, student1, student2, student3);
    }
    @Test
    public void savedOneByOne(){
        Teacher teacher1 = new Teacher("Lauren", "Sawes", "lauriesawes@x.y", "Math",27);
        Teacher teacher2 = new Teacher("Ta", "Ul", "a@x.y", "Bio",34);

        Student student1 = new Student("Mark", "Tawer", "marktawer@mail.org", "CS",19);
        Student student2 = new Student("Johny", "Jason", "jhnjson@mail.org", "IT",20);
        Student student3 = new Student("Sudden", "Third", "student@mail.org", "Unexpected",19);
        //1
		teacherService.saveTeacher(teacher1);
		teacherService.saveTeacher(teacher2);
		//2
		student1.getTeachers().add(teacher1);
		student1.getTeachers().add(teacher2);
		//3
		studentService.saveStudent(student1);
    }
}
