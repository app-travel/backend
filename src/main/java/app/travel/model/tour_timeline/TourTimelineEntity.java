package app.travel.model.tour_timeline;

import app.travel.shared.entity.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Table(name = "tour_timeline")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourTimelineEntity extends AuditEntity {

    @JoinColumn(name = "tour_id")
    UUID tourId;

    @Column(name = "tour_name")
    String tourName;

    @Column(name = "tour_day")
    Integer tourDay;

    @Column(name = "image_url")
    String imageUrl;

}
