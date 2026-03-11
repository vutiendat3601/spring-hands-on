package vn.io.vutiendat3601.booking.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.io.vutiendat3601.booking.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
  // use for optimistic lock
  Room findOneByIdAndAvailable(Long id, boolean available);

  // use for pessimistic lock, can uncomment to test
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  Room findByIdAndAvailable(Long id, boolean available);

  @Modifying
  @Query(
      """
        UPDATE room SET available = false, version = version + 1 WHERE id = :id AND version = :version
      """)
  int updateRoomAsUnavailable(@Param(value = "id") Long id, @Param(value = "version") int version);

  @Modifying
  @Query("UPDATE room SET available = false WHERE id = :id AND available = true")
  int updateRoomAsUnavailableWhenPessimisticLocked(@Param(value = "id") Long id);
}
