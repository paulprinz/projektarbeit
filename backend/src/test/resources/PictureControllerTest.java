package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.PictureDto;
import at.technikum.springrestbackend.model.Picture;
import at.technikum.springrestbackend.service.PictureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
public class PictureControllerTest {

    @InjectMocks
    private PictureController pictureController;

    @Mock
    private PictureService pictureService;

    private Picture picture;
    private PictureDto pictureDto;

    @BeforeEach
    void setUp() {
        picture = new Picture();
        picture.setId(1L);
        picture.setName("Test Picture");

        pictureDto = new PictureDto();
        pictureDto.setId(1L);
        pictureDto.setName("Test Picture Dto");
    }

    @Test
    void testGetById() {
        Mockito.when(pictureService.findById(anyLong())).thenReturn(picture);
        Mockito.when(pictureService.convertToDto(any(Picture.class))).thenReturn(pictureDto);

        ResponseEntity<PictureDto> response = pictureController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pictureDto, response.getBody());
    }

    @Test
    void testCreatePicture() throws Exception {
        Mockito.when(pictureService.save(any(PictureDto.class))).thenReturn(picture);
        Mockito.when(pictureService.convertToDto(any(Picture.class))).thenReturn(pictureDto);

        ResponseEntity<PictureDto> response = pictureController.createPicture(pictureDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pictureDto, response.getBody());
    }

    @Test
    void testDeletePicture() {
        Mockito.doNothing().when(pictureService).deleteById(anyLong());

        ResponseEntity<Void> response = pictureController.deletePicture(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeletePictureException() {
        Mockito.doThrow(new RuntimeException()).when(pictureService).deleteById(anyLong());

        ResponseEntity<Void> response = pictureController.deletePicture(1L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
