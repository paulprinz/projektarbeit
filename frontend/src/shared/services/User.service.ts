import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDetails } from '../models/UserDetails.model';


@Injectable({
    providedIn: 'root'
})

export class UserService {
  
    constructor(
        private http: HttpClient,
    ) {}
    
    private apiUrl = 'http://localhost:8080/api/users';
    private getUserByNameUrl = '/get-user-by-name/';
    private getUserByIdUrl = '/get/';
    private getMyUserUrl = '/me';

    getUserDetailsByName(username: string): Observable<UserDetails> {
        return this.http.get<UserDetails>(this.apiUrl + this.getUserByNameUrl + username);
    }

    getUserDetailsById(userId: number): Observable<UserDetails> {
        return this.http.get<UserDetails>(this.apiUrl + this.getUserByIdUrl + userId);
    }

    getMyProfile(): Observable<UserDetails> {
        return this.http.get<UserDetails>(this.apiUrl + this.getMyUserUrl);
    }

}
