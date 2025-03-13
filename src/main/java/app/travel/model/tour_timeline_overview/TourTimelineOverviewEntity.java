package app.travel.model.tour_timeline_overview;

import app.travel.shared.dto.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Table(name = "tour_timeline_overview")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourTimelineOverviewEntity extends AuditableEntity {

    @JoinColumn(name = "tour_timeline_id")
    UUID tourTimelineId;

    @Column(name = "overview_html")
    String overviewHtml;

    @Column(name = "image_url")
    String imageUrl;

    @Column(name = "image_name")
    String imageName;

}
