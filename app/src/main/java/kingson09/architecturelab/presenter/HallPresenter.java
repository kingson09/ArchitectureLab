package kingson09.architecturelab.presenter;

import java.util.ArrayList;
import java.util.List;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.databinding.Observable;
import android.support.annotation.Nullable;
import android.util.ArrayMap;

import kingson09.architecturelab.BR;
import kingson09.architecturelab.bean.HallInfo;
import kingson09.architecturelab.bean.Period;
import kingson09.architecturelab.business.HallMovieFilter;
import kingson09.architecturelab.model.BannerItem;
import kingson09.architecturelab.model.Movie;
import kingson09.architecturelab.repository.HallInfoRepository;
import kingson09.architecturelab.repository.PeriodInfoRepository;
import kingson09.architecturelab.view.bindingAttrs.states.BannerState;
import kingson09.architecturelab.view.bindingAttrs.states.ListViewState;
import kingson09.architecturelab.viewmodel.HallViewModel;

import static android.arch.lifecycle.Lifecycle.State.DESTROYED;

public class HallPresenter extends Observable.OnPropertyChangedCallback implements GenericLifecycleObserver {
  private boolean bind;
  private HallViewModel hallViewModel;
  private HallMovieFilter filter = new HallMovieFilter();
  private Observer<HallInfo> hallInfoObserver = new Observer<HallInfo>() {
    @Override
    public void onChanged(@Nullable HallInfo hallInfo) {
      updateMovies(filter.filter(hallInfo.getMovies()));
      updateBanners(hallInfo.getBanner());
      hallViewModel.setLoading(false);
      hallViewModel.setRefreshing(false);
    }
  };
  private Observer<List<Period>> periodsInfoObserver = new Observer<List<Period>>() {
    @Override
    public void onChanged(@Nullable List<Period> periods) {
      ArrayMap<String, Period> map = new ArrayMap<>();
      for (Period period : periods) {
        map.put(period.id, period);
      }
      for (Movie movie : hallViewModel.getMovies()) {
        Period period = map.get(movie.id);
        if (period != null) {
          movie.setPeriod(period);
        }
      }
    }
  };

  public HallPresenter(HallViewModel cardList, LifecycleOwner owner) {
    this.bind = true;
    this.hallViewModel = cardList;
    this.hallViewModel.addOnPropertyChangedCallback(this);
    owner.getLifecycle().addObserver(this);
    HallInfoRepository.getRepo().getHallInfo().observe(owner, hallInfoObserver);
    PeriodInfoRepository.getRepo().getPeriodsInfo().observe(owner, periodsInfoObserver);
  }


  @Override
  public void onPropertyChanged(Observable sender, int propertyId) {

    switch (propertyId) {
      case BR.refreshing:
        if (hallViewModel.getRefreshing()) {
          refresh();
        }
        break;
      case BR.loading:
        if (hallViewModel.getLoading()) {
          loadMoreMovie();
        }
        break;
    }
  }

  private void updateMovies(ArrayList<Movie> moves) {
    this.hallViewModel.setMovies(moves);
    if (this.hallViewModel.getMovies().size() > 0) {
      this.hallViewModel.setMovieListState(ListViewState.LIST_STATE_NORMAL);
    } else {
      this.hallViewModel.setMovieListState(ListViewState.LIST_STATE_EMPTY);
    }
  }

  private void updateBanners(List<BannerItem> bannerItems) {
    this.hallViewModel.setBannerList(bannerItems);
    if (this.hallViewModel.getBannerList().size() >0) {
      this.hallViewModel.setBannerState(BannerState.BANNER_STATE_NORMAL);
    } else {
      this.hallViewModel.setBannerState(BannerState.BANNER_STATE_EMPTY);
    }
  }

  public void loadMoreMovie() {
    HallInfoRepository.getRepo().loadMoreHallInfo();
  }

  public void refresh() {
    HallInfoRepository.getRepo().loadHallInfo();
  }

  @Override
  public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
    if (source.getLifecycle().getCurrentState() == DESTROYED) {
      removeAllLongLifeHolder(source);
      return;
    }
  }

  public void removeAllLongLifeHolder(LifecycleOwner source) {
    this.bind = false;
  }


}
