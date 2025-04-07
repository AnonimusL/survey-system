import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-question-create',
  templateUrl: './question-create.component.html',
  styleUrls: ['./question-create.component.css']
})
export class QuestionCreateComponent implements OnInit {

  selectMultiple: boolean = false
  labels: string[] = [];
  numberOfLabelsArray: any[];

  constructor() { 
    this.numberOfLabelsArray = Array.from({ length: this.numberOfLabels }, (_, index) => index);
  }

  set numberOfLabels(value: number) {
    this._numberOfLabels = value;
    console.log('numberOfLabels changed:', this.numberOfLabels);
    
    this.onChangeNumberOfLabels();
  }

  get numberOfLabels(): number {
    return this._numberOfLabels;
  }

  private _numberOfLabels: number = 0;

  onChangeNumberOfLabels() {
    this.numberOfLabelsArray = Array.from({ length: this.numberOfLabels }, (_, index) => index);
  }

  ngOnInit(): void {
  }

  onSubmit(): void{

  }

}
