package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import lombok.*;

// TODO - annotations

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String fileLink;

    // TODO - join table
    private Long userId;

}
