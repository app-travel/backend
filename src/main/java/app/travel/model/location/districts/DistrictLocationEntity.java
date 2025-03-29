package app.travel.model.location.districts;

import app.travel.shared.entity.LocationEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Table(name = "districts_location")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DistrictLocationEntity extends LocationEntity {

    @JoinColumn(name = "province_location_id")
    String provinceLocationId;

}
