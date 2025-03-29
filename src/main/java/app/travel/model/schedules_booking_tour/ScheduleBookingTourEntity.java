package app.travel.model.schedules_booking_tour;

import app.travel.shared.entity.AuditEntity;
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

@Table(name = "schedules_booking_tour")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleBookingTourEntity extends AuditEntity {

    @JoinColumn(name = "tour_id")
    UUID tourId;

    @JoinColumn(name = "hotel_id")
    UUID hotelId;

    @Column(name = "start_date")
    OffsetDateTime startDate;

    @Column(name = "end_date")
    OffsetDateTime endDate;

    @Column(name = "remaining_tickets")
    Integer remainingTickets;

    @Column(name = "tickets_status")
    Boolean ticketsStatus;

    @Column(name = "money")
    BigDecimal money;

    @Column(name = "money_type")
    String moneyType;

}
