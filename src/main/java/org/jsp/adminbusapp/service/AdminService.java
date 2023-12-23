package org.jsp.adminbusapp.service;

import java.util.Optional;

import org.jsp.adminbusapp.dao.AdminDao;
import org.jsp.adminbusapp.dto.Admin;
import org.jsp.adminbusapp.dto.ResponseStructure;
import org.jsp.adminbusapp.exception.AdminNotFoundException;
import org.jsp.adminbusapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		structure.setData(adminDao.save(admin));
		structure.setMessage("Admin Saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.findById(admin.getId());
		if (recAdmin.isPresent()) {
			Admin dbAdmin = recAdmin.get();
			dbAdmin.setName(admin.getName());
			dbAdmin.setEmail(admin.getEmail());
			dbAdmin.setPhone(admin.getPhone());
			dbAdmin.setPassword(admin.getPassword());
			dbAdmin.setGstNo(admin.getGstNo());
			dbAdmin.setTravelsName(admin.getTravelsName());
			structure.setData(adminDao.save(dbAdmin));
			structure.setMessage("Admin Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.ACCEPTED);
		}
		throw new AdminNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Optional<Admin>>> verifyAdmin(long phone, String password) {
		ResponseStructure<Optional<Admin>> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.verify(phone, password);
		if (recAdmin.isPresent()) {
			structure.setData(adminDao.verify(phone, password));
			structure.setMessage("Verification Successful");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Optional<Admin>>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid phone or password");
	}

	public ResponseEntity<ResponseStructure<Optional<Admin>>> verifyAdmin(String email, String password) {
		ResponseStructure<Optional<Admin>> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.verify(email, password);
		if (recAdmin.isPresent()) {
			structure.setData(adminDao.verify(email, password));
			structure.setMessage("Verification Successful");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Optional<Admin>>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid email or password");
	}

	public ResponseEntity<ResponseStructure<Optional<Admin>>> findAdminById(int id) {
		ResponseStructure<Optional<Admin>> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.findById(id);
		if (recAdmin.isPresent()) {
			structure.setData(recAdmin);
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Optional<Admin>>>(structure, HttpStatus.FOUND);
		}
		throw new AdminNotFoundException();
	}

}
