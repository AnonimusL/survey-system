import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyRibbonComponent } from './survey-ribbon.component';

describe('SurveyRibbonComponent', () => {
  let component: SurveyRibbonComponent;
  let fixture: ComponentFixture<SurveyRibbonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SurveyRibbonComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SurveyRibbonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
