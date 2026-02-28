package vn.io.vutiendat3601.imdb.repository;

import java.util.List;

import vn.io.vutiendat3601.imdb.model.User;

public interface UserRepository {
  User createNewUser(String email, String password);

  User findUserByUserEmail(String email);

  void saveFavoriteMovie(long userId, long movieId);

  List<Long> getUserFavoritesMovieIds(long userId);
}
