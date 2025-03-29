package app.travel.model.comment_feedback;

import app.travel.shared.entity.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Table(name = "comment_feedback")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentFeedbackEntity extends AuditEntity {

    @JoinColumn(name = "feedback_id")
    UUID feedbackId;

    @JoinColumn(name = "founder_tour_id")
    UUID founderTourId;

    @Column(name = "content")
    String content;

}
