package app.travel.model.location.districts;

import app.travel.shared.dto.LocationFieldsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Table(name = "districts")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DistrictLocationEntity extends LocationFieldsEntity {

    @JoinColumn(name = "province_id")
    UUID provinceId;

}
