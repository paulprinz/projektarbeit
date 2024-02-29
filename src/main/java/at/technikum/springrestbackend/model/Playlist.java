package at.technikum.springrestbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private User creator;

    @OneToMany
    private List<Song> songs;


    public Playlist() {
    }

    public Playlist(Long id, String name, User creator, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.songs = songs;
    }

    public Playlist(String name, User creator, List<Song> songs) {
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
