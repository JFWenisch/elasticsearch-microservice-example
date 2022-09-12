package tech.wenisch.flightstatus;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import tech.wenisch.flightstatus.models.Flight;
import tech.wenisch.flightstatus.web.FlightStatusRequest;

public class ElasticManager {

	private static ElasticManager instance;
	private ElasticsearchClient elasticClient;
	private static Logger logger = LoggerFactory.getLogger(ElasticManager.class);

	private ElasticManager() {
		RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
		ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
		elasticClient = new ElasticsearchClient(transport);
	}

	static {
		try {
			instance = new ElasticManager();
		} catch (Exception e) {
			throw new RuntimeException("Exception occured initialization of ElasticManager:" + e.getMessage());
		}
	}

	public static ElasticManager getInstance() {
		return instance;
	}

	public void storeFlightStatusRequest(FlightStatusRequest request) throws ElasticsearchException, IOException {
		co.elastic.clients.elasticsearch.core.IndexResponse response = elasticClient
				.index(i -> i.index("requests").document(request));
		logger.info(response.toString());
	}

	public void storeFlight(Flight flight) throws ElasticsearchException, IOException {
		co.elastic.clients.elasticsearch.core.IndexResponse response = elasticClient
				.index(i -> i.index("flight").document(flight));
		logger.info(response.toString());
	}
}