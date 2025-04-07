import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SurveyCreateComponent } from './components/survey-create/survey-create.component';
import { QuestionComponent } from './components/survey/question/question.component';
import { SurveyComponent } from './components/survey/survey.component';
import { SurveyTemplateComponent } from './components/survey/survey-template/survey-template.component';
import { MySurveyComponent } from './components/survey/my-survey/my-survey.component';
import { AllSurveyComponent } from './components/survey/all-survey/all-survey.component';
import { SurveyGeneralComponent } from './components/survey/survey-general/survey-general.component';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './guards/auth.guard';
import { SurveyFillComponent } from './components/survey-fill/survey-fill.component';
import { UsersComponent } from './components/users/users.component';
import { RoleGuard } from './guards/role.guard';
import { LoginGuard } from './guards/login.guard';
import { ActiveSurveysComponent } from './components/survey/active-surveys/active-surveys.component';

const routes: Routes = [
  { path: 'survey', component: SurveyComponent, children: [
    { path: 'my-surveys', component: MySurveyComponent },
    { path: 'all-surveys', component: AllSurveyComponent },
    { path: 'general/:id', component: SurveyGeneralComponent }, 
    { path: 'templates', component: SurveyTemplateComponent }, 
    { path: '', redirectTo: 'my-surveys', pathMatch: 'full' }
  ]},
  {path: 'login', component: LoginComponent},
  {path: 'users', component: UsersComponent},
  { path: 'app-active-surveys', component: ActiveSurveysComponent},
  { path: 'fill-survey/:id', component: SurveyFillComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
