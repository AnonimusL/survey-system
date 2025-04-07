import { Component, OnInit, ViewContainerRef, ViewChild, ChangeDetectorRef } from '@angular/core';
import { SurveyGeneralComponent } from './survey-general/survey-general.component';
import { SurveyTreeService } from 'src/app/services/survey-tree.service';
import { SurveyRibbonComponent } from '../survey-ribbon/survey-ribbon.component';
import { SurveysService } from 'src/app/services/surveys.service';
import { SurveyRibbonService } from 'src/app/services/survey-ribbon.service';
import { SurveyTemplateComponent } from './survey-template/survey-template.component';
import { QuestionComponent } from './question/question.component';
import { QuestionCreateComponent } from '../question-create/question-create.component';
import { SurveyInstanceComponent } from './survey-instance/survey-instance.component';

@Component({
  selector: 'app-survey',
  templateUrl: './survey.component.html',
  styleUrls: ['./survey.component.css']
})
export class SurveyComponent implements OnInit {

  @ViewChild('dynamicContent', { read: ViewContainerRef }) dynamicContent!: ViewContainerRef;

  activate = true;

  selectedSurvey: number;

  constructor(private viewContainerRef: ViewContainerRef, private treeService: SurveyTreeService, private surveyService: SurveysService, private ribbonService: SurveyRibbonService) {
    this.treeService.selectedSurvey = 1 
    this.selectedSurvey = this.treeService.selectedSurvey
  }

  ngOnInit(): void {
    this.ribbonService.activeComponent$.subscribe(menuKey => {
      switch (menuKey) {
        case 'general':
          this.loadComponent(SurveyGeneralComponent);
          break;
        case 'questions':
          this.loadComponent(QuestionComponent);
          break;
        case 'templates':
          this.loadComponent(SurveyTemplateComponent);
          break;
        case 'instances':
          this.loadComponent(SurveyInstanceComponent);
          break;
        case 'newQuestion':
            this.loadComponent(QuestionCreateComponent);
            break;
        default:
          break;
      }
    })
    this.treeService.fileClick$.subscribe(surveyId => {
      this.surveyService.getSurveyDetailed(surveyId).subscribe(res=>{
        this.surveyService.activeSurvey = res
        this.ribbonService.changeComponent('general')
      })
    });
  }

  loadComponent(componentType: any): void {
    if (this.dynamicContent) {
      console.log('Loading component...');
      this.dynamicContent.clear();
      const component = this.dynamicContent.createComponent(componentType);
    }
  }

}