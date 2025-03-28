package app.travel.model.invalid_token;

import app.travel.shared.entity.AuditableEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import java.util.UUID;

@Table(name = "invalid_token")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Schema(hidden = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvalidTokenEntity extends AuditableEntity {

    @JoinColumn(name = "user_id")
    UUID userId;

    @Column(name = "token")
    String token;

    @Column(name = "invalid_type")
    String invalidType;

}
