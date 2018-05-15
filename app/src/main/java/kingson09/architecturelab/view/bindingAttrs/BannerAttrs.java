package kingson09.architecturelab.view.bindingAttrs;

import java.util.List;

import android.databinding.BindingAdapter;

import kingson09.architecturelab.view.BaseBindingBanner;
import kingson09.architecturelab.view.bindingAttrs.states.BannerState;

public class BannerAttrs {
  @BindingAdapter("imgs")
  public static void bindImgs(BaseBindingBanner view, List imgs) {
    view.setImages(imgs);
  }

  @BindingAdapter("banner_state")
  public static void setBanner(BaseBindingBanner view, int bannerState) {
    view.render();
    switch (bannerState) {
      case BannerState.BANNER_STATE_NORMAL:
        break;
      case BannerState.BANNER_STATE_EMPTY:
        break;
    }

  }
}
