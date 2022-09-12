package tech.wenisch.flightstatus.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class Flight {
	@Schema(description = "time of derparture in yyyy-MM-dd HH:mm:ss format", example = "2022-08-17")
	@JsonProperty
	String departureTime;
	@Schema(description = "time of arrival in yyyy-MM-dd HH:mm:ss format", example = "2022-08-17")
	@JsonProperty
	String arrivalTime;
	Airport derpartureAirport;
	Airport arrivalAirport;
	@Schema(description = "Name of the departure gate", example = "B05")
	@JsonProperty
	String departureGate;
	@Schema(description = "Name of the arrival gate", example = "A12")
	@JsonProperty
	String arrivalGate;
	@Schema(description = "Unique identifier of the flight", example = "F12345")
	@JsonProperty
	String flightNumber;

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Airport getDerpartureAirport() {
		return derpartureAirport;
	}

	public void setDerpartureAirport(Airport derpartureAirport) {
		this.derpartureAirport = derpartureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public String getDepartureGate() {
		return departureGate;
	}

	public void setDepartureGate(String departureGate) {
		this.departureGate = departureGate;
	}

	public String getArrivalGate() {
		return arrivalGate;
	}

	public void setArrivalGate(String arrivalGate) {
		this.arrivalGate = arrivalGate;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

}
