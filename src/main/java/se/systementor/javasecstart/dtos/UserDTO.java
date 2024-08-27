package se.systementor.javasecstart.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;

    private String password;

    public String toString() {
        return "Registration info: username: " + this.username + ", password: " + this.password;
    }
}
