package at.technikum.springrestbackend.dto;

import at.technikum.springrestbackend.model.Song;
import at.technikum.springrestbackend.model.User;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class PlaylistDto {
    private Long id;
    @NotBlank(message = "name is mandatory")
    private String name;
    @OneToOne
    @NotBlank(message = "creator is mandatory")
    private User creator;
    @OneToMany
    private List<Song> songs;


    public PlaylistDto() {
    }

    public PlaylistDto(Long id, String name, User creator, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.songs = songs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
