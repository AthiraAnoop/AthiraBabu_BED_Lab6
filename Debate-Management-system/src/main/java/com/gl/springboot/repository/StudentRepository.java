package com.gl.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.springboot.entity.Student;

@Repository
public interface StudentRepository  extends JpaRepository<Student,Long>{

}
