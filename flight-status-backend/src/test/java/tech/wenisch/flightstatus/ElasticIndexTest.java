package tech.wenisch.flightstatus;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import tech.wenisch.flightstatus.web.FlightStatusRequest;

public class ElasticIndexTest {
	@Test
	void demoTestMethod() throws ElasticsearchException, IOException {
		/*
		FlightStatusApplication.initElasticClient();
		FlightStatusRequest request = new FlightStatusRequest("1",
				FlightStatusApplication.getDateFormatter().format(new Date()));

		IndexResponse response = FlightStatusApplication.getElasticClient()
				.index(i -> i.index("requests").document(request));
		System.out.println(response);
		assertTrue(true);
		*/
	}
}
