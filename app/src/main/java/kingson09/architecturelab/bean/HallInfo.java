package kingson09.architecturelab.bean;

import java.util.List;

import kingson09.architecturelab.model.BannerItem;

public class HallInfo {
  public List<MovieInfo> getMovies() {
    return movies;
  }

  public void setMovies(List<MovieInfo> movies) {
    this.movies = movies;
  }

  public List<BannerItem> getBanner() {
    return banner;
  }

  public void setBanner(List<BannerItem> banner) {
    this.banner = banner;
  }

  private List<MovieInfo> movies;
  private List<BannerItem> banner;
}
