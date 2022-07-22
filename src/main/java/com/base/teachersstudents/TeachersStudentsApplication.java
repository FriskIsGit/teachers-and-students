package com.base.teachersstudents;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TeachersStudentsApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplicationBuilder appBuilder = new SpringApplicationBuilder(TeachersStudentsApplication.class);
		ConfigurableApplicationContext appContext = appBuilder.run(args);
	}

	@Override
	public void run(String... args){

		System.out.println("Finished");
	}
	private void runIndefinitely(){
		try{
			System.out.println("Make app run indefinitely");
			Thread.currentThread().join();
		}catch (InterruptedException interruptExc){
			System.err.println("App not running");
		}
	}
}