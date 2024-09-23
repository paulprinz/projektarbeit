import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuard } from '../shared/services/Auth.guard';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserManagementComponent } from './user-management/user-management.component';


const routes: Routes = [
  { path: 'login', 
    component: LoginComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'signup',
    component: RegisterComponent,
    canActivate: [AuthGuard]
  },
  {
    path: '',
    component: HomeComponent,
    // canActivateChild: [AuthGuard]
    /* For components inside the APP
    children: [

    ]
    */
  },
  {
    path: 'me',
    component: UserDetailsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'user/:id',
    component: UserDetailsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'user-management',
    component: UserManagementComponent,
    canActivate: [AuthGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
