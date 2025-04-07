// tree-item.component.ts

import { Component, Input, Output, EventEmitter } from '@angular/core';
import { NodeWorkspace, NodeWorkspaceActivation } from '../../models/survey_model';
import { SurveyTreeService } from 'src/app/services/survey-tree.service';

@Component({
  selector: 'app-tree-item',
  templateUrl: './tree-item.component.html',
  styleUrls: ['./tree-item.component.css']
})
export class TreeItemComponent {
  @Input() nodes: NodeWorkspace[] = [];

  activation: { [key: number]: NodeWorkspaceActivation } = {};
  clickedFolder: number
  clickedFile: number

  constructor(private treeService: SurveyTreeService) {
    this.activation = treeService.activation
    this.clickedFolder = -1
    this.clickedFile = -1
  }

  onFolderClick(node: NodeWorkspace) : void{
    if(this.treeService.activation[node.id].active == true){
      this.recursivelySetActiveFalse(this.treeService.activation[node.id].children);
    }
    this.treeService.activation[node.id].active = !this.treeService.activation[node.id].active;
    this.clickedFolder = -1
    this.clickedFile = -1
    this.clickedFolder = node.id
    console.log(this.clickedFolder)
  }

  onFileClick(surveyId: number): void {
    this.treeService.selectedSurvey = surveyId
    this.clickedFile = surveyId
    this.treeService.emitFileClick(surveyId);
  }

  recursivelySetActiveFalse(children: number[]): void {
    if (children.length > 0) {
      children.forEach(child => {
        this.treeService.activation[child].active = false
  
        this.recursivelySetActiveFalse(this.treeService.activation[child].children);
      });
    }
  }

  calculatePadding(nodeLvl: number): number {
    return nodeLvl * 10 + 10;
  }
}
