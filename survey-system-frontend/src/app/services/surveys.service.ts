import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NodeWorkspace, QuestionForSurvey, QuestionForSurveyDet, Survey, SurveyDetailed, SurveyInstance, SurveysActivation, UserSurvey } from '../models/survey_model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { WorkspaceGetDto } from '../models/node_model';

@Injectable({
  providedIn: 'root'
})
export class SurveysService {

  private apiUrl = environment.surveyApiUrl;
  private targetUrl = environment.targetUserApiUrl;

  surveys: Survey[] = []
  activeSurvey!: SurveyDetailed
  constructor(private http: HttpClient) { }

  getSurveys(type: string): Observable<Survey[]>{
    if(type == 'all')
      return this.http.get<Survey[]>(`${this.apiUrl}/survey/all`)
    else
      return this.http.get<Survey[]>(`${this.apiUrl}/survey/my`)
  }

  getSurveyGeneral(surveyId: number): Observable<Survey>{
    return this.http.get<Survey>(`${this.apiUrl}/survey/${surveyId}`)
  }

  getSurveyQuestions(surveyId: number): Observable<QuestionForSurvey[]>{
    return this.http.get<QuestionForSurvey[]>(`${this.apiUrl}/survey/${surveyId}/questions`)
  }

  getSurveyQuestionsDet(surveyId: number): Observable<QuestionForSurveyDet[]>{
    return this.http.get<QuestionForSurveyDet[]>(`${this.apiUrl}/survey/${surveyId}/questions`)
  }

  getSurveyDetailed(surveyId: number): Observable<SurveyDetailed>{
    return this.http.get<SurveyDetailed>(`${this.apiUrl}/survey/${surveyId}/detailed`)
  }

  getSurveyInstances(surveyName: string): Observable<SurveyInstance[]>{
    let httpParams = new HttpParams();
    httpParams = httpParams.append("name", surveyName)
    return this.http.get<SurveyInstance[]>(`${this.apiUrl}/survey/instances`, {params:httpParams})
  }

  activateSurveys(surveyName: string, activeFrom: string, activeTo: string, surveys: SurveyInstance[]): Observable<SurveyInstance[]>{
    const reqBody: SurveysActivation = {
      surveyName: surveyName,
      activeFrom: activeFrom,
      activeTo: activeTo,
      surveys: surveys
    }
    return this.http.post<SurveyInstance[]>(`${this.apiUrl}/survey/instances/activate`, reqBody)
  }

  uploadSurveys(files:any): Observable<NodeWorkspace[]> {
    const formData: FormData = new FormData();

    let httpParams = new HttpParams();

    for (let i = 0; i < files.length; i++) {
      formData.append('files', files[i], files[i].name);
    }
   
    httpParams = httpParams.append("domain", "Education").append("parentNodeId", 1);

  
    return this.http.post<NodeWorkspace[]>(`${this.apiUrl}/survey/upload`, formData, {params: httpParams })
  }


}
