import { Component, OnInit } from '@angular/core';
import { Observable, switchMap } from 'rxjs';
import { OrganizationDetailed } from 'src/app/models/organization_model';
import { Survey } from 'src/app/models/survey_model';
import { OrganizationService } from 'src/app/services/organization.service';
import { SurveyTreeService } from 'src/app/services/survey-tree.service';
import { SurveysService } from 'src/app/services/surveys.service';

@Component({
  selector: 'app-survey-general',
  templateUrl: './survey-general.component.html',
  styleUrls: ['./survey-general.component.css']
})
export class SurveyGeneralComponent implements OnInit {

  survey: Survey;
  organization: OrganizationDetailed;

  constructor(private treeService: SurveyTreeService, private surveyService: SurveysService, private organizationService: OrganizationService) {
    this.survey = {
      id: this.surveyService.activeSurvey.id,
      title: this.surveyService.activeSurvey.title,
      description: this.surveyService.activeSurvey.description,
      logo: this.surveyService.activeSurvey.logo, 
      priv: this.surveyService.activeSurvey.priv,
      activationDate: this.surveyService.activeSurvey.activationDate,
      deactivationDate: this.surveyService.activeSurvey.deactivationDate,
      active: this.surveyService.activeSurvey.active,
      organizationId: this.surveyService.activeSurvey.organizationId,
      userId: this.surveyService.activeSurvey.userId
    }

    console.log("jeaa", this.survey)

    this.organization = {
      name: "",
      phoneNumber: "",
      webpage: "",
      active: true,
      addressDto: {
        country: "",
        city: "",
        postcode: "",
        street: "",
        number: "",
      },
      organizationId: 1,
      userId: 1,
      about: "",
      logo: new Uint8Array(2)
    }
    

  }

  ngOnInit(): void {
    console.log(this.surveyService.activeSurvey.organizationId)
    this.organizationService.getOrganization(this.surveyService.activeSurvey.organizationId).subscribe(res => {
      this.organization = res
    })
  }

  ngAfterViewInit(): void {
    // Your initialization code here
    console.log("JUHU 2")
  }

}
