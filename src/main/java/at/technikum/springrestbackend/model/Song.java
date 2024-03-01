package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "artist is mandatory")
    private String artist;
    private Long length; // in seconds

    private int likeCount;
    @Transient
    private List<String> comments;
    @NotBlank(message = "fileLink is mandatory")
    private String fileLink;

    private String genre;
    @OneToOne
    private Picture picture;


    public Song() {
    }

    public Song(
            Long id,
            String name,
            String artist,
            Long length,
            int likeCount,
            List<String> comments,
            String fileLink,
            String genre,
            Picture picture
    ) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.length = length;
        this.likeCount = likeCount;
        this.comments = comments;
        this.fileLink = fileLink;
        this.genre = genre;
        this.picture = picture;
    }

    public Song(
            String name,
            String artist,
            Long length,
            int likeCount,
            List<String> comments,
            String fileLink,
            String genre,
            Picture picture
    ) {
        this.name = name;
        this.artist = artist;
        this.length = length;
        this.likeCount = likeCount;
        this.comments = comments;
        this.fileLink = fileLink;
        this.genre = genre;
        this.picture = picture;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
