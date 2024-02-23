package at.technikum.springrestbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Picture {
    @Id
    private Long id;
    private String fileLink;


    public Picture() {
    }

    public Picture(Long id, String fileLink) {
        this.id = id;
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
