package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.PictureDto;
import at.technikum.springrestbackend.mapper.PictureMapper;
import at.technikum.springrestbackend.model.Picture;
import at.technikum.springrestbackend.service.PictureService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pictures")
public class PictureController {
    private final PictureService pictureService;
    private final PictureMapper pictureMapper;


    public PictureController(PictureService pictureService, PictureMapper pictureMapper) {
        this.pictureService = pictureService;
        this.pictureMapper = pictureMapper;
    }

    @GetMapping("/{id}")
    public PictureDto getById(@PathVariable Long id){
        Picture picture = pictureService.find(id);
        return pictureMapper.toDto(picture);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PictureDto create(@RequestBody @Valid PictureDto pictureDto){
        Picture picture = pictureMapper.toEntity(pictureDto);
        picture = pictureService.save(picture);
        return pictureMapper.toDto(picture);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        pictureService.deleteById(id);
    }
}
