package kingson09.architecturelab.business;

import java.util.ArrayList;
import java.util.List;

import kingson09.architecturelab.bean.MovieInfo;
import kingson09.architecturelab.model.Movie;

public class HallMovieFilter {
  public ArrayList<Movie> filter(List<MovieInfo> hallInfoMovies) {
    ArrayList<Movie> movies = new ArrayList<>();
    for (MovieInfo movie : hallInfoMovies) {
      if (!movie.filmName.contains("魂")) {
        movies.add(new Movie(movie));
      }
    }
    return movies;
  }
}
