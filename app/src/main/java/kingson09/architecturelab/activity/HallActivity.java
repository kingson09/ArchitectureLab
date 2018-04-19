package kingson09.architecturelab.activity;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import kingson09.architecturelab.R;
import kingson09.architecturelab.databinding.ActivityPracticeBannerBinding;
import kingson09.architecturelab.databinding.ListitemMovieHeaderBinding;
import kingson09.architecturelab.databinding.ListitemMovieItemBinding;
import kingson09.architecturelab.model.BannerItem;
import kingson09.architecturelab.model.Movie;
import kingson09.architecturelab.utils.BaseComponent;
import kingson09.architecturelab.view.BaseBindingAdapter;
import kingson09.architecturelab.view.bindingAttrs.states.BannerState;
import kingson09.architecturelab.view.bindingAttrs.states.ListViewState;
import kingson09.architecturelab.viewmodel.HallViewModel;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * 广告轮播-Banner
 */
@SuppressWarnings("ALL")
public class HallActivity extends AppCompatActivity {

  private BaseBindingAdapter<Movie> mAdapter;
  private HallViewModel hallViewModel;
  private Component component;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    hallViewModel = new HallViewModel();
    component = new Component(this, hallViewModel);
    ActivityPracticeBannerBinding binding =
        DataBindingUtil.setContentView(this, R.layout.activity_practice_banner, component);
    hallViewModel.observer(this);
    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

    final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    final RefreshLayout refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    binding.setHallViewModel(hallViewModel);
  }


  public static class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
      imageView.setImageResource(((BannerItem) path).pic);
    }
  }

  public static class Component extends BaseComponent {
    private HallViewModel hallViewModel;
    private Banner banner;
    private ListitemMovieHeaderBinding headerBinding;

    @BindingAdapter("banner_state")
    public void setBanner(RecyclerView view, int bannerState) {
      if (banner == null && bannerState == BannerState.BANNER_STATE_NORMAL) {
        headerBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.listitem_movie_header, view, false);
        banner = (Banner) ((ViewDataBinding) headerBinding).getRoot();
        banner.setImageLoader(new GlideImageLoader());
        banner.setOnBannerListener(new OnBannerListener() {
          @Override
          public void OnBannerClick(int position) {
            //HallInfoRepository.getRepo().loadHallInfo();
          }
        });
        headerBinding.setBannerItems(hallViewModel.getBannerList());
      }
      if (banner != null && banner.getParent() == null && view.getAdapter() != null) {
        ((BaseBindingAdapter) view.getAdapter()).addHeaderView(banner, 0);
      }
      switch (bannerState) {
        case BannerState.BANNER_STATE_NORMAL:
          headerBinding.setBannerItems(hallViewModel.getBannerList());
          break;
        case BannerState.BANNER_STATE_EMPTY:
          if (banner != null && view.getAdapter() != null) {
            ((BaseBindingAdapter) view.getAdapter()).removeHeaderView(banner);
            headerBinding.unbind();
            headerBinding = null;
            banner = null;
          }
          break;
      }

    }

    @BindingAdapter("list_state")
    public void bindRecyclerViewData(android.support.v7.widget.RecyclerView view, int movieListState) {
      if (view.getAdapter() == null) {
        BaseBindingAdapter mAdapter = new BaseBindingAdapter(R.layout.listitem_movie_item, hallViewModel.getMovies());
        mAdapter.setOnBindViewHolder(new BaseBindingAdapter.BindView<Movie>() {
          @Override
          public void onBindViewHolder(ViewDataBinding b, Movie item) {
            ((ListitemMovieItemBinding) b).setMovie(item);
          }
        });
        if (banner != null) {
          mAdapter.removeHeaderView(banner);
          mAdapter.addHeaderView(banner, 0);
        }
        view.setAdapter(mAdapter);
        mAdapter.openLoadAnimation();
      }
      ((BaseBindingAdapter) view.getAdapter()).replaceData(hallViewModel.getMovies());
      switch (movieListState) {
        case ListViewState.LIST_STATE_NORMAL:
          break;
        case ListViewState.LIST_STATE_EMPTY:
          //TODO
          break;
        case ListViewState.LIST_STATE_NET_ERROR:
          //TODO
          break;
      }
    }

    public Component(Context context, HallViewModel hallViewModel) {
      super(context);
      this.hallViewModel = hallViewModel;
    }

    @Override
    public Component getComponent() {
      return this;
    }
  }

}
