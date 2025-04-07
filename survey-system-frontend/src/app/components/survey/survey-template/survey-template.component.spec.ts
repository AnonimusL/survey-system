import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyTemplateComponent } from './survey-template.component';

describe('SurveyTemplateComponent', () => {
  let component: SurveyTemplateComponent;
  let fixture: ComponentFixture<SurveyTemplateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SurveyTemplateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SurveyTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
