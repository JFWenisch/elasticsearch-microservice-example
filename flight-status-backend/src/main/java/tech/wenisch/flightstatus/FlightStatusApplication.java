package tech.wenisch.flightstatus;

import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

@SpringBootApplication
//@EnableEurekaClient
public class FlightStatusApplication {
	private static FlightStatusManager manager;
	private static ElasticsearchClient elasticClient;
	private static Format dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		initElasticClient();
		manager = new FlightStatusManager();
		SpringApplication.run(FlightStatusApplication.class, args);
	}

	public static void initElasticClient() {

		RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
		ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
		elasticClient = new ElasticsearchClient(transport);
	}

	public static FlightStatusManager getManager() {
		return manager;
	}

	public static ElasticsearchClient getElasticClient() {
		return elasticClient;
	}

	public static Format getDateFormatter() {
		return dateFormatter;
	}
}
