package kingson09.architecturelab.activity;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import kingson09.architecturelab.view.BaseBindingBanner;
import kingson09.architecturelab.view.bindingAttrs.states.BannerState;
import kingson09.architecturelab.view.bindingAttrs.states.ListViewState;
import kingson09.architecturelab.viewmodel.HallViewModel;

/**
 * 广告轮播-Banner
 */
@SuppressWarnings("ALL")
public class HallActivity extends AppCompatActivity {

  private BaseBindingAdapter<Movie> mAdapter;
  private HallViewModel hallViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    hallViewModel = new HallViewModel();
    ActivityPracticeBannerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_practice_banner);
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
    BaseBindingAdapter mAdapter = new BaseBindingAdapter(R.layout.listitem_movie_item, hallViewModel.getMovies());
    mAdapter.setOnBindViewHolder(new BaseBindingAdapter.BindView<Movie>() {
      @Override
      public void onBindViewHolder(ViewDataBinding b, Movie item) {
        ((ListitemMovieItemBinding) b).setMovie(item);
      }
    });
    ListitemMovieHeaderBinding headerBinding =
        DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.listitem_movie_header, recyclerView, false);
    BaseBindingBanner banner = (BaseBindingBanner) ((ViewDataBinding) headerBinding).getRoot();
    banner.setImageLoader(new GlideImageLoader());
    banner.setOnBannerListener(new OnBannerListener() {
      @Override
      public void OnBannerClick(int position) {
        //HallInfoRepository.getRepo().loadHallInfo();
      }
    });
    headerBinding.setHallViewModel(hallViewModel);
    mAdapter.addHeaderView(banner);
    recyclerView.setAdapter(mAdapter);
    mAdapter.openLoadAnimation();
    binding.setHallViewModel(hallViewModel);
  }


  public static class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
      imageView.setImageResource(((BannerItem) path).pic);
    }
  }

}
