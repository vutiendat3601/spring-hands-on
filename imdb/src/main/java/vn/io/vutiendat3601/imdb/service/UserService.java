package vn.io.vutiendat3601.imdb.service;

import vn.io.vutiendat3601.imdb.dto.FavoriteMovieRequest;
import vn.io.vutiendat3601.imdb.dto.GetUserFavoriteMovieResponse;
import vn.io.vutiendat3601.imdb.dto.LoginRequest;
import vn.io.vutiendat3601.imdb.dto.RegistrationRequest;
import vn.io.vutiendat3601.imdb.dto.RegistrationResponse;
import vn.io.vutiendat3601.imdb.exception.MovieNotFoundException;
import vn.io.vutiendat3601.imdb.model.User;

public interface UserService {
  RegistrationResponse createUser(RegistrationRequest registrationRequest);

  User findUser(LoginRequest loginRequest);

  void favoriteMovie(User user, FavoriteMovieRequest favoriteMovieRequest)
      throws MovieNotFoundException;

  GetUserFavoriteMovieResponse getUserFavoriteMovies(User user);
}
