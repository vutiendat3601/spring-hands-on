package vn.io.vutiendat3601.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.io.vutiendat3601.booking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {}
