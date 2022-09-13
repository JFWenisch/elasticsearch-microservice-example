import { Airport } from "./airport";

export interface Flight {
    departureTime: string;
    arrivalTime: string;
    derpartureAirport: Airport;
    arrivalAirport: Airport;
    departureGate: string;
    arrivalGate: string;
    flightNumber: string;
}
