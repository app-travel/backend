package app.travel.model.roles;

import app.travel.common.constant.Role;
import app.travel.shared.entity.AuditEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Table(name = "roles")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity extends AuditEntity {

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    Role roleName;

}
