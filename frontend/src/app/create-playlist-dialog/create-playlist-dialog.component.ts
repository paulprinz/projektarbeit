import { Component } from '@angular/core';
import { PlaylistService } from '../../shared/services/Playlist.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialogRef } from '@angular/material/dialog';
import { TokenService } from '../login/token.service';

@Component({
  selector: 'app-create-playlist-dialog',
  templateUrl: './create-playlist-dialog.component.html'
})
export class CreatePlaylistDialogComponent {
  playlistName: string = '';

  constructor(
    private playlistService: PlaylistService,
    public dialogRef: MatDialogRef<CreatePlaylistDialogComponent>,
    private snackBar: MatSnackBar,
    private tokenService: TokenService,
  ) {}

  onCreatePlaylist(): void {
    if (this.playlistName.trim()) {
      const newPlaylist = { id: 0, name: this.playlistName, userId: this.tokenService.getUserId()!, songs: [] };
      this.playlistService.createPlaylist(newPlaylist).subscribe({
        next: (createdPlaylist) => {
          this.snackBar.open('Playlist created successfully', 'Close', { duration: 3000 });
          this.dialogRef.close(createdPlaylist);
        },
        error: () => {
          this.snackBar.open('Failed to create playlist', 'Close', { duration: 3000 });
        }
      });
    }
  }

  onClose(): void {
    this.dialogRef.close();
  }
}
