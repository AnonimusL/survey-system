import { TestBed } from '@angular/core/testing';

import { SurveyRibbonService } from './survey-ribbon.service';

describe('SurveyRibbonService', () => {
  let service: SurveyRibbonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SurveyRibbonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
