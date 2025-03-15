package app.travel.model.tour_information_notice;

import app.travel.shared.entity.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Table(name = "tour_information_notice")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourIn4mationNoticeEntity extends AuditableEntity {

    @JoinColumn(name = "tour_id")
    UUID tourId;

    @Column(name = "notice_name")
    String noticeName;

    @Column(name = "information_html")
    String informationHtml;

}
