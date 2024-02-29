package at.technikum.springrestbackend.controller;


import at.technikum.springrestbackend.dto.PictureDto;
import at.technikum.springrestbackend.dto.PlaylistDto;
import at.technikum.springrestbackend.dto.UserDto;
import at.technikum.springrestbackend.mapper.PictureMapper;
import at.technikum.springrestbackend.mapper.PlaylistMapper;
import at.technikum.springrestbackend.model.Picture;
import at.technikum.springrestbackend.model.Playlist;
import at.technikum.springrestbackend.service.PictureService;
import at.technikum.springrestbackend.service.PlaylistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;
    private final PlaylistMapper playlistMapper;


    public PlaylistController(PlaylistService playlistService, PlaylistMapper playlistMapper) {
        this.playlistService = playlistService;
        this.playlistMapper = playlistMapper;
    }


    @GetMapping("/all")
    public List<PlaylistDto> getAll(){
        return playlistService.findAll().stream().map(playlistMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public PlaylistDto getById(@PathVariable Long id){
        Playlist playlist = playlistService.find(id);
        return playlistMapper.toDto(playlist);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaylistDto create(@RequestBody @Valid PlaylistDto playlistDto){
        Playlist playlist = playlistMapper.toEntity(playlistDto);
        playlist = playlistService.save(playlist);
        return playlistMapper.toDto(playlist);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        playlistService.deleteById(id);
    }
    
}
