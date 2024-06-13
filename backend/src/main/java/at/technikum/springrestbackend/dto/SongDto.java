package at.technikum.springrestbackend.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SongDto {
    private Long id;

    private String name;

    private String artist;

    private Long length;

    private int likeCount;

    private List<String> comments;

    private String fileLink;

    private String genre;

    private Long user_id;


    public SongDto(
            Long id,
            String name,
            String artist,
            Long length,
            int likeCount,
            List<String> comments,
            String fileLink,
            String genre,
            Long userId
    ) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.length = length;
        this.likeCount = likeCount;
        this.comments = comments;
        this.fileLink = fileLink;
        this.genre = genre;
        this.user_id = userId;
    }

}
