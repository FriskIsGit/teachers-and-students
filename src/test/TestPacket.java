import com.base.teachersstudents.controller.EntityControllerTest;
import com.base.teachersstudents.dao.StudentDAOTest;
import com.base.teachersstudents.dao.TeacherDAOTest;
import com.base.teachersstudents.service.ConstraintsTest;
import com.base.teachersstudents.service.StudentTeacherServiceTest;
import com.base.teachersstudents.service.StudentServiceTest;
import com.base.teachersstudents.service.TeacherServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StudentDAOTest.class,
        TeacherDAOTest.class,
        StudentTeacherServiceTest.class,
        StudentServiceTest.class,
        TeacherServiceTest.class,
        EntityControllerTest.class,
        ConstraintsTest.class
})
public class TestPacket{
    //org.junit.runners.model.InvalidTestClassError: Invalid test class 'TestPacket':
    //1. No runnable methods
}
