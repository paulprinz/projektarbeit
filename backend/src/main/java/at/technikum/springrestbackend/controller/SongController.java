package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.SongDto;
import at.technikum.springrestbackend.model.Song;
import at.technikum.springrestbackend.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/songs")
public class SongController {

    @Autowired
    SongService songService;


    @GetMapping("/getAllSongs")
    public List<SongDto> getAllSongs(){
        return songService.findAll().stream()
                .map(songService::convertToDto)
                .toList();
    }

    @GetMapping("/getSong/{id}")
    public ResponseEntity<SongDto>  getSongById(@PathVariable Long id){
        Song song = songService.findById(id);
        return ResponseEntity.ok(songService.convertToDto(song));
    }

    @PostMapping("/create")
    public ResponseEntity<SongDto> createSong(@RequestBody SongDto songDto) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(songService.convertToDto(songService.save(songDto)));
    }

    @DeleteMapping("deleteSong/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id){
        try {
            songService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/updateSong")
    public ResponseEntity<SongDto> updateSong(@RequestBody SongDto songDto) {
        try {
            Song updatedSong = songService.updateById(songDto);
            return ResponseEntity.ok(songService.convertToDto(updatedSong));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
