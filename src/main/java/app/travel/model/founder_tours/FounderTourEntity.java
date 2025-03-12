package app.travel.model.founder_tours;

import app.travel.shared.dto.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Table(name = "founder_tours")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FounderTourEntity extends AuditableEntity {

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "company_name")
    String companyName;

    @Column(name = "email")
    String email;

    @Column(name = "address")
    String address;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "ward_location_id")
    UUID wardLocationId;

    @Column(name = "enterprise_code")
    String enterpriseCode;

}
