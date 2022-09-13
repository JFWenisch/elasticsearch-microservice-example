import { Component, OnInit,Input } from '@angular/core';
import { Router } from '@angular/router';
import { RestClientService } from '../shared/rest-client.service';
@Component({
  selector: 'app-flightstatus',
  templateUrl: './flightstatus.component.html',
  styleUrls: ['./flightstatus.component.css']
})
export class FlightstatusComponent implements OnInit {
  @Input() flightDetails = { flightNumber: '', travelDate: ''};
  constructor(public restApi: RestClientService, public router: Router) {}
  ngOnInit() {}
  searchFlight(details: any) {
    this.restApi.searchFlight(this.flightDetails).subscribe((data: {}) => {
      this.router.navigate(['/employees-list']);
    });
}
}