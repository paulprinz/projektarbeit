package at.technikum.springrestbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDto {
    private Long id;

    private Long userId;

    private String name;

    private String artist;

    private int likeCount;

    private String genre;


    public SongDto(
            Long id,
            Long userId,
            String name,
            String artist,
            int likeCount,
            String genre
    ) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.artist = artist;
        this.likeCount = likeCount;
        this.genre = genre;
    }

}
