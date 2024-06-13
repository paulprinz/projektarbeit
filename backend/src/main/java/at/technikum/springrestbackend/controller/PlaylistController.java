package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.PlaylistDto;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.Playlist;
import at.technikum.springrestbackend.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/playlists")
public class PlaylistController {

    @Autowired
    PlaylistService playlistService;


    @GetMapping("/getAllPlaylists")
    public List<PlaylistDto> getAllPlaylists(){
        return playlistService.findAll().stream()
                .map(playlistService::convertToDto)
                .toList();
    }

    @GetMapping("getPlaylist/{id}")
    public ResponseEntity<PlaylistDto> getById(@PathVariable Long id) throws EntityNotFoundException {
        Playlist playlist = playlistService.findById(id);
        return ResponseEntity.ok(playlistService.convertToDto(playlist));
    }

    @PostMapping("/create")
    public ResponseEntity<PlaylistDto>  create(@RequestBody PlaylistDto playlistDto) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playlistService.convertToDto(playlistService.save(playlistDto)));
    }

    @PutMapping("/updatePlaylist")
    public ResponseEntity<PlaylistDto> updateById(@RequestBody PlaylistDto playlistDto) {
        try {
            Playlist updatedPlaylist = playlistService.updateById(playlistDto);
            return ResponseEntity.ok(playlistService.convertToDto(updatedPlaylist));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("deletePlaylist/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        try {
            playlistService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
