package tech.wenisch.flightstatus.web;

import java.util.List;

import tech.wenisch.flightstatus.models.Flight;

public class FlightStatusResponse {

	List<Flight> flights;

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flight) {
		this.flights = flight;
	}



}
