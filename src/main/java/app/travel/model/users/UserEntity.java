package app.travel.model.users;

import app.travel.shared.dto.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends AuditableEntity {

    @JoinColumn(name = "role_id")
    UUID roleId;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "enabled")
    Boolean enabled;

}
