import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FlightstatusComponent } from './flightstatus/flightstatus.component';

const routes: Routes = [
  { path: '',     component: FlightstatusComponent}
  ]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
