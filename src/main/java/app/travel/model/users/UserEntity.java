package app.travel.model.users;

import app.travel.shared.entity.AuditEntity;
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
public class UserEntity extends AuditEntity {

    @JoinColumn(name = "role_id")
    UUID roleId;

    @Column(name = "email")
    String email;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "is_enabled")
    Boolean isEnabled;

}
