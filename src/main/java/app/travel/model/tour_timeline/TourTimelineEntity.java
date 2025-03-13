package app.travel.model.tour_timeline;

import app.travel.shared.dto.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table(name = "tour_timeline")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourTimelineEntity extends AuditableEntity {

    @JoinColumn(name = "tour_id")
    UUID tourId;

    @Column(name = "tour_name")
    String tourName;

    @Column(name = "tour_day")
    OffsetDateTime tourDay;

    @Column(name = "image_url")
    String imageUrl;

}
