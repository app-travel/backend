package app.travel.model.profile_user;

import app.travel.shared.entity.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Table(name = "profile_user")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileUserEntity extends AuditEntity {

    @JoinColumn(name = "user_id")
    UUID userId;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "identify_code")
    String identifyCode;

    @Column(name = "profile_image")
    String profileImage;

}
