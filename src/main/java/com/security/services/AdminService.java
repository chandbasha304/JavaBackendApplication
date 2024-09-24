package com.security.services;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.entity.Admin;
import com.exceptions.ResourceNotFoundException;
import com.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepo;
	
	 @Autowired
	  private PasswordEncoder encoder;
	
	public Admin addAdmin(Admin admin) {
		
		encoder.encode(admin.getPassword());
		adminRepo.save(admin);
		return admin;
	}

	
	public List<Admin> getAllAdmins() {
		List<Admin> admin = adminRepo.findAll();
		return admin;
	}

	
	public Admin updateAdmin(Admin admin) throws Throwable {
		
		Long adminId = admin.getId();
		Supplier s1= ()->new ResourceNotFoundException("Admin Does not exist in the database");
		Admin c1=adminRepo.findById(adminId).orElseThrow(s1);
		System.out.println(c1+"**********************");
		
		c1.setUsername(admin.getUsername());
		c1.setPassword(encoder.encode(admin.getPassword()));
		c1.setActive(admin.isActive());
		
			c1.setEmail(admin.getEmail());
			adminRepo.save(c1);
			return c1;
	}

	
	public String deleteAdmin(Admin admin) {
		adminRepo.delete(admin);
		return "Deleted";
	}

	
	public String deleteAdminById(Long id) {
		System.out.println("Deleting Admin Id******************8");
		adminRepo.deleteById(id);
		return "Deleted Successfully";
	}

	public Admin getAdminById(Long adminId) {
		return adminRepo.findById(adminId).orElse(null);
	}

}
