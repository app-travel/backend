package app.travel.model.tour_packs;

import app.travel.shared.entity.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Table(name = "tour_packs")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourPackEntity extends AuditableEntity {

    @Column(name = "pack_name")
    String packName;

    @Column(name = "is_enabled")
    Boolean isEnabled;

}
