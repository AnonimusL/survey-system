import { Component, OnInit } from '@angular/core';
import { TargetUser } from 'src/app/models/organization_model';
import { QuestionForSurvey, UserSurvey } from 'src/app/models/survey_model';
import { DialogService } from 'src/app/services/dialog.service';
import { SurveyRibbonService } from 'src/app/services/survey-ribbon.service';
import { SurveyTreeService } from 'src/app/services/survey-tree.service';
import { SurveysService } from 'src/app/services/surveys.service';
import { UserService } from 'src/app/services/user.service';
import { FileUploadComponent } from '../file-upload/file-upload.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: TargetUser[] = []
  pageSize: number
  currentPageIndex: number
  pageUsers: TargetUser[] = []
  userSurveys: UserSurvey[]
  selectedOption: string = 'users'; 

  constructor(private userService: UserService, private dialogService: DialogService) {
    this.users = []
    this.pageSize = 10
    this.currentPageIndex = 0
    this.userSurveys = []
   }

  ngOnInit(): void {
    this.userService.getUsers("users").subscribe(res =>{
      console.log(res)
      this.users = res
      this.userService.users = res
      this.loadData()
    })
  }

  switch(option: string) {
      this.selectedOption = option;
        this.userService.getUsers(option).subscribe(res =>{
          this.users = res
          this.userService.users = res
          this.loadData()
        })
  }

  changePage(pageIndex: number) {
    this.currentPageIndex = pageIndex;
    this.loadData(); // Assuming you have a method to load data based on pageIndex
  }

  loadData() {
    const startIndex = this.currentPageIndex * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.pageUsers = this.users.slice(startIndex, endIndex);
  }

  onUpload(): void{
    this.dialogService.openDialog(FileUploadComponent, "users");
  }

  showDetails(email: string): void{
    console.log(email)
    this.userService.getUserSurveys(email).subscribe(res => {
      this.userSurveys = res
      console.log(this.userSurveys)
    })
  }

  get shouldShowPaginator(): boolean {
    return this.users.length > this.pageSize;
  }
}
