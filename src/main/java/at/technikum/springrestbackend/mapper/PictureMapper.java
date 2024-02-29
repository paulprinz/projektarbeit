package at.technikum.springrestbackend.mapper;

import at.technikum.springrestbackend.dto.PictureDto;
import at.technikum.springrestbackend.model.Picture;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PictureMapper {

    public PictureDto toDto(Picture picture){
        PictureDto pictureDto = new PictureDto();
        pictureDto.setId(picture.getId());
        pictureDto.setFileLink(picture.getFileLink());
        return pictureDto;
    }

    public Picture toEntity(PictureDto pictureDto){
        if (pictureDto.getId()==null){
            return new Picture (
                    pictureDto.getFileLink()
            );
        }
        return new Picture(
                pictureDto.getId(),
                pictureDto.getFileLink()
        );
    }
}
