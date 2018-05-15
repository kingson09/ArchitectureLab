package kingson09.architecturelab.activity;

import java.util.List;

import android.content.Context;
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

import com.youth.banner.loader.ImageLoader;

import kingson09.architecturelab.R;
import kingson09.architecturelab.databinding.ActivityPracticeBannerBinding;
import kingson09.architecturelab.model.BannerItem;
import kingson09.architecturelab.model.Movie;
import kingson09.architecturelab.view.BaseBindingAdapter;
import kingson09.architecturelab.view.BaseBindingBanner;
import kingson09.architecturelab.viewmodel.HallViewModel;
import kingson09.architecturelab.viewmodel.IHallBind;

/**
 * 广告轮播-Banner
 */
@SuppressWarnings("ALL")
public class HallActivity extends AppCompatActivity implements IHallBind {

  private BaseBindingAdapter<Movie> mAdapter;
  private HallViewModel hallViewModel;
  private RecyclerView recyclerView;
  private boolean start = true;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityPracticeBannerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_practice_banner);
    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    hallViewModel = new HallViewModel(this);
    binding.setHallViewModel(hallViewModel);
    hallViewModel.bind();
    hallViewModel.observer(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    System.out.println("HallActivity onResume");
  }
  @Override
  protected void onStop() {
    super.onStop();
    System.out.println("HallActivity onStop");
  }
  @Override
  protected void onDestroy() {
    super.onDestroy();
    start = false;
  }

  @Override
  public void bindBanner(int bindId, Object value) {
    ViewDataBinding headerBinding =
        DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.listitem_movie_header, recyclerView, false);
    BaseBindingBanner banner = (BaseBindingBanner) headerBinding.getRoot();
    banner.setImageLoader(new GlideImageLoader());
    headerBinding.setVariable(bindId, value);
    mAdapter.addHeaderView(banner);
  }

  @Override
  public void bindList(int bindId, Object value) {
    mAdapter = new BaseBindingAdapter((List) value, bindId);
    recyclerView.setAdapter(mAdapter);
    mAdapter.openLoadAnimation();

  }


  public static class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
      imageView.setImageResource(((BannerItem) path).pic);
    }
  }

}