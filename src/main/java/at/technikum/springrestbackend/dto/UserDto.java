package at.technikum.springrestbackend.dto;

import at.technikum.springrestbackend.model.Picture;
import at.technikum.springrestbackend.model.Playlist;
import at.technikum.springrestbackend.model.Song;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Date;
import java.util.List;

public class UserDto {
    private String id;
    private String nickname;
    private String email;
    private String role;
    private Date birthday;

    private String password;
    private String country;
    @OneToOne
    private Picture profilePicture;
    @OneToMany
    private List<Song> songs;
    @OneToMany
    private List<Playlist> playlists;

    private int followerCount;

    private boolean status;

    public UserDto() {
    }

    public UserDto(
            String id,
            String nickname,
            String email,
            String role,
            Date birthday,
            String password,
            String country,
            Picture profilePicture,
            List<Song> songs,
            List<Playlist> playlists,
            int followerCount,
            boolean status
    ) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.birthday = birthday;
        this.password = password;
        this.country = country;
        this.profilePicture = profilePicture;
        this.songs = songs;
        this.playlists = playlists;
        this.followerCount = followerCount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
