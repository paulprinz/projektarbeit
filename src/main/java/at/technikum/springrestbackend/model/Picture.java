package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileLink;


    public Picture() {
    }

    public Picture(Long id, String fileLink) {
        this.id = id;
        this.fileLink = fileLink;
    }

    public Picture(String fileLink) {
        this.fileLink = fileLink;
    }

    public Long getId() {
        return id;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }
}
