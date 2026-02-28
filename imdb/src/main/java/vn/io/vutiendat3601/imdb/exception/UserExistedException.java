package vn.io.vutiendat3601.imdb.exception;

public class UserExistedException extends RuntimeException {
  public UserExistedException(String message) {
    super(message);
  }
}
