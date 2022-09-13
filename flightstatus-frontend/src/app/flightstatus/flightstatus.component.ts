import { Component, OnInit,Input } from '@angular/core';
import { Router } from '@angular/router';
import { FlightStatusResponse } from '../shared/flight-status-response';
import { RestClientService } from '../shared/rest-client.service';
@Component({
  selector: 'app-flightstatus',
  templateUrl: './flightstatus.component.html',
  styleUrls: ['./flightstatus.component.css']
})
export class FlightstatusComponent implements OnInit {
  @Input() flightDetails = { flightNumber: '', travelDate: ''};
  constructor(public restApi: RestClientService, public router: Router) {}
  data: any;
  isLoading:any;
  ngOnInit() {}
  searchFlight(details: any) {
    this.isLoading=true;
    this.restApi.searchFlight(this.flightDetails).subscribe((data: FlightStatusResponse) => {
      console.log(data)
      this.data=data.flights;
      this.isLoading=false;
    });
}
}