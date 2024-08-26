package se.systementor.javasecstart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private Integer roleId;

    @Column(name="role_type")
    private String roleType;
    @Override
    public String getAuthority() {
        return this.roleType;
    }

}
