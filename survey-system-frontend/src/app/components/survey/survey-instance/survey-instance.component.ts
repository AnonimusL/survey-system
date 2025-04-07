import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { SurveyInstance } from 'src/app/models/survey_model';
import { SurveysService } from 'src/app/services/surveys.service';
import {NativeDateAdapter} from '@angular/material/core';
import { MatDatepicker } from '@angular/material/datepicker';
import { NgxMaterialTimepickerComponent } from 'ngx-material-timepicker';

@Component({
  selector: 'app-survey-instance',
  templateUrl: './survey-instance.component.html',
  styleUrls: ['./survey-instance.component.css'],
  providers: [NativeDateAdapter]
})
export class SurveyInstanceComponent implements OnInit {
  @ViewChild('fromDatePicker') fromDatePicker?: MatDatepicker<Date>;
  @ViewChild('fromTimePicker') fromTimePicker?: NgxMaterialTimepickerComponent;
  @ViewChild('toDatePicker') toDatePicker?: MatDatepicker<Date>;
  @ViewChild('toTimePicker') toTimePicker?: NgxMaterialTimepickerComponent;

  fromDate: Date | null = null;
  fromTime: string = '';
  toDate: Date | null = null;
  toTime: string = '';

  pageSize: number
  currentPageIndex: number

  date = new FormControl(new Date());
  serializedDate = new FormControl(new Date().toISOString());
  minDate = new Date();
  maxDate = new Date(2030, 11, 31);
  disabled = false;
  showSpinners = true;
  showSeconds = false;
  stepHour = 1;
  stepMinute = 1;
  stepSecond = 1;
  touchUi = false;
  color = 'primary';
  enableMeridian = false;
  disableMinute = false;
  hideTime = false;

  activeFrom = ""
  activeTo = ""

  instances: SurveyInstance[] = []
  pageInstances: SurveyInstance[] = []

  selectedAll: boolean

  constructor(private surveyService : SurveysService) { 
    this.selectedAll = false
    this.pageSize = 10
    this.currentPageIndex = 0
  }

  ngOnInit(): void {
    console.log(this.surveyService.activeSurvey.title)
    this.surveyService.getSurveyInstances(this.surveyService.activeSurvey.title + "_" + "SURVEY").subscribe((res) => {
      this.instances = res
      console.log(res)
    })
  }

  selectAll(): void {
    this.selectedAll = !this.selectedAll

    for (let instance of this.instances) {
      instance.selected = this.selectedAll;
    }
  }

  activate(): void {
    console.log(this.activeFrom)
    console.log(this.activeTo)

    this.surveyService.activateSurveys(this.surveyService.activeSurvey.title + "_" + "SURVEY", this.activeFrom, this.activeTo, this.getSelectedInstances()).subscribe(res => {
      console.log(res)
    })
      
  }

  getSelectedInstances(): SurveyInstance[] {
    return this.instances.filter(instance => instance.selected);
  }

  showDetails(email: string): void{
    console.log(email)
    // add call for detail data
  }

  get shouldShowPaginator(): boolean {
    return this.instances.length > this.pageSize;
  }

  changePage(pageIndex: number) {
    this.currentPageIndex = pageIndex;
    this.loadData(); // Assuming you have a method to load data based on pageIndex
  }

  loadData() {
    const startIndex = this.currentPageIndex * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.pageInstances = this.instances.slice(startIndex, endIndex);
  }

  // Update date based on selection
  onFromDateChange(event: any) {
    this.fromDate = event.value;
  }

  onToDateChange(event: any) {
    this.toDate = event.value;
  }

  // Update time based on selection
  onFromTimeChange(event: any) {
    this.fromTime = event;
  }

  onToTimeChange(event: any) {
    this.toTime = event;
  }
  
}
