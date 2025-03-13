package app.travel.model.location.wards;

import app.travel.shared.entity.LocationFieldsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import java.util.UUID;

@Table(name = "wards")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WardLocationEntity extends LocationFieldsEntity {

    @JoinColumn(name = "district_id")
    UUID districtId;

}
