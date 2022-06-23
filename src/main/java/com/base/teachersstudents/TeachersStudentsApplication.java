package com.base.teachersstudents;

import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.entities.Teacher;
import com.base.teachersstudents.service.StudentService;
import com.base.teachersstudents.service.StudentTeacherService;
import com.base.teachersstudents.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TeachersStudentsApplication implements CommandLineRunner{
	@Autowired
	TeacherService teacherService;
	@Autowired
	StudentService studentService;
	@Autowired
	StudentTeacherService studentTeacherService;
	public static void main(String[] args) {
		SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder(TeachersStudentsApplication.class);

		ConfigurableApplicationContext applicationContext = applicationBuilder.run(args);
	}

	@Override
	public void run(String... args){
		Teacher teacher1 = new Teacher("Lauren", "Sawes", "lauriesawes@x.y", "Math",27);
		Teacher teacher2 = new Teacher("Ta", "Ul", "a@x.y", "Bio",34);

		Student student1 = new Student("Mark", "Tawer", "marktawer@mail.org", "CS",19);
		Student student2 = new Student("Johny", "Jason", "jhnjson@mail.org", "IT",20);
		Student student3 = new Student("Sudden", "Third", "student@mail.org", "Unexpected",19);
		/*//1
		teacherService.saveTeacher(teacher1);
		teacherService.saveTeacher(teacher2);
		//2
		student1.getTeachers().add(teacher1);
		student1.getTeachers().add(teacher2);
		//3
		studentService.saveStudent(student1);*/

		studentTeacherService.addTeacherToManyStudents(teacher1, student1, student2, student3);

		/*student2.getTeachers().add(teacher1);
		student2.getTeachers().add(teacher2);

		student3.getTeachers().add(teacher1);
		student3.getTeachers().add(teacher2);

		studentService.saveStudent(student2);*/

		System.out.println("Finished");
	}
}