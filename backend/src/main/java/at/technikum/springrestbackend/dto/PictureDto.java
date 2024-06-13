package at.technikum.springrestbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PictureDto {
    private Long id;

    private String fileLink;

    private Long userId;

    public PictureDto(Long id, String fileLink, Long userId) {
        this.id = id;
        this.fileLink = fileLink;
        this.userId = userId;
    }

}
