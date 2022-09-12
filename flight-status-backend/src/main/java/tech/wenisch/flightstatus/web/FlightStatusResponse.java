package tech.wenisch.flightstatus.web;

import java.util.List;

import tech.wenisch.flightstatus.models.Flight;

public class FlightStatusResponse {

	List<Flight> flight;

	public List<Flight> getFlight() {
		return flight;
	}

	public void setFlight(List<Flight> flight) {
		this.flight = flight;
	}



}
