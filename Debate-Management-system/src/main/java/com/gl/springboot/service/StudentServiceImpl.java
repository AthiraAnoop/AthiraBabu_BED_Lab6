package com.gl.springboot.service;

import java.util.List;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.gl.springboot.entity.Student;
import com.gl.springboot.entity.User;
import com.gl.springboot.repository.StudentRepository;
import com.gl.springboot.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studentRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<Student> findAll() {
		List<Student> students=studentRepository.findAll();
		return students;
	}

	@Transactional
	public Student findById(long theId) {
		Student student =new Student();
		student=studentRepository.findById(theId).get();
		return student;
	}

	@Transactional
	public void save(Student theStudent) {
		studentRepository.save(theStudent);
	}

	@Override
	public void deleteById(long theId) {
		studentRepository.deleteById(theId);
		
	}

	
	public String addUser(User theUser) {
		theUser.setUsername(theUser.getUsername());
		theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
		theUser.setRoles(theUser.getRoles());
		userRepository.save(theUser);
		return "User add to the system";
	}

}
