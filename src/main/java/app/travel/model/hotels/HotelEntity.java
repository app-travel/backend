package app.travel.model.hotels;

import app.travel.shared.entity.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Table(name = "hotels")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelEntity extends AuditableEntity {

    @Column(name = "hotel_name")
    String hotel_name;

    @Column(name = "money")
    BigDecimal money;

}
