import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightstatusComponent } from './flightstatus.component';

describe('FlightstatusComponent', () => {
  let component: FlightstatusComponent;
  let fixture: ComponentFixture<FlightstatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FlightstatusComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FlightstatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
