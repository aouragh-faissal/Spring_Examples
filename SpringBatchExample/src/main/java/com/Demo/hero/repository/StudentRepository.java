package com.Demo.hero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Demo.hero.student.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
