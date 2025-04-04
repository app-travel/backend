package app.travel.model.location.provinces;

import app.travel.shared.entity.LocationEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Table(name = "provinces_location")
@Entity
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProvinceLocationEntity extends LocationEntity {
}
