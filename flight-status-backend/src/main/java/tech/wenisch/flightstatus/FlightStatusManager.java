package tech.wenisch.flightstatus;

import java.io.IOException;

import tech.wenisch.flightstatus.web.FlightStatusRequest;
import tech.wenisch.flightstatus.web.FlightStatusResponse;

public class FlightStatusManager {
	FlightStatusManager() {
		try {
			initModel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initModel() throws IOException {

	}

	public static FlightStatusResponse findFlight(FlightStatusRequest request) {
		FlightStatusResponse response = new FlightStatusResponse();
		return response;
	}

}
