package vn.io.vutiendat3601.imdb.repository;

import vn.io.vutiendat3601.imdb.model.Movie;

public interface MovieRepsitory {

  Movie addMovie(long id, String title);

  Movie findMovieByMovieId(long movieId);
}
