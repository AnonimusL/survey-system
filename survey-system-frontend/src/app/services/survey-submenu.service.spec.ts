import { TestBed } from '@angular/core/testing';

import { SurveySubmenuService } from './survey-submenu.service';

describe('SurveySubmenuService', () => {
  let service: SurveySubmenuService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SurveySubmenuService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
