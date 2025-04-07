// survey-tree-view.component.ts

import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NodeWorkspace } from 'src/app/models/survey_model';
import { DialogService } from 'src/app/services/dialog.service';
import { NodeService } from 'src/app/services/node.service';
import { SurveyTreeService } from 'src/app/services/survey-tree.service';
import { FileUploadComponent } from '../file-upload/file-upload.component';

@Component({
  selector: 'app-survey-tree-view',
  templateUrl: './survey-tree-view.component.html',
  styleUrls: ['./survey-tree-view.component.css']
})
export class SurveyTreeViewComponent implements OnInit{

  dataMap = new Map<number, NodeWorkspace>();
  treeData: NodeWorkspace[] = [];
  data: NodeWorkspace[] = [];

  constructor(
    private nodeService: NodeService,
    private dialogService: DialogService,
  ) {}

  ngOnInit(): void {
    const userId = 1;
    const organizationId = 1;

    this.nodeService.treeData$.subscribe(newTreeData => {
      this.treeData = newTreeData;
    });
    this.nodeService.getWorkspace(userId, organizationId).subscribe(response => {});

  }

  onAdd(): void{
    this.dialogService.openDialog(FileUploadComponent, "nodes");
  }
}
