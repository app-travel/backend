package app.travel.model.tours;

import app.travel.shared.dto.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import java.util.UUID;

@Table(name = "tours")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourEntity extends AuditableEntity {

    @Column(name = "founder_tour_id")
    UUID founderTourId;

    @Column(name = "tag_tour_id")
    UUID tagTourId;

    @Column(name = "place_depart_id")
    UUID placeDepartId;

    @Column(name = "place_travel_id")
    UUID placeTravelId;

    @Column(name = "tour_name")
    String tourName;

    @Column(name = "tour_code")
    String tourCode;

    @Column(name = "extension_items")
    String extensionItems;

    @Column(name = "rating_avg")
    Float ratingAvg;

    @Column(name = "tour_experience_html")
    String tourExperienceHtml;

    @Column(name = "tour_video_url")
    String tourVideoUrl;

}
