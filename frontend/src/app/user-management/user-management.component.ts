import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FileService } from '../../shared/services/File.service';
import { HttpEvent, HttpEventType, HttpResponse } from '@angular/common/http';
import { UserService } from '../../shared/services/User.service';
import { UserDetails } from '../../shared/models/UserDetails.model';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserDto } from '../../shared/models/UserDto.model';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort, Sort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';



@Component({
  selector: 'app-user-details',
  templateUrl: './user-management.component.html'
})
export class UserManagementComponent implements OnInit, AfterViewInit {
  
  length: number | undefined;
  pageIndex = 0; 
  pageEvent: PageEvent | undefined;
  filter = '';
  sortField = 'lastName';
  sortDirection = 'asc';
  sort = 'ascending';

  pageSizeOptions: number[] | undefined;

  displayedUsersColumns: string[] = ['userName', 'active'];
  
  userId: number | undefined;
  userDetails: UserDto | undefined = {} as UserDto;
  users: UserDto[] | undefined;
  availableUsers: MatTableDataSource<UserDto> = new MatTableDataSource<UserDto>([]);
  filteredUsers: UserDto[] = [];
  active: boolean = true;

  @ViewChild(MatSort) usersSort: MatSort | undefined;

  constructor(
    private userService: UserService,
    private paginator: MatPaginator,
    public snackBar: MatSnackBar,
    public router: Router,
    private dialog: MatDialog,
  ) { }


  
  ngAfterViewInit(): void {
      this.availableUsers.paginator = this.paginator;
  }

  ngOnInit(): void {

    
    this.loadUsers();
  }

  async loadUsers() {
    try {
      const pageIndex =this.pageIndex;
      const active = this.active;
      const sort = this.sortField + "," + this.sortDirection;
      const filter = this.filter;
      
      await this.userService.getUsersWithFilterSort(pageIndex, active, filter, sort).subscribe(data => {
        this.users = data.content;
        this.length = data.totalItems;
        this.availableUsers = new MatTableDataSource(this.users);
      });
    } catch (error) {
      console.error('Error loading users:', error);
    }

  }

  sortData(sort: any) {
    this.sortField = sort.active;
    if (sort.direction.length === 0 && this.sortDirection === 'asc') {
      sort.direction = 'desc';
    } else if (sort.direction.length === 0 && this.sortDirection === 'desc') {
      sort.direction = 'asc';
    } 
    this.sortDirection = sort.direction;
    this.loadUsers();
  }


   //Filter active/inactive
  updateFilteredUsers() {
    this.loadUsers();
  }

  
  applyFilter() {
    this.pageIndex = 0;
    this.paginator.pageIndex = 0;
    this.loadUsers();
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 3000, 
      horizontalPosition: 'start', 
      verticalPosition: 'bottom' 
    });
  }

  handlePageEvent(event: PageEvent) {
    this.pageEvent = event;
    this.length = event.length;
    this.pageIndex = event.pageIndex;
    this.loadUsers();
  }

  navigateToSelectedUser(selectedUserId: number) {
    if (selectedUserId === -1) {
      this.router.navigateByUrl('userDetails/create');
    } else {
      this.router.navigate(['/userDetails', selectedUserId]);
    }
  }


}