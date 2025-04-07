import { TestBed } from '@angular/core/testing';

import { SurveyTreeService } from './survey-tree.service';

describe('SurveyTreeService', () => {
  let service: SurveyTreeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SurveyTreeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
