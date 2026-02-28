package vn.io.vutiendat3601.imdb.repository.impl;

import static vn.io.vutiendat3601.imdb.repository.impl.InMemory.userFavoriteMovies;
import static vn.io.vutiendat3601.imdb.repository.impl.InMemory.userIdGenerator;
import static vn.io.vutiendat3601.imdb.repository.impl.InMemory.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Repository;
import vn.io.vutiendat3601.imdb.model.User;
import vn.io.vutiendat3601.imdb.model.UserFavoriteMovie;
import vn.io.vutiendat3601.imdb.repository.UserRepository;

@Repository
public class InMemoryUserRepository implements UserRepository {

  @Override
  public User createNewUser(String email, String password) {
    final long userId = userIdGenerator.incrementAndGet();
    final User newUser = new User(userId, email, password);

    // return null if email already exists
    final User previousUser = users.putIfAbsent(email, newUser);
    if (Objects.isNull(previousUser)) {
      return newUser;
    }
    return null;
  }

  @Override
  public User findUserByUserEmail(String email) {
    return users.get(email);
  }

  @Override
  public void saveFavoriteMovie(long userId, long movieId) {
    userFavoriteMovies.put(UserFavoriteMovie.builder().userId(userId).movieId(movieId).build(), 1);
  }

  @Override
  public List<Long> getUserFavoritesMovieIds(long userId) {
    final List<Long> movieIds = new ArrayList<>();
    for (UserFavoriteMovie userFavoriteMovie : userFavoriteMovies.keySet()) {
      if (userFavoriteMovie.getUserId() == userId) {
        movieIds.add(userFavoriteMovie.getMovieId());
      }
    }
    return movieIds;
  }
}
