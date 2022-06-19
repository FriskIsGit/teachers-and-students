package com.base.teachersstudents;

import com.base.teachersstudents.entities.Teacher;
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
	public static void main(String[] args) {
		SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder(TeachersStudentsApplication.class);

		ConfigurableApplicationContext applicationContext = applicationBuilder.run(args);

		/*Iterator<String> beanNamesIterator = applicationContext.getBeanFactory().getBeanNamesIterator();
		while(beanNamesIterator.hasNext()){
			System.out.println(beanNamesIterator.next());
		}*/
	}

	@Override
	public void run(String... args){
		Teacher teacher1 = new Teacher("Joe", "Mama", "joemama@gmail.com", "Math",27);
		teacherService.registerTeacher(teacher1);

		System.out.println("Finished");
	}
}
