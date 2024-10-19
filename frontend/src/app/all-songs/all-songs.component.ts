import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SongService } from '../../shared/services/Song.service';
import { UserDto } from '../../shared/models/UserDto.model';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { SongDto } from '../../shared/models/SongDto.model';
import { TokenService } from '../../app/login/token.service'


@Component({
  selector: 'app-user-details',
  templateUrl: './all-songs.component.html'
})
export class AllSongsComponent implements OnInit, AfterViewInit {
  
  length: number | undefined;
  pageIndex = 0; 
  pageEvent: PageEvent | undefined;
  filter = '';
  sortField = 'name';
  sortDirection = 'asc';
  sort = 'ascending';

  pageSizeOptions: number[] | undefined;

  displayedUsersColumns: string[] = ['name', 'artist', 'genre', 'likeCount', 'actions'];
  
  userId: number | undefined;
  userDetails: UserDto | undefined = {} as UserDto;
  songs: SongDto[] | undefined;
  availableSongs: MatTableDataSource<SongDto> = new MatTableDataSource<SongDto>([]);
  filteredUsers: UserDto[] = [];

  @ViewChild(MatSort) usersSort: MatSort | undefined;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private songService: SongService,
    public tokenService: TokenService,
    public snackBar: MatSnackBar,
    public router: Router,
  ) { }
  
  ngAfterViewInit(): void {
    this.availableSongs.paginator = this.paginator;
  }

  ngOnInit(): void {
    this.userId = Number(this.tokenService.getUserId())
    this.loadSongs();
    localStorage.setItem('returnUrl', "/all-songs");
  }

  async loadSongs() {
    try {
      const pageIndex =this.pageIndex;
      const sort = this.sortField + "," + this.sortDirection;
      const filter = this.filter;
      
      await this.songService.getSongsWithFilterSort(pageIndex, filter, sort).subscribe(data => {
        this.songs = data.content;
        this.length = data.totalItems;
        this.availableSongs = new MatTableDataSource(this.songs);
      });
    } catch (error) {
      console.error('Error loading users:', error);
    }
  }

  deleteSong(songId: number) {
    if (confirm('Are you sure you want to delete this song?')) {
      this.songService.deleteSong(songId).subscribe({
        next: () => {
          this.openSnackBar('Song deleted successfully');
          this.loadSongs(); // Reload the list after deletion
        },
        error: (error) => {
          console.error('Error deleting song:', error);
          this.openSnackBar('Failed to delete song');
        }
      });
    }
  }

  likeSong(songId: number) {
      this.songService.likeSong(songId).subscribe({
        next: () => {
          this.openSnackBar('You liked a song!');
          this.loadSongs(); // Reload the list after deletion
        },
        error: (error) => {
          console.error('Error liking song:', error);
          this.openSnackBar('Failed to like a song');
        }
      });
  }
  
  sortData(sort: any) {
    this.sortField = sort.active;
    if (sort.direction.length === 0 && this.sortDirection === 'asc') {
      sort.direction = 'desc';
    } else if (sort.direction.length === 0 && this.sortDirection === 'desc') {
      sort.direction = 'asc';
    } 
    this.sortDirection = sort.direction;
    this.loadSongs();
  }

  //Filter active/inactive
  updateFilteredSongs() {
    this.loadSongs();
  }

  applyFilter() {
    this.pageIndex = 0;
    this.paginator.pageIndex = 0;
    this.loadSongs();
  }

  handlePageEvent(event: PageEvent) {
    this.pageEvent = event;
    this.length = event.length;
    this.pageIndex = event.pageIndex;
    this.loadSongs();
  }

  navigateToSong(songId: number): void {
    this.router.navigate(['/track', songId]); 
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 3000, 
      horizontalPosition: 'start', 
      verticalPosition: 'bottom' 
    });
  }

}