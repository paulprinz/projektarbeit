import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FileService } from '../../shared/services/File.service';
import { HttpEvent, HttpEventType, HttpResponse } from '@angular/common/http';
import { UserService } from '../../shared/services/User.service';
import { UserDetails } from '../../shared/models/UserDetails.model';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PasswordChangeDto } from '../../shared/models/PasswordChangeDto.model';
import { TokenService } from '../login/token.service';
import { delay, map, Observable, of } from 'rxjs';

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
  userIdParam: string | null | undefined;
  roles: string[] = ['ROLE_ADMIN', 'ROLE_USER'];

  // User profile form
  userProfileForm: FormGroup = this.fb.group({
    id: ['', Validators.required],
    username:  ['', Validators.required],
    email: ['', [Validators.required, Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")], [this.asyncEmailValidator]],
    role: ['', Validators.required],
    birthDate: ['', Validators.required],
    country: ['', [Validators.required, Validators.pattern(/^[^\d]*$/)]],
    followerCount:  ['', Validators.required],
    active:  ['', Validators.required],
  });

  // Password change
  passwordForm: FormGroup = this.fb.group({
    oldPassword: ['', Validators.required],
    newPassword: ['', Validators.required],
    confirmNewPassword: ['', Validators.required],
  });

  constructor(
    private snackBar: MatSnackBar,
    private fileService: FileService,
    private userService: UserService,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    public tokenService: TokenService,
  ) { }

  ngOnInit(): void {
    // Get the ID from the URL
    this.route.paramMap.subscribe(async (params: ParamMap) => {
      this.userIdParam = params.get('id');
  
      if (this.userIdParam) {
        // Load user details by ID
        this.selectedUserId = +this.userIdParam;
        this.loadUserDetailsById();
      } else {
        // Load details for the logged-in user
        this.loadMyUserDetails();
      }
    });
  }

  async loadUserDetailsById(): Promise<void> {
    if (this.selectedUserId) {
      return new Promise((resolve, reject) => {
        this.userService.getUserDetailsById(this.selectedUserId!).subscribe(
          (data: UserDetails) => {
            this.userDetails = data;
            this.userId = data.id;
            this.loadAvatar();
            this.userProfileForm.patchValue(data);
            resolve();
          },
          error => {
            this.openSnackBar('Error loading user details.');
            reject(error);
          }
        );
      });
    }
  }

  async loadMyUserDetails(): Promise<void> {
    return new Promise((resolve, reject) => {
      this.userService.getMyProfile().subscribe(
        (data: UserDetails) => {
          this.userDetails = data;
          this.userId = data.id;
          this.loadAvatar();
          this.userProfileForm.patchValue(data);
          resolve();
        },
        error => {
          this.openSnackBar('Error loading my profile.');
          reject();
        }
      );
    });
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
      this.fileService.uploadAvatar(this.selectedFile).subscribe();
    }
  }

  deleteAvatar(): void {
    if (this.userDetails?.id) {
      this.fileService.deleteAvatar(this.userDetails.id).subscribe(
        () => {
          this.openSnackBar('Avatar deleted successfully');
          this.fileUrl = '';
        },
        error => {
          this.openSnackBar('Error deleting avatar!');
        }
      );
    }
  }

  updateUser(): void {
    const updatedData: UserDetails = this.userProfileForm.value;

    if(this.userProfileForm.valid){
      this.userService.updateUser(updatedData).subscribe({
        next: () => {
          this.openSnackBar(this.userDetails?.username + ' updated successfully!');
        },
        error: (error) => {
          console.error('Error updating user:', error);
          this.openSnackBar('Failed to update user');
        }
      });
    } 
  }

  saveChanges(): void {
    if(this.selectedFile){
      this.uploadAvatar()
    }
    this.updateUser();
  }

  changePassword(): void {
    // Gets the form values
    const oldPassword = this.passwordForm.value.oldPassword;
    const newPassword = this.passwordForm.value.newPassword;
    const confirmNewPassword = this.passwordForm.value.confirmNewPassword;

    // Check if the new password and confirm new password match
    if (newPassword !== confirmNewPassword) {
      this.openSnackBar('New password and confirm new password do not match.');
      return;
    }

    // Check if the user typed the same password as his old password
    if(oldPassword == newPassword) {
      this.openSnackBar('Old password can not be new password!');
      return;
    }

    const passwordDto: PasswordChangeDto = {
      oldPassword: oldPassword,
      newPassword: newPassword,
    };
    
    // Makes an API call to update the user's password
    this.userService.changePassword(passwordDto).subscribe({
      next: (response) => {
        this.openSnackBar('Password updated successfully.');
        // Clears the form values
        this.passwordForm.reset();
      },
      error: (error) => {
        this.openSnackBar(error.error);
      }
    });
  }

  asyncEmailValidator(control: AbstractControl): Observable<{ [key: string]: any } | null> {
    return of(control.value).pipe(
      delay(2000),
      map(value => {
        const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        if (!regex.test(value)) {
          return { invalidEmail: true };
        } else {
          return null;
        }
      })
    );
  }

  openSnackBar(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 3000,
      horizontalPosition: 'start',
      verticalPosition: 'bottom'
    });
  }

}