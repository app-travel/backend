package app.travel.model.packs_of_tour;

import app.travel.shared.entity.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Table(name = "packs_of_tour")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PackOfTourEntity extends AuditableEntity {

    @JoinColumn(name = "tour_id")
    UUID tourId;

    @JoinColumn(name = "tour_pack_id")
    UUID tourPackId;

}
