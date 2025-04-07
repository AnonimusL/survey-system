import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SurveyRibbonService } from 'src/app/services/survey-ribbon.service';
import { SurveyComponent } from '../survey/survey.component'; 
import { SurveyGeneralComponent } from '../survey/survey-general/survey-general.component';
import { QuestionComponent } from '../survey/question/question.component';
import { SurveyTemplateComponent } from '../survey/survey-template/survey-template.component';

@Component({
  selector: 'survey-ribbon',
  templateUrl: './survey-ribbon.component.html',
  styleUrls: ['./survey-ribbon.component.css']
})
export class SurveyRibbonComponent implements OnInit {

  submenus: { [key: string]: boolean } 
  selectedSurveyItem: string = '';

  constructor(private ribbonService: SurveyRibbonService, private router: Router, private surveyComponent: SurveyComponent) {
    this.submenus = ribbonService.submenus;
  }

  ngOnInit(): void {
  
  }
  
  ngAfterViewInit(): void {
    this.surveyComponent.loadComponent(SurveyGeneralComponent);
  }

  toggleSubmenu(menuKey: string): void {
    // Reset all submenu states to false
    this.ribbonService.changeComponent(menuKey)
  }

}
