package vn.io.vutiendat3601.imdb.service.impl;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.io.vutiendat3601.imdb.dto.FavoriteMovieRequest;
import vn.io.vutiendat3601.imdb.dto.GetUserFavoriteMovieResponse;
import vn.io.vutiendat3601.imdb.dto.LoginRequest;
import vn.io.vutiendat3601.imdb.dto.RegistrationRequest;
import vn.io.vutiendat3601.imdb.dto.RegistrationResponse;
import vn.io.vutiendat3601.imdb.exception.MovieNotFoundException;
import vn.io.vutiendat3601.imdb.model.Movie;
import vn.io.vutiendat3601.imdb.model.User;
import vn.io.vutiendat3601.imdb.repository.MovieRepsitory;
import vn.io.vutiendat3601.imdb.repository.UserRepository;
import vn.io.vutiendat3601.imdb.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final MovieRepsitory movieRepsitory;
  private final PasswordEncoder passwordEncoder;

  private String hashPassword(String password) {
    return passwordEncoder.encode(password);
  }

  @Override
  public RegistrationResponse createUser(RegistrationRequest registrationRequest) {
    final String hashedPassword = hashPassword(registrationRequest.getPassword());
    log.info("hashedPassword: " + hashedPassword);
    User createdUser = userRepository.createNewUser(registrationRequest.getEmail(), hashedPassword);
    if (Objects.isNull(createdUser)) {
      return null;
    }
    return new RegistrationResponse(createdUser.getId());
  }

  @Override
  public User findUser(LoginRequest loginRequest) {
    final String hashedPassword = hashPassword(loginRequest.getPassword());
    log.info("hashedPassword: " + hashedPassword);
    User user = userRepository.findUserByUserEmail(loginRequest.getEmail());
    if (!Objects.isNull(user)
        && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
      return user;
    }
    return null;
  }

  @Override
  public void favoriteMovie(User user, FavoriteMovieRequest favoriteMovieRequest)
      throws MovieNotFoundException {
    final Movie movie = movieRepsitory.findMovieByMovieId(favoriteMovieRequest.getMovieId());
    if (Objects.isNull(movie)) {
      throw new MovieNotFoundException("Movie not found: id=" + favoriteMovieRequest.getMovieId());
    }
    userRepository.saveFavoriteMovie(user.getId(), favoriteMovieRequest.getMovieId());
  }

  @Override
  public GetUserFavoriteMovieResponse getUserFavoriteMovies(User user) {
    List<Long> movieIds = userRepository.getUserFavoritesMovieIds(user.getId());
    List<Movie> favoriteMovies =
        movieIds.stream().map(movieId -> movieRepsitory.findMovieByMovieId(movieId)).toList();
    return GetUserFavoriteMovieResponse.builder().favoriteMovies(favoriteMovies).build();
  }
}
