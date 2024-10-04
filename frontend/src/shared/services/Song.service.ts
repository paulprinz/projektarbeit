import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Song } from '../models/Song.model';
import { Observable } from 'rxjs';


@Injectable({
    providedIn: 'root'
})

export class SongService {

    constructor(
        private http: HttpClient,
    ) {}
    
    private apiUrl = 'http://localhost:8080/api/songs';


    getSongDetailsById(songId: number): Observable<Song> {
        return this.http.get<Song>(this.apiUrl + '/' + songId);
    }
    
}
