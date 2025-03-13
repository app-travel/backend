package app.travel.model.tags_tour;

import app.travel.shared.entity.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Table(name = "tag_tour")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagTourEntity extends AuditableEntity {

    @JoinColumn(name = "tour_id")
    UUID tourId;

    @Column(name = "tag_name")
    String tagName;

    @Column(name = "tag_type")
    String tagType;

}
