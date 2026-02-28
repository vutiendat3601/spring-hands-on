package vn.io.vutiendat3601.imdb.repository.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import vn.io.vutiendat3601.imdb.model.Movie;
import vn.io.vutiendat3601.imdb.model.User;
import vn.io.vutiendat3601.imdb.model.UserFavoriteMovie;

public class InMemory {
  static AtomicLong userIdGenerator = new AtomicLong();

  static Map<String, User> users = new ConcurrentHashMap<>();

  static Map<Long, Movie> movies = new ConcurrentHashMap<>();

  static Map<UserFavoriteMovie, Integer> userFavoriteMovies = new ConcurrentHashMap<>();

  static {
    movies.put(1L, new Movie(1L, "The Shawshank Redemption"));
    movies.put(2L, new Movie(2L, "The Godfather"));
    movies.put(3L, new Movie(3L, "The Dark Knight"));
    movies.put(4L, new Movie(4L, "Pulp Fiction"));
    movies.put(5L, new Movie(5L, "The Lord of the Rings: The Return of the King"));
  }
}
