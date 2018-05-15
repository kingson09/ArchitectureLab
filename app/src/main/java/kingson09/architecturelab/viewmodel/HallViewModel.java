package kingson09.architecturelab.viewmodel;

import java.util.ArrayList;
import java.util.List;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import kingson09.architecturelab.BR;
import kingson09.architecturelab.model.BannerItem;
import kingson09.architecturelab.model.Movie;
import kingson09.architecturelab.presenter.HallPresenter;
import kingson09.architecturelab.view.bindingAttrs.states.BannerState;
import kingson09.architecturelab.view.bindingAttrs.states.ListViewState;

/*
ing和state后缀的属性都类似于reactJs的state，代表可变状态,其他属性类似于props
 */
public class HallViewModel extends BaseObservable {
  private boolean loading;
  private boolean refreshing;
  private int movieListState = ListViewState.LIST_STATE_EMPTY;
  private int bannerState = BannerState.BANNER_STATE_EMPTY;
  private ArrayList<Movie> movies = new ArrayList();
  private ArrayList<BannerItem> bannerList = new ArrayList();

  @Bindable
  public boolean getLoading() {
    return loading;
  }

  public void setLoading(boolean loading) {
    if (this.loading != loading) {
      this.loading = loading;
      notifyPropertyChanged(BR.loading);
    }
  }

  @Bindable
  public boolean getRefreshing() {
    return refreshing;
  }

  public void setRefreshing(boolean refreshing) {
    if (this.refreshing != refreshing) {
      this.refreshing = refreshing;
      notifyPropertyChanged(BR.refreshing);
    }
  }

  @Bindable
  public int getMovieListState() {
    return movieListState;
  }

  public void setMovieListState(int movieListState) {
    this.movieListState = movieListState;
    notifyPropertyChanged(BR.movieListState);
  }

  @Bindable
  public int getBannerState() {
    return bannerState;
  }

  public void setBannerState(int bannerState) {
    this.bannerState = bannerState;
    notifyPropertyChanged(BR.bannerState);
  }

  public ArrayList<Movie> getMovies() {
    return movies;
  }

  public void setMovies(ArrayList<Movie> movies) {
    this.movies.clear();
    this.movies.addAll(movies);
  }

  public ArrayList<BannerItem> getBannerList() {
    return bannerList;
  }

  public void setBannerList(List<BannerItem> bannerList) {
    this.bannerList.clear();
    this.bannerList.addAll(bannerList);
  }

  public void observer(LifecycleOwner owner) {
    new HallPresenter(this, owner);
  }

  public void clearMovies() {
    setMovies(new ArrayList<Movie>());
  }


}
