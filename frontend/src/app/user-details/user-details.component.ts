import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FileService } from '../../shared/services/File.service';
import { HttpEvent, HttpEventType, HttpResponse } from '@angular/common/http';
import { UserService } from '../../shared/services/User.service';
import { UserDetails } from '../../shared/models/UserDetails.model';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html'
})
export class UserDetailsComponent implements OnInit {
  // Avatar
  selectedFile: File | null = null;
  fileUrl: string = '';

  // User
  userDetails: UserDetails | undefined;
  selectedUserId: number | undefined;
  userId: number | undefined;
  username: string | undefined;

  constructor(
    private snackBar: MatSnackBar,
    private fileService: FileService,
    private userService: UserService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    // Get the ID from the URL
    this.route.paramMap.subscribe(async (params: ParamMap) => {
      const userIdParam = params.get('id');
  
      if (userIdParam) {
        // Load user details by ID
        this.selectedUserId = +userIdParam;
        this.loadUserDetailsById();
      } else {
        // Load details for the logged-in user
        this.loadMyUserDetails();
      }
    });
  }
  
  loadUserDetailsById() {
    if (this.selectedUserId) {
      this.userService.getUserDetailsById(this.selectedUserId).subscribe(
        (data: UserDetails) => {
          this.userDetails = data;
          this.userId = data.id;
          this.username = data.username;
          this.loadAvatar();
        },
        error => {
          this.openSnackBar('Error loading user details.');
        }
      );
    }
  }

  loadMyUserDetails() {
    this.userService.getMyProfile().subscribe(
      (data: UserDetails) => {
        this.userDetails = data;
        this.userId = data.id;
        this.username = data.username;
        this.loadAvatar();
      },
      error => {
        this.openSnackBar('Error loading my profile.');
      }
    );
  }
  
  // Avatar
  onFileChange(event: any) {
    const files = event.target.files as FileList;
  
    if (files.length > 0) {
      this.selectedFile = files[0];
      this.fileUrl = URL.createObjectURL(this.selectedFile);
      this.resetInput();   
    }
  }

  resetInput(): void {
    const input = document.getElementById('avatar-input-file') as HTMLInputElement;
    if (input) {
      input.value = "";
    }
  }

  loadAvatar(): void {
    if (this.userId) {
      this.fileService.downloadAvatar(this.userId).subscribe(
        (event: HttpEvent<Blob>) => {
          if (event.type === HttpEventType.Response) {
            const httpResponse = event as HttpResponse<Blob>;
                  
            if (httpResponse.status === 200 && httpResponse.body) {
              const urlCreator = window.URL || window.webkitURL;
              this.fileUrl = urlCreator.createObjectURL(httpResponse.body);
            } else {
              this.fileUrl = '';
            }
          }
        },
        error => {
          this.fileUrl = '';
        }
      );
    }
  }

  uploadAvatar(): void {
    if (this.selectedFile) {
      this.fileService.uploadAvatar(this.selectedFile).subscribe(
        response => {
          this.openSnackBar('Avatar uploaded successfully!');
        },
        error => {
          this.openSnackBar('Error uploading avatar.');
        }
      );
    }
  }

  deleteAvatar(): void {
    if (this.userDetails?.id) {
      this.fileService.deleteAvatar(this.userDetails.id).subscribe(
        () => {
          this.openSnackBar('File deleted successfully');
          this.fileUrl = '';
        },
        error => {
          this.openSnackBar('Error deleting file!');
        }
      );
    }
  }

  openSnackBar(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 3000,
      horizontalPosition: 'start',
      verticalPosition: 'bottom'
    });
  }

}