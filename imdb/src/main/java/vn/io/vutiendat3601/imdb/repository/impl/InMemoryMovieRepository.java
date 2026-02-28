package vn.io.vutiendat3601.imdb.repository.impl;

import static vn.io.vutiendat3601.imdb.repository.impl.InMemory.movies;

import org.springframework.stereotype.Repository;

import vn.io.vutiendat3601.imdb.model.Movie;
import vn.io.vutiendat3601.imdb.repository.MovieRepsitory;

@Repository
public class InMemoryMovieRepository implements MovieRepsitory {

  @Override
  public Movie addMovie(long id, String title) {
      Movie newMovie = Movie.builder().id(id).title(title).build();
      movies.put(id, newMovie);
      return newMovie;
  }

  @Override
  public Movie findMovieByMovieId(long movieId) {
    return movies.get(movieId);
  }
}
