import { Component, OnInit } from '@angular/core';
import { QuestionForSurvey } from 'src/app/models/survey_model';
import { DialogService } from 'src/app/services/dialog.service';
import { SurveyRibbonService } from 'src/app/services/survey-ribbon.service';
import { SurveyTreeService } from 'src/app/services/survey-tree.service';
import { SurveysService } from 'src/app/services/surveys.service';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {

  questions: QuestionForSurvey[] = []

  constructor(private surveyService: SurveysService, private ribbonService: SurveyRibbonService) {
    this.questions = surveyService.activeSurvey.questionForSurveyDto
   }

  ngOnInit(): void {
  }

  newQuestion(): void{
    this.ribbonService.changeComponent("newQuestion")
  }

}
