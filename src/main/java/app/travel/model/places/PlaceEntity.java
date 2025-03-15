package app.travel.model.places;

import app.travel.shared.entity.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Table(name = "places")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaceEntity extends AuditableEntity {

    @Column(name = "place_name")
    String placeName;

    @Column(name = "is_foreign")
    Boolean isForeign;

}
