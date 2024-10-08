import { Component } from '@angular/core';

@Component({
  selector: 'app-upload-song-dialog',
  templateUrl: './upload-song-dialog.component.html',
})
export class UploadSongDialogComponent {
  songName: string = '';
  artistName: string = '';           
  genre: string = '';      
  selectedFile: File | null = null;  



  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.selectedFile = file;
    }
  }

}
