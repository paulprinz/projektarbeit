package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

// TODO - annotations

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String artist;

    private Long length;

    private int likeCount;

    // TODO - create a comment model
    @Transient
    private List<String> comments;

    private String fileLink;

    private String genre;

    // TODO - join table
    private Long user_id;

    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists = new ArrayList<>();


}
