package com.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.entity.Admin;
import com.security.services.AdminService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	
	@GetMapping("/signup")
	public ModelAndView signUpAccount()
	{
		return new ModelAndView("");
	}
	
	
	@GetMapping("/first")
	public ModelAndView startProcess()
	{
		System.out.println("First Process");
		return new ModelAndView("main");
	}
	
	@GetMapping("/AllAdmins")
	public List<Admin> listAdmins(){
		return adminService.getAllAdmins();
		}
	
	@GetMapping("/{Id}")
	public Admin getAdminById(@PathVariable Long Id) {
		return adminService.getAdminById(Id);
	}
	
	@PostMapping("/addAdmin")
	public Admin addAdmin(@Valid @RequestBody Admin admin ) {
		return adminService.addAdmin(admin);
	}
	
	@PutMapping("/updateAdmin")
	public Admin updateAdmin(@RequestBody Admin admin) throws Throwable {
		return adminService.updateAdmin(admin);
	}
	
	
	
	@DeleteMapping("/deleteAdminById/{Id}")
	
	
	public String deleteAdminById(@PathVariable Long Id) {
		
		System.out.println("Controller Layer*****************");
		return adminService.deleteAdminById(Id);
	}

}
