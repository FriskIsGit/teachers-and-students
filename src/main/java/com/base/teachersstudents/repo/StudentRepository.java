package com.base.teachersstudents.repo;

import com.base.teachersstudents.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    List<Student> findByName(String name);
    List<Student> findByLastname(String lastname);
    Student findFirstByNameAndLastname(String name, String lastname);
    List<Student> findByNameAndLastname(String name, String lastname);
    List<Student> findByMajor(String major);
}
