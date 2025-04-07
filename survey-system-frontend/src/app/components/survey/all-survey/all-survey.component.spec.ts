import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllSurveyComponent } from './all-survey.component';

describe('AllSurveyComponent', () => {
  let component: AllSurveyComponent;
  let fixture: ComponentFixture<AllSurveyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllSurveyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllSurveyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
