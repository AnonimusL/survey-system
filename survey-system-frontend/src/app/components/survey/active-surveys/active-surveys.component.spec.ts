import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveSurveysComponent } from './active-surveys.component';

describe('ActiveSurveysComponent', () => {
  let component: ActiveSurveysComponent;
  let fixture: ComponentFixture<ActiveSurveysComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActiveSurveysComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActiveSurveysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
