package app.travel.model.users;

import app.travel.shared.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

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

    @Column(name = "is_enabled")
    Boolean isEnabled;

}
