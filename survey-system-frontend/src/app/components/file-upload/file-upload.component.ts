import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { fadeInOut } from '../../dialog-animations'; // Import the animation
import { SurveysService } from 'src/app/services/surveys.service';
import { NodeService } from 'src/app/services/node.service';
import { UserService } from 'src/app/services/user.service';
import { DialogService } from 'src/app/services/dialog.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css'],
  animations: [fadeInOut]
})
export class FileUploadComponent {

  selectedFile: any;
  files: any[] = []
  forUpload: string = ""

  constructor(
    public dialogRef: MatDialogRef<FileUploadComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, 
    private surveyService: SurveysService, 
    private nodeService: NodeService, 
    private userService: UserService,
    private dialogService: DialogService,
  ) {
    this.forUpload = data.forUpload
    console.log(this.forUpload)
  }

  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files) {
      const newFiles = Array.from(input.files);

      this.files = [...this.files, ...newFiles];
    }
  }

  upload(): void{
    if(this.forUpload == "nodes"){
      this.surveyService.uploadSurveys(this.files).subscribe(res => {
        if (Array.isArray(res) && this.nodeService.data) {
          this.nodeService.data = this.nodeService.data.concat(res);
          this.nodeService.getTree(this.nodeService.data)
        }
        
      })
    }
    else if(this.forUpload == "users"){
      this.userService.uploadTargetUsers(this.files).subscribe(res => {
        this.userService.targetUsers = this.userService.targetUsers.concat(res);
      })
    }
    this.close()
  }

  onDragOver(event: DragEvent) {
    event.preventDefault(); // Prevent default to allow dropping
  }
  
  onDragLeave(event: DragEvent) {
    // Optional: handle if needed (e.g., visual cue when dragging ends)
  }
  
  onDrop(event: DragEvent) {
    event.preventDefault();
    const droppedFiles = event.dataTransfer?.files;
    if (droppedFiles) {
      this.addFiles(droppedFiles); // Add files to the list
    }
  }
  
  addFiles(fileList: FileList) {
    for (let i = 0; i < fileList.length; i++) {
      this.files.push(fileList[i]);  // Add selected files to the array
    }
  }
  
  removeFile(index: number) {
    this.files.splice(index, 1);  // Remove the selected file from the array
  }
  
  close() {
   this.dialogService.closeDialog();
  }

}
