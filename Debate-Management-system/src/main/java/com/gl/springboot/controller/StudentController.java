package com.gl.springboot.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.springboot.entity.Student;
import com.gl.springboot.entity.User;
import com.gl.springboot.service.StudentService;

@Controller
@RequestMapping("/StudentManagement")
public class StudentController {
    
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/error")
    public String handleError() {
        return "error"; // Assuming error.html is your custom error page
    }
	
	@RequestMapping("/logout")
    public String logout() {
        // Additional logout logic if needed
        return "redirect:/login?logout"; // Redirect to login page with logout parameter
    }
	
	@PostMapping("/new")
	public String addNewUser(@RequestBody User userinfo) {
		return studentService.addUser(userinfo);
	}
	
	
	@RequestMapping("/list")
	public String listStudents(Model model) {
		List<Student> students=studentService.findAll();
		model.addAttribute("Students",students);
		return "list-students";
	}
	
	@RequestMapping("/addForm")
	public String showForm(Model model) {
		Student student= new Student();
		model.addAttribute("Student",student);
		return "student-form";
	}
	
	@RequestMapping("/updateForm/{id}")
	public String updateForm(@PathVariable(value="id") long id,Model model) {
		
		Student theStudent= studentService.findById(id);
		model.addAttribute("Student",theStudent);
		return "student-form";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@RequestParam("id") long id,@ModelAttribute("Student") Student student) {
		
		if(id !=0) {
			
			Student getStudent=studentService.findById(id);
			getStudent.setFirstName(student.getFirstName());
			getStudent.setLastName(student.getLastName());
			getStudent.setCourse(student.getCourse());
			getStudent.setCountry(student.getCountry());
			studentService.save(getStudent);
			return "redirect:/StudentManagement/list";
		}
		studentService.save(student);
		return "redirect:/StudentManagement/list";
	}
	
	@RequestMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable(value="id") long id) {
		studentService.deleteById(id);
		return "redirect:/StudentManagement/list";
	}
}
