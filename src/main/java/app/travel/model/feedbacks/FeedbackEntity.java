package app.travel.model.feedbacks;

import app.travel.shared.dto.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Table(name = "feedbacks")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedbackEntity extends AuditableEntity {

    @JoinColumn(name = "user_id")
    UUID userId;

    @JoinColumn(name = "tour_id")
    UUID tourId;

    @Column(name = "rating")
    Float rating;

    @Column(name = "content")
    String content;

}
