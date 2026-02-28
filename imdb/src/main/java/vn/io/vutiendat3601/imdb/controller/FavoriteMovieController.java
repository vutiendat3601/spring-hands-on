package vn.io.vutiendat3601.imdb.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.io.vutiendat3601.imdb.dto.FavoriteMovieRequest;
import vn.io.vutiendat3601.imdb.dto.FavoriteMovieResponse;
import vn.io.vutiendat3601.imdb.dto.GetUserFavoriteMovieResponse;
import vn.io.vutiendat3601.imdb.exception.MovieNotFoundException;
import vn.io.vutiendat3601.imdb.model.User;
import vn.io.vutiendat3601.imdb.service.UserService;

@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class FavoriteMovieController extends AbstractController {
  private final UserService userService;

  @GetMapping("get-user-favorite-movies")
  public ResponseEntity<GetUserFavoriteMovieResponse> get(HttpServletRequest request) {
    User user = getUserFromSession(request);
    if (Objects.isNull(user)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    log.info(String.format("get favorite movide for user {0}", user.getId()));
    GetUserFavoriteMovieResponse response = userService.getUserFavoriteMovies(user);
    return ResponseEntity.ok(response);
  }

  @PostMapping("favorite-movie")
  public ResponseEntity<FavoriteMovieResponse> favoriteMovie(
      @Valid @RequestBody FavoriteMovieRequest favoriteMovieRequest, HttpServletRequest request)
      throws MovieNotFoundException {
    final User user = getUserFromSession(request);
    if (Objects.isNull(user)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    log.info(
        String.format(
            "user {0} favorite movie {1}", user.getId(), favoriteMovieRequest.getMovieId()));
    userService.favoriteMovie(user, favoriteMovieRequest);
    return ResponseEntity.ok().build();
  }
}
