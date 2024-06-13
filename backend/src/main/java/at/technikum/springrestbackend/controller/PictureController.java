package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.PictureDto;
import at.technikum.springrestbackend.model.Picture;
import at.technikum.springrestbackend.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/pictures")
public class PictureController {

    @Autowired
    PictureService pictureService;


    @GetMapping("/getPicture/{id}")
    public ResponseEntity<PictureDto> getById(@PathVariable Long id){
        Picture picture = pictureService.findById(id);
        return ResponseEntity.ok(pictureService.convertToDto(picture));
    }

    @PostMapping("/create")
    public ResponseEntity<PictureDto> createPicture(@RequestBody PictureDto pictureDto) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pictureService.convertToDto(pictureService.save(pictureDto)));
    }

    @DeleteMapping("deletePicture/{id}")
    public ResponseEntity<Void> deletePicture(@PathVariable Long id) {
        try {
            pictureService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
