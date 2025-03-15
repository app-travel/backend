package app.travel.model.bookings_tour;

import app.travel.shared.entity.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Table(name = "bookings_tour")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingTourEntity extends AuditableEntity {

    @JoinColumn(name = "schedules_tour_id")
    UUID schedulesTourId;

    @JoinColumn(name = "user_id")
    UUID userId;

    @Column(name = "tickets_number")
    Integer ticketsNumber;

    @Column(name = "money_tour")
    BigDecimal moneyTour;

    @Column(name = "money_tour_type")
    String moneyTourType;

    @Column(name = "booking_status")
    Boolean bookingStatus;

    @Column(name = "tour_image_url")
    String tourImageUrl;

}
