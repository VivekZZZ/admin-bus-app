package org.jsp.adminbusapp.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String travelsName;
	@Column(nullable = false)
	private String busNo;
	@Column(nullable = false)
	private LocalDate dateOfDeparture;
	@Column(nullable = false)
	private int noOfSeats;
	@Column(nullable = false)
	private double costPerSeat;
	@Column(nullable = false)
	private String fromLocation;
	@Column(nullable = false)
	private String toLocation;
	@Column(nullable = false)
	private String imageUrl;
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusNo() {
		return busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	public LocalDate getDateOfDeparture() {
		return dateOfDeparture;
	}

	public void setDateOfDeparture(LocalDate dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public double getCostPerSeat() {
		return costPerSeat;
	}

	public void setCostPerSeat(double costPerSeat) {
		this.costPerSeat = costPerSeat;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@JsonIgnore
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getTravelsName() {
		return travelsName;
	}

	public void setTravelsName(String travelsName) {
		this.travelsName = travelsName;
	}

}
