package app.travel.model.location.wards;

import app.travel.shared.entity.LocationEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Table(name = "wards_location")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WardLocationEntity extends LocationEntity {

    @JoinColumn(name = "district_location_id")
    String districtLocationId;

}
