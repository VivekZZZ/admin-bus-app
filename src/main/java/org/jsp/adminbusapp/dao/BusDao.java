package org.jsp.adminbusapp.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.adminbusapp.dto.Bus;
import org.jsp.adminbusapp.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {

	@Autowired
	private BusRepository busRepository;

	public Bus addBus(Bus bus) {
		return busRepository.save(bus);
	}

	public List<Bus> findBus(LocalDate dateOfDeparture, String from, String to) {
		return busRepository.findByDateOfDepFromToLocation(dateOfDeparture, from, to);
	}

	public Optional<Bus> findById(int id) {
		return busRepository.findById(id);
	}

	public List<Bus> findBusByAdminId(int admin_id) {
		return busRepository.findByAdminId(admin_id);
	}

	public List<Bus> findByBusNo(String busNo) {
		return busRepository.findByBusNo(busNo);
	}

	public List<Bus> findByTravelsName(String travelsName) {
		return busRepository.findByTravelsName(travelsName);
	}

	public List<Bus> findByDateOfDeparture(LocalDate dateOfDeparture) {
		return busRepository.findByDateOfDeparture(dateOfDeparture);
	}

}
