package app.travel.model.user_roles;

import app.travel.shared.dto.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Table(name = "user_roles")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleEntity extends AuditableEntity {

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "role_id")
    UUID roleId;

}
