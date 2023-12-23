package org.jsp.adminbusapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.adminbusapp.dao.AdminDao;
import org.jsp.adminbusapp.dao.BusDao;
import org.jsp.adminbusapp.dto.Admin;
import org.jsp.adminbusapp.dto.Bus;
import org.jsp.adminbusapp.dto.ResponseStructure;
import org.jsp.adminbusapp.exception.AdminNotFoundException;
import org.jsp.adminbusapp.exception.BusNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusService {

	@Autowired
	private BusDao busDao;
	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Bus>> addBus(Bus bus, int admin_id) {
		ResponseStructure<Bus> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.findById(admin_id);
		if (recAdmin != null) {
			Admin admin = recAdmin.get();
			admin.getBuses().add(bus);
			bus.setAdmin(admin);
			adminDao.save(admin);
			structure.setData(busDao.addBus(bus));
			structure.setMessage("Bus added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure, HttpStatus.CREATED);
		}
		throw new AdminNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Bus>> updateBus(Bus bus) {
		ResponseStructure<Bus> structure = new ResponseStructure<>();
		Optional<Bus> recBus = busDao.findById(bus.getId());
		if (recBus != null) {
			Bus dbBus = recBus.get();
			dbBus.setBusNo(bus.getBusNo());
			dbBus.setTravelsName(bus.getTravelsName());
			dbBus.setCostPerSeat(bus.getCostPerSeat());
			dbBus.setDateOfDeparture(bus.getDateOfDeparture());
			dbBus.setFromLocation(bus.getFromLocation());
			dbBus.setToLocation(bus.getToLocation());
			dbBus.setNoOfSeats(bus.getNoOfSeats());
			dbBus.setImageUrl(bus.getImageUrl());
			structure.setData(busDao.addBus(dbBus));
			structure.setMessage("Bus updated");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure, HttpStatus.CREATED);
		}
		throw new BusNotFoundException("invalid Id");
	}

	public ResponseEntity<ResponseStructure<List<Bus>>> findByAdminId(int admin_id) {
		ResponseStructure<List<Bus>> structure = new ResponseStructure<>();
		List<Bus> dbBuses = busDao.findBusByAdminId(admin_id);
		if (dbBuses.size() > 0) {
			structure.setData(dbBuses);
			structure.setMessage("Bus Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure, HttpStatus.FOUND);
		}
		throw new AdminNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByDateFromToLocation(LocalDate date, String from,
			String to) {
		ResponseStructure<List<Bus>> structure = new ResponseStructure<>();
		List<Bus> dbBuses = busDao.findBus(date, from, to);
		if (dbBuses.size() > 0) {
			structure.setData(dbBuses);
			structure.setMessage("Bus Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure, HttpStatus.FOUND);
		}
		throw new BusNotFoundException("invalid date to from location");
	}

	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByBusNo(String busNo) {
		ResponseStructure<List<Bus>> structure = new ResponseStructure<>();
		List<Bus> dbBuses = busDao.findByBusNo(busNo);
		if (dbBuses.size() > 0) {
			structure.setData(dbBuses);
			structure.setMessage("Bus Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure, HttpStatus.FOUND);
		}
		throw new BusNotFoundException("invalid bus no");
	}

	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByTravelsName(String travelsName) {
		ResponseStructure<List<Bus>> structure = new ResponseStructure<>();
		List<Bus> dbBuses = busDao.findByTravelsName(travelsName);
		if (dbBuses.size() > 0) {
			structure.setData(dbBuses);
			structure.setMessage("Bus Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure, HttpStatus.FOUND);
		}
		throw new BusNotFoundException("Invalid travels name");
	}

	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByDateOfDeparture(LocalDate dateOfDeparture) {
		ResponseStructure<List<Bus>> structure = new ResponseStructure<>();
		List<Bus> dbBuses = busDao.findByDateOfDeparture(dateOfDeparture);
		if (dbBuses.size() > 0) {
			structure.setData(dbBuses);
			structure.setMessage("Bus Found");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure, HttpStatus.FOUND);
		}
		throw new BusNotFoundException("Invalid date of departure");
	}
}
