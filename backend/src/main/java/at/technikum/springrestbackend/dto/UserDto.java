package at.technikum.springrestbackend.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
public class UserDto {
    private Long id;

    private String username;

    private String email;

    private String role;

    private LocalDate birthDate;

    private String password;

    private String country;

    private int followerCount;

    private boolean status;


    public UserDto(
            Long id,
            String username,
            String email,
            String role,
            LocalDate birthDate,
            String password,
            String country,
            int followerCount,
            boolean status
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.birthDate = birthDate;
        this.password = password;
        this.country = country;
        this.followerCount = followerCount;
        this.status = status;
    }

}
