package app.travel.model.feedback_galleries;

import app.travel.shared.entity.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Table(name = "feedback_gallery")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedbackGalleryEntity extends AuditableEntity {

    @JoinColumn(name = "feedback_id")
    UUID feedbackId;

    @Column(name = "image_url")
    String imageUrl;

}
