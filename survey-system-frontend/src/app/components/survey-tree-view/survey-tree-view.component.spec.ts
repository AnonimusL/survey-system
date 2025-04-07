import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyTreeViewComponent } from './survey-tree-view.component';

describe('SurveyTreeViewComponent', () => {
  let component: SurveyTreeViewComponent;
  let fixture: ComponentFixture<SurveyTreeViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SurveyTreeViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SurveyTreeViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
