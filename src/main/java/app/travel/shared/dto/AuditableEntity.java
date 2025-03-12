package app.travel.shared.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuditableEntity {



}
