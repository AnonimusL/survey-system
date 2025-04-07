import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ComponentType } from '@angular/cdk/portal';

@Injectable({
  providedIn: 'root',
})
export class DialogService {
  constructor(private dialog: MatDialog) { }

  dialogRef: any 

  openDialog(component: ComponentType<any>, forUpload: string): void {
    this.dialogRef = this.dialog.open(component, {
      height: '100%',
      maxWidth: '100%',
      panelClass: 'custom-dialog-container',
      data: { forUpload: forUpload }
    });

   // Add click event listener to the overlay to close the dialog on outside click
   this.dialogRef.afterOpened().subscribe(() => {
    this.dialogRef.backdropClick().subscribe(() => {
      this.dialogRef.close();
    });
  });
  }

  closeDialog(){
    this.dialogRef.close();
  }

}
