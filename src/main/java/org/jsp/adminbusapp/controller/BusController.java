package org.jsp.adminbusapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.jsp.adminbusapp.dto.Bus;
import org.jsp.adminbusapp.dto.ResponseStructure;
import org.jsp.adminbusapp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusController {

	@Autowired
	private BusService busService;

	@PostMapping("/buses/{admin_id}")
	public ResponseEntity<ResponseStructure<Bus>> addBus(@RequestBody Bus bus, @PathVariable int admin_id) {
		return busService.addBus(bus, admin_id);
	}

	@PutMapping("/buses")
	public ResponseEntity<ResponseStructure<Bus>> updateBus(@RequestBody Bus bus) {
		return busService.updateBus(bus);
	}

	@GetMapping("/buses/by-date-location")
	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByDateToFrom(@RequestParam LocalDate date,
			@RequestParam String to, @RequestParam String from) {
		return busService.findBusByDateFromToLocation(date, from, to);
	}

	@GetMapping("/buses/by-admin/{admin_id}")
	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByAdminId(@PathVariable int admin_id) {
		return busService.findByAdminId(admin_id);
	}

	@GetMapping("/buses/by-bus/{busNo}")
	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByBusNo(@PathVariable String busNo) {
		return busService.findBusByBusNo(busNo);
	}

	@GetMapping("/buses/by-travel/{travelsName}")
	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByTravelsName(@PathVariable String travelsName) {
		return busService.findBusByTravelsName(travelsName);
	}

	@GetMapping("/buses/by-date/{byDate}")
	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByLocationDate(@PathVariable LocalDate byDate) {
		return busService.findBusByDateOfDeparture(byDate);
	}

}
