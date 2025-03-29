package app.travel.model.tokens;

import app.travel.shared.entity.AuditEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import java.util.UUID;

@Table(name = "tokens")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Schema(hidden = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenEntity extends AuditEntity {

    @JoinColumn(name = "user_id")
    UUID userId;

    @Column(name = "token")
    String token;

    @Column(name = "token_type")
    String tokenType;

}
