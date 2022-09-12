package tech.wenisch.flightstatus.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class Airport {
	@Schema(description = "Full name of the airport", example = "Cologne Bonn Airport")
	@JsonProperty
	String fullName;
	@Schema(description = "Short name / IATA CODE", example = "CGN")
	@JsonProperty
	String shortName;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
