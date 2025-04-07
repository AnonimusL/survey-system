import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SurveySubmenuService {

  submenuSidebar: { [key: string]: boolean } = {
    surveys: false,
    reports: false
  };

}
