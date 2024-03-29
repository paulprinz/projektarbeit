package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.SongDto;
import at.technikum.springrestbackend.mapper.SongMapper;
import at.technikum.springrestbackend.model.Song;
import at.technikum.springrestbackend.service.SongService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final SongMapper songMapper;


    public SongController(SongService songService, SongMapper songMapper) {
        this.songService = songService;
        this.songMapper = songMapper;
    }

    @GetMapping("/all")
    public List<SongDto> getAll(){
        return songService.findAll().stream().map(songMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public SongDto getById(@PathVariable Long id){
        Song song = songService.find(id);
        return songMapper.toDto(song);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SongDto create(@RequestBody @Valid SongDto songDto){
        Song song = songMapper.toEntity(songDto);
        song = songService.save(song);
        return songMapper.toDto(song);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        songService.deleteById(id);
    }
    @PutMapping("/{id}")
    public SongDto updateById(@PathVariable Long id, @RequestBody @Valid SongDto songDto) {
        Song song = songService.updateById(id,songDto);
        return songMapper.toDto(song);
    }
}
