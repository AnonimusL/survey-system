import { Injectable } from '@angular/core';
import { NodeWorkspaceActivation } from '../models/survey_model';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SurveyTreeService {

  private fileClickSubject = new Subject<number>();

  fileClick$ = this.fileClickSubject.asObservable();

  submenus: { [key: string]: boolean } = {
    general: true,
    questions: false,
    templates: false,
  };

  activation: { [key: number]: NodeWorkspaceActivation} = {};
  selectedSurvey: number = -1
  constructor() {
    
   }

   emitFileClick(surveyId: number): void {
    this.fileClickSubject.next(surveyId);
  }
}
