# Flightstatus Example
![Build](https://github.com/JFWenisch/flight-status-example/actions/workflows/ci.yml/badge.svg) ![Version](https://img.shields.io/github/v/release/jfwenisch/flight-status-example) ![License](https://img.shields.io/github/license/jfwenisch/flight-status-example)   ![Maintainability](https://img.shields.io/codeclimate/maintainability/JFWenisch/flight-status-example)
 ![Size](https://img.shields.io/github/repo-size/jfwenisch/flight-status-example) 


The Flightstatus example is an application containing a lightweight java spring-boot microservice used as backend (REST Api), an angular frontend, elasticsearch and kibana to demonstrate a possible integration.

```mermaid
stateDiagram-v2
 [*] --> Frontend
Frontend --> Backend: flightStatusRequest
Backend --> Elasticsearch: flightStatusRequest
Backend --> Frontend: flightStatusResponse
 [*] --> Kibana 
 Kibana --> Elasticsearch
 
```
## Quickstart
###  Run using docker-compose

The docker image for the backend is built and pushed to the github container registry during CI. The application exposes an endpoint on port 8090 which is used to retrieve the input text via a HTTP Post. Therefore we will expose this port when running the image.


Start the microservice, Elasticsearch and Kibana using Docker Compose:
```bash 
docker-compose up -d
```
After a couple of seconds, the elasticsearch instance can be reached  at `http://localhost:9200/`, Kibana will be running at `http://localhost:5601` the frontend at `http://localhost:8080/` and the backend at `http://localhost:8090/`.


To shut down Elasticsearch and Kibana run:
```bash 
docker-compose down
```

In case you also would like to remove the docker volume while shutting down run:
```bash 
docker-compose down -v
```

### Search for flight status
The microsoervice provides and endpoint that will return random flights for a given POST request. The backend always requires a travelDate and flightNumber to be provided.

There are multiple ways on how to test this functionality: 
<strong> Using the frontend</strong>
Open the frontend by navigating to [http://localhost:8080/](http://localhost:8080) and enter a flightNumber (i.e. `F12345`) and choose a travelDate 
![frontend](./docs/img/flight-status-frontend.png)

<strong>Using Swagger UI</strong>

Navigate to [http://localhost:8090/swagger-ui/index.html#/controller/process](http://localhost:8090/swagger-ui/index.html#/controller/process) and run the request with the provided sample data.

![frontend](./docs/img/swagger-ui-sample.png)


<strong> Using Curl </strong>

```bash
curl --location --request POST 'localhost:8090' \
--header 'Content-Type: application/json' \
--data-raw '{ "flightNumber": "F12345", "travelDate": "2022-08-17" }'
```
Hint: The travelDate needs to be provided as `yyyy-MM-dd` formatted  string.

### Result
The endpoint returns an array of flights that matches the input.
```JSON
{
  "flight": [
    {
      "departureTime": "2022-08-17 18:17:00",
      "arrivalTime": "2022-08-17 21:15:22",
      "derpartureAirport": {
        "fullName": "Cologne Bonn Airport",
        "shortName": "CGN"
      },
      "arrivalAirport": {
        "fullName": "Cologne Bonn Airport",
        "shortName": "CGN"
      },
      "departureGate": "B05",
      "arrivalGate": "A12",
      "flightNumber": "F12345"
    }
  ]
}
```
 For more information regarding available endpoints and schemas please refer to the openapi specification and the swagger-ui that is automatically generated and available during runtime on `http://localhost:8090/swagger-ui/index.html`

## Further information
### General
For example purposes the application will automatically create and return a random list of flights for the provided traveldate if the flightnumber matches `F12345`. All flights that are created this way are stored as document in elasticsearch indexed under `flights`. If the flightNumber starts with `F` but isn't matching `F12345` an empty list is returned with the HTTP Status 204 (No Content). If the flightNumber is not starting with `F`, the Backend will return an HTTP 400 (Bad Request) errorcode .

All requested flights independent of the flightnumber are stored as document in elasticsearch and indexed under `requests`


### Usage with Eureka Server
The Application can register itself with an Eureka Server instance for service discovery. To do so, set the environment variable `EUREKA_URL`.
### Tools / Modules used

 * Java 17
 * Spring Boot
 * Elasticsearch Java client


## Build manually

### Prerequisites
 * JDK 17
 * Maven
 * Docker

### Build Jar
For manually building the project run
```bash 
cd flight-status-backend && mvn clean package
```

### Build Docker container
For manually building the project run
```bash 
cd flight-status-backend && docker build -t flight-status-backend .
```
