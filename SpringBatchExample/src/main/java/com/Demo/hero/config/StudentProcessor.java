package com.Demo.hero.config;

import org.springframework.batch.item.ItemProcessor;

import com.Demo.hero.student.Student;

public class StudentProcessor implements ItemProcessor<Student, Student> {

	@Override
	public Student process(Student student) throws Exception {
		student.setId(null);
		return student;
	}

}
