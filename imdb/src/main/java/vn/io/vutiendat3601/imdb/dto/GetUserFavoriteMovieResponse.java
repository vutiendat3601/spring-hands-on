package vn.io.vutiendat3601.imdb.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import vn.io.vutiendat3601.imdb.model.Movie;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetUserFavoriteMovieResponse {
  List<Movie> favoriteMovies;
}
