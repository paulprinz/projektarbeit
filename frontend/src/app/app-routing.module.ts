import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuard } from '../shared/services/Auth.guard';


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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
