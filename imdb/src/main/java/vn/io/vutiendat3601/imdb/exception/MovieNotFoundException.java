package vn.io.vutiendat3601.imdb.exception;

public class MovieNotFoundException extends RuntimeException {
  public MovieNotFoundException(String message) {
    super(message);
  }
}
