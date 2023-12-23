package org.jsp.adminbusapp.controller;

import java.util.Optional;

import org.jsp.adminbusapp.dto.Admin;
import org.jsp.adminbusapp.dto.ResponseStructure;
import org.jsp.adminbusapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminController {
	@Autowired
	private AdminService adminService;

	@PostMapping("/admins/save")
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody Admin admin) {
		return adminService.saveAdmin(admin);
	}

	@PutMapping("/admins")
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody Admin admin) {
		return adminService.updateAdmin(admin);
	}

	@PostMapping("/admins/verify-by-phone")
	private ResponseEntity<ResponseStructure<Optional<Admin>>> verifyAdminByPhone(@RequestParam long phone,
			@RequestParam String password) {
		return adminService.verifyAdmin(phone, password);
	}

	@PostMapping("/admins/verify-by-email")
	private ResponseEntity<ResponseStructure<Optional<Admin>>> verifyAdminByEmail(@RequestParam String email,
			@RequestParam String password) {
		return adminService.verifyAdmin(email, password);
	}
	@GetMapping("/admins/{id}")
	private ResponseEntity<ResponseStructure<Optional<Admin>>> findAdminById(@PathVariable int id){
		return adminService.findAdminById(id);
	}

}
