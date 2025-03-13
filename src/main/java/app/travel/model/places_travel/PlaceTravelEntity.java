package app.travel.model.places_travel;

import app.travel.shared.entity.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Table(name = "places_travel")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaceTravelEntity extends AuditableEntity {

    @JoinColumn(name = "tour_id")
    UUID tourId;

    @JoinColumn(name = "place_id")
    UUID placeId;

}
