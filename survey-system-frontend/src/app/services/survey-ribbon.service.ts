import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SurveyRibbonService {

  private activeComponent = new Subject<string>();

  activeComponent$ = this.activeComponent.asObservable();

  submenus: { [key: string]: boolean } = {
    general: true,
    questions: false,
    templates: false,
    newQuestion: false,
    instances: false,
  };

  changeComponent(menuKey: string) {
    Object.keys(this.submenus).forEach(key => this.submenus[key] = false);
  
    this.submenus[menuKey] = true;

    this.activeComponent.next(menuKey);

  }
  
}
