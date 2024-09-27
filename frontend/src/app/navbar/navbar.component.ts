import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { TokenService } from '../login/token.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
})
export class NavbarComponent implements OnInit {

  username: any;

  constructor(
    public loginService: LoginService,
    public tokenService: TokenService,
    private router: Router,
  ) { }


  ngOnInit(): void {
    this.username = this.tokenService.getUsername();
  }




  // Navigate to Home
  navigateToHome() {
    this.router.navigateByUrl('/');
  }

  // Navigate to Sign Up
  navigateToSignUp() {
    this.router.navigateByUrl('/signup');
  }

  // Navigate to Login
  navigateToLogin() {
    this.router.navigateByUrl('/login');
  }

  // Navigate to MyProfile
  navigateToMyProfile() {
    this.router.navigateByUrl('/me');
  }

  // Navigate to UserManagement
  navigateToUserManagement() {
    this.router.navigateByUrl('/user-management');
  }

  logout() {
    this.loginService.logout();
    // Reload the page
    window.location.reload();
  }
  
}
