import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
    providedIn: 'root'
})

export class FileService {
  
    constructor(
        private http: HttpClient,
    ) {}
    
    private apiUrl = 'http://localhost:8080/api/files';
    private avatarUrl = '/avatars';


    uploadAvatar(file: File): Observable<string> {
        const formData: FormData = new FormData();
        formData.append('file', file, file.name);
        return this.http.post<string>(this.apiUrl + this.avatarUrl, formData, { responseType: 'text' as 'json' });
    }

    downloadAvatar(userId: number): Observable<HttpEvent<Blob>> {
        return this.http.get<Blob>(this.apiUrl + this.avatarUrl + '/' + userId, {
            observe: 'events',
            responseType: 'blob' as 'json'
        });
    }
    
    deleteAvatar(userId: number): Observable<void> {
        return this.http.delete<void>(this.apiUrl + this.avatarUrl + '/' + userId);
    }

}
