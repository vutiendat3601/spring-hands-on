package vn.io.vutiendat3601.booking.exception;

public class RoomUpdateConflictException extends RuntimeException {
  public RoomUpdateConflictException(String message) {
    super(message);
  }
}
