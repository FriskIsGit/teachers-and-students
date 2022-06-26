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

	public static void main(String[] args) {
		SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder(TeachersStudentsApplication.class);

		ConfigurableApplicationContext applicationContext = applicationBuilder.run(args);

		try{
			System.out.println("Make app run indefinitely");
			Thread.currentThread().join();
		}catch (InterruptedException interruptExc){
			System.err.println("App not running");
		}
	}

	@Override
	public void run(String... args){

		System.out.println("Finished");
	}
}