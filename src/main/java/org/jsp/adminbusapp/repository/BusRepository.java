package org.jsp.adminbusapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.jsp.adminbusapp.dto.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusRepository extends JpaRepository<Bus, Integer> {

	@Query("select b from Bus b where b.dateOfDeparture=?1 and b.fromLocation=?2 and b.toLocation=?3")
	public List<Bus> findByDateOfDepFromToLocation(LocalDate dateOfDeparture, String fromLocation, String toLocation);

	@Query("select b from Bus b where b.admin.id=?1")
	public List<Bus> findByAdminId(int admin_id);

	@Query("select b from Bus b where b.busNo=?1")
	public List<Bus> findByBusNo(String busNo);

	@Query("select b from Bus b where b.travelsName=?1")
	public List<Bus> findByTravelsName(String travelsName);

	@Query("select b from Bus b where b.dateOfDeparture=?1")
	public List<Bus> findByDateOfDeparture(LocalDate dateOfDeparture);

}
