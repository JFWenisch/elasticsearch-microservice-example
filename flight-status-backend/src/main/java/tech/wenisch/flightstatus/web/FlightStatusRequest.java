package tech.wenisch.flightstatus.web;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class FlightStatusRequest {
	@Schema(required = true, description = "Unique identifier of the flight", example = "F12345")
	@JsonProperty
	String flightNumber;

	@Schema(required = true, description = "Date of the flight in yyyy-MM-dd format", example = "2022-08-17")
	@JsonProperty
	String travelDate;

	public FlightStatusRequest() {

	}

	public FlightStatusRequest(String flightNumber, String travelDate) {
		this.flightNumber = flightNumber;
		this.travelDate = travelDate;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

}
