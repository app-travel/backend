package app.travel.model.tag_tours;

import app.travel.shared.entity.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Table(name = "tag_tours")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagTourEntity extends AuditEntity {

    @Column(name = "tag_name")
    String tagName;

    @Column(name = "tag_type")
    String tagType;

}
