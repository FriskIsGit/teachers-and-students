package com.base.teachersstudents.repo;

import com.base.teachersstudents.entities.Teacher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{
    List<Teacher> findByName(String name);
    List<Teacher> findByLastname(String lastname);
    Teacher findFirstByNameAndLastname(String name, String lastname);
    List<Teacher> findByNameAndLastname(String name, String lastname);
    List<Teacher> findBySubject(String subject);
}
