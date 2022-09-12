package tech.wenisch.flightstatus.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import tech.wenisch.flightstatus.FlightStatusManager;

@RestController
@EnableAutoConfiguration
public class Controller {

	@Operation(summary = "Returns HTTP Status 200 if the service up and running")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Service is up and running", content = @Content) })
	@GetMapping(path = "/health")
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<?> healthCHeck() throws Exception {
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

	@Operation(summary = "Returns flights for the provided flightnumber and traveldate")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Related flights found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = FlightStatusResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input (i.e. flightumber)", content = @Content),
			@ApiResponse(responseCode = "204", description = "No flights found", content = @Content) })
	@PostMapping(path = "/")
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<FlightStatusResponse> process(@RequestBody FlightStatusRequest request) throws Exception {
		FlightStatusResponse response = FlightStatusManager.getInstance().findFlight(request);
		if (response == null) {
			return new ResponseEntity<FlightStatusResponse>(response, HttpStatus.BAD_REQUEST);

		} else if (response.getFlights() == null) {
			return new ResponseEntity<FlightStatusResponse>(response, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<FlightStatusResponse>(response, HttpStatus.OK);

	}
}
