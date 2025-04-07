import { Component, OnInit } from '@angular/core';
import { MultipleChoiceForSurveyDto, OpenEndedForSurveyDto, QuestionForSurvey, QuestionForSurveyDet, RatingScaleForSurveyDto } from 'src/app/models/survey_model';
import { SurveysService } from 'src/app/services/surveys.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-survey-fill',
  templateUrl: './survey-fill.component.html',
  styleUrls: ['./survey-fill.component.css']
})
export class SurveyFillComponent implements OnInit {

  questions: QuestionForSurveyDet[] = []

  constructor(private route: ActivatedRoute, private surveyService: SurveysService) { 
    console.log("uso")
  }

  ngOnInit(): void {
    //this.route.params.subscribe(params => {
      //  const id = params['id'];
      let id = 26
        console.log(id)
        this.surveyService.getSurveyQuestionsDet(id).subscribe(res => {
            this.questions = res
            console.log(res)
            this.questions = res
        })
    //})
  }


}
