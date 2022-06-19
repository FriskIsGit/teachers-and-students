package com.base.teachersstudents;

import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.entities.Teacher;
import com.base.teachersstudents.service.StudentService;
import com.base.teachersstudents.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.util.Iterator;

@SpringBootApplication
public class TeachersStudentsApplication implements CommandLineRunner{
	@Autowired
	TeacherService teacherService;
	@Autowired
	StudentService studentService;
	public static void main(String[] args) {
		SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder(TeachersStudentsApplication.class);
		ConfigurableApplicationContext applicationContext = applicationBuilder.run(args);
	}

	@Override
	public void run(String... args){
		Teacher teacher1 = new Teacher("Joe", "Mama", "joemama@gmail.com", "Math",27);
		Teacher teacher2 = new Teacher("Lauren", "Sawes", "lauriesawes@x.y", "Math",27);
		Teacher teacher3 = new Teacher("Ta", "Ul", "a@x.y", "Bio",34);
		Student student = new Student("Mark", "Tawer", "marktawer@mail.org", "CS",19);
		teacherService.registerTeacher(teacher1);
		teacherService.registerTeacher(teacher2);
		teacherService.registerTeacher(teacher3);
		studentService.registerStudent(student);

		Student invalidStudent = new Student("Mark", "Tawer", "dmail.org", "any",7);
		studentService.registerStudent(invalidStudent);

		System.out.println("Finished");
	}
}