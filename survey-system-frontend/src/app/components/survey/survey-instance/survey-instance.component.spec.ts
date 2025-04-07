import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyInstanceComponent } from './survey-instance.component';

describe('SurveyInstanceComponent', () => {
  let component: SurveyInstanceComponent;
  let fixture: ComponentFixture<SurveyInstanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SurveyInstanceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SurveyInstanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
