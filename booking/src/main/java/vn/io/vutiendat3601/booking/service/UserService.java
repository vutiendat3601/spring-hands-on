package vn.io.vutiendat3601.booking.service;

import org.springframework.stereotype.Service;
import vn.io.vutiendat3601.booking.entity.Booking;

@Service
public interface UserService {
  Booking optimisticBookRoom(Long userId, Long roomId);

  Booking perssimisticBookRom(Long userId, Long roomId);
}
