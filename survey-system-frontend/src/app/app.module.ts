import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SurveyCreateComponent } from './components/survey-create/survey-create.component';
import { QuestionCreateComponent } from './components/question-create/question-create.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SurveyComponent } from './components/survey/survey.component';
import { QuestionComponent } from './components/survey/question/question.component';
import { SurveyRibbonComponent } from './components/survey-ribbon/survey-ribbon.component';
import { SurveyTemplateComponent } from './components/survey/survey-template/survey-template.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { MySurveyComponent } from './components/survey/my-survey/my-survey.component';
import { AllSurveyComponent } from './components/survey/all-survey/all-survey.component';
import { SurveyGeneralComponent } from './components/survey/survey-general/survey-general.component';
import { SurveyTreeViewComponent } from './components/survey-tree-view/survey-tree-view.component';
import { TreeItemComponent } from './components/tree-item/tree-item.component';
import { HttpClientModule } from '@angular/common/http';
import { MatDialogModule } from '@angular/material/dialog';
import { FileUploadComponent } from './components/file-upload/file-upload.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './components/login/login.component';

import { SocialLoginModule, SocialAuthServiceConfig } from '@abacritt/angularx-social-login';
import {
  GoogleLoginProvider,
} from '@abacritt/angularx-social-login';
import { UsersComponent } from './components/users/users.component';
import { PaginatorComponent } from './components/paginator/paginator.component';
import { SurveyFillComponent } from './components/survey-fill/survey-fill.component';
import { SurveyInstanceComponent } from './components/survey/survey-instance/survey-instance.component';
import { NgxMatDatetimePickerModule, NgxMatNativeDateModule, NgxMatTimepickerModule } from '@angular-material-components/datetime-picker';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule, MatRippleModule  } from '@angular/material/core';
import { ActiveSurveysComponent } from './components/survey/active-surveys/active-surveys.component';
import { FilterBySubjectPipe } from './filter-by-subject.pipe';
import { MatIconModule } from '@angular/material/icon';  // Import MatIconModule

@NgModule({
  declarations: [
    AppComponent,
    SurveyCreateComponent,
    QuestionCreateComponent,
    SurveyComponent,
    QuestionComponent,
    SurveyRibbonComponent,
    SurveyTemplateComponent,
    SidebarComponent,
    MySurveyComponent,
    AllSurveyComponent,
    SurveyGeneralComponent,
    SurveyTreeViewComponent,
    TreeItemComponent,
    FileUploadComponent,
    LoginComponent,
    UsersComponent,
    PaginatorComponent,
    SurveyFillComponent,
    SurveyInstanceComponent,
    ActiveSurveysComponent,
    FilterBySubjectPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatDialogModule,
    BrowserAnimationsModule,
    SocialLoginModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatInputModule,
    NgxMatTimepickerModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    NgxMatDatetimePickerModule,
    NgxMatNativeDateModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatRippleModule,
    NgxMaterialTimepickerModule,
    MatIconModule  
  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '724979803977-n1vbc47o1j2b2ljsstj5u20k8rshnui1.apps.googleusercontent.com'
            )
          },
        ],
        onError: (err) => {
          console.error(err);
        }
      } as SocialAuthServiceConfig,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
