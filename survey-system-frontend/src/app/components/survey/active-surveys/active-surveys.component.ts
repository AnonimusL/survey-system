import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserSurvey } from 'src/app/models/survey_model';
import { SurveysService } from 'src/app/services/surveys.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-active-surveys',
  templateUrl: './active-surveys.component.html',
  styleUrls: ['./active-surveys.component.css']
})
export class ActiveSurveysComponent implements OnInit {

  surveys: UserSurvey[] = []
  subjectSurveyMap: Map<string, UserSurvey[]> = new Map();

  constructor(private userService : UserService, private surveyService: SurveysService, private router: Router) { 
  
  }

  ngOnInit(): void {
    let email = localStorage.getItem("email") ?? ""; 
      
    this.userService.getUserSurveys(email).subscribe(res => {
        this.surveys = res
        console.log(res)

        this.surveys.forEach(survey => {
          if (this.subjectSurveyMap.has(survey.subject)) {
            this.subjectSurveyMap.get(survey.subject)!.push(survey);
          } else {
            this.subjectSurveyMap.set(survey.subject, [survey]);
          }
        });

        console.log(this.subjectSurveyMap)
    })
  }

  select(id: number){
    console.log('uso')
    this.router.navigate(['/fill-survey', id]);
  }

}
