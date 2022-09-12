package tech.wenisch.flightstatus;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import tech.wenisch.flightstatus.models.Airport;
import tech.wenisch.flightstatus.models.Flight;
import tech.wenisch.flightstatus.web.FlightStatusRequest;
import tech.wenisch.flightstatus.web.FlightStatusResponse;

public class FlightStatusManager {

	private static FlightStatusManager instance;
	private static final String exampleFlightNumber = "F12345";
	private static Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	private FlightStatusManager() {
	}

	static {
		try {
			instance = new FlightStatusManager();
		} catch (Exception e) {
			throw new RuntimeException("Exception occured initialization of FlightStatusManager:" + e.getMessage());
		}
	}

	public static FlightStatusManager getInstance() {
		return instance;
	}

	public FlightStatusResponse findFlight(FlightStatusRequest request) {
		try {
			ElasticManager.getInstance().storeFlightStatusRequest(request);
		} catch (ElasticsearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (request.getFlightNumber().equalsIgnoreCase(exampleFlightNumber)) {
			List<Flight> flights = createRandomFlights(exampleFlightNumber, request.getTravelDate());
			FlightStatusResponse response = new FlightStatusResponse();
			response.setFlights(flights);
			return response;
		} else if (request.getFlightNumber().startsWith("F")) {
			return new FlightStatusResponse();
		} else {
			return null;
		}
	}

	private List<Flight> createRandomFlights(String exampleflightnumber, String travelDate) {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 5 + 1);
		List<Flight> flights = new ArrayList<Flight>();
		
		for (int i = 0; i < randomNum; i++) {
			Flight currentFlight = new Flight();
			
			Airport departureAirport = new Airport();
			departureAirport.setFullName("Cologne Bonn Airport");
			departureAirport.setShortName("CGN");
			currentFlight.setDerpartureAirport(departureAirport);
			
			Airport arrivalAirport = new Airport();
			arrivalAirport.setFullName("Zurich Airport");
			arrivalAirport.setShortName("ZRH");
			currentFlight.setArrivalAirport(arrivalAirport);
			
			currentFlight.setArrivalGate(generateRandomGate());
			currentFlight.setDepartureGate(generateRandomGate());
			
			currentFlight.setFlightNumber(exampleflightnumber);

			currentFlight.setDepartureTime(generateRandomTimeForTravelDate(0, 16, travelDate));
			currentFlight.setArrivalTime(generateRandomTimeForTravelDate(16, 23, travelDate));
			flights.add(currentFlight);
			try {
				ElasticManager.getInstance().storeFlight(currentFlight);
			} catch (ElasticsearchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flights;
	}

	/**
	 * Quickhack generating a random gate Name, starting with a Character from A to
	 * Z and a Number betweeen 1 and 99
	 * 
	 * @return
	 */
	private String generateRandomGate() {
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		char randomChar = abc.charAt(random.nextInt(abc.length()));

		int randomNum = ThreadLocalRandom.current().nextInt(1, 99 + 1);
		return String.valueOf(randomChar) + String.valueOf(randomNum);
	}
	/**
	 * Quickhack generating a random time for date
	 * TODO: Switch to Date /  Calender
	 * 
	 * @return
	 */
	private String generateRandomTimeForTravelDate(int minHour, int maxHour, String travelDate)
	{
		int randomHour = ThreadLocalRandom.current().nextInt(minHour, maxHour + 1);
		int randomMin = ThreadLocalRandom.current().nextInt(1, 59 + 1);
		return travelDate+" "+String.valueOf(randomHour)+":"+String.valueOf(randomMin)+":00";
		
	}

}
