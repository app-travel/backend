package app.travel.shared.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuditableEntity {

    @Id
    @Column(name = "id")
    UUID id;

    @Column(name = "created_at")
    OffsetDateTime createdAt;

    @Column(name = "updated_at")
    OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID();
        createdAt = OffsetDateTime.now(ZoneId.systemDefault());
        updatedAt = OffsetDateTime.now(ZoneId.systemDefault());
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = OffsetDateTime.now(ZoneId.systemDefault());
    }

}
