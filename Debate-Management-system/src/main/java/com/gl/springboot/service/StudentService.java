package com.gl.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.springboot.entity.Student;
import com.gl.springboot.entity.User;

@Service
public interface StudentService {
	
	public List<Student> findAll();
	
	
	public Student findById(long theId);
	
	public void save(Student theStudent);
	
	public void deleteById(long theId);
	
	public String addUser(User theUser);
}
