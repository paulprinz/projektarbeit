package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

// TODO - annotations

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String username;

    private String email;

    private String role;

    private LocalDate birthDate;

    private String password;

    private String country;

    private int followerCount;

    private boolean status;

}
