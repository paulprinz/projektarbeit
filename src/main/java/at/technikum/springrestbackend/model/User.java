package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;
    @NotBlank(message = "nickname is mendatory")
    private String nickname;
    @Email
    @NotBlank(message = "email is mendatory")
    private String email;
    private String role;
    @NotBlank(message = "birthday is mendatory")
    @Past
    private Date birthday;

    @Size(min = 6, max = 250)
    private String password;
    @NotBlank(message = "country is mendatory")
    private String country;
    @OneToOne
    private Picture profilePicture;
    @OneToMany
    private List<Song> songs;
    @OneToMany
    private List<Playlist> playlists;

    private int followerCount;

    private boolean status;


    public User() {
    }

    public User(
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

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
