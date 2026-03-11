package vn.io.vutiendat3601.booking;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import vn.io.vutiendat3601.booking.entity.Booking;
import vn.io.vutiendat3601.booking.entity.Room;
// import vn.io.vutiendat3601.booking.repository.BookingRepository;
import vn.io.vutiendat3601.booking.repository.RoomRepository;
import vn.io.vutiendat3601.booking.service.UserService;

@Slf4j
@SpringBootApplication
public class BookingApplication {
  private static int TRANSACTIONS_PER_MACHINE = 5;

  public static class BookingRunnable implements Runnable {
    private long userId;
    private long roomId;
    private UserService service;

    public BookingRunnable(UserService service, long userId, long roomId) {
      this.userId = userId;
      this.service = service;
      this.roomId = roomId;
    }

    public void run() {
      // change here to switch between optimistic and pessimistic lock
      Booking booking = service.optimisticBookRoom(userId, roomId);
      // Booking booking = service.perssimisticBookRom(userId, roomId);
      log.info("userId={}, booking id={}", userId, booking.getId());
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ConfigurableApplicationContext context = SpringApplication.run(BookingApplication.class, args);

    UserService service = context.getBean(UserService.class);
    final RoomRepository roomRepository = context.getBean(RoomRepository.class);
    // final BookingRepository bookingRepository = context.getBean(BookingRepository.class);

    Room room1 = Room.builder().room("Room1").available(true).version(1).build();
    Room createdRoom = roomRepository.save(room1);
    room1.setRoom("ahihi");
    createdRoom = roomRepository.save(room1);

    // createdRoom = roomRepository.s
    // roomRepository.save();
    // log.info("created room={}", createdRoom);

    // service.optimisticBookRoom(1, createdRoom.getId());
    final List<Thread> allThreads = new ArrayList<>();
    for (long userId = 1; userId <= TRANSACTIONS_PER_MACHINE; userId++) {
      final Thread t = new Thread(new BookingRunnable(service, userId, createdRoom.getId()));
      log.info("start thread userId={}", userId);
      t.start();
      allThreads.add(t);
    }
    for (Thread t : allThreads) {
      t.join();
    }
    // room1.setAvailable(true);
    // roomRepository.save(room1);
  }
}
