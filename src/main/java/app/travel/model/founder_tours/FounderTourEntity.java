package app.travel.model.founder_tours;

import app.travel.shared.entity.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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

    @JoinColumn(name = "user_id")
    UUID userId;

    @JoinColumn(name = "ward_location_id")
    UUID wardLocationId;

    @Column(name = "company_name")
    String companyName;

    @Column(name = "email")
    String email;

    @Column(name = "address")
    String address;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "enterprise_code")
    String enterpriseCode;

    @Column(name = "address_location")
    String addressLocation;

}
