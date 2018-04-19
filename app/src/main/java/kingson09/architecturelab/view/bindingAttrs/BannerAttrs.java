package kingson09.architecturelab.view.bindingAttrs;

import java.util.List;

import android.databinding.BindingAdapter;

import com.youth.banner.Banner;

public class BannerAttrs {
  @BindingAdapter("imgs")
  public static void bindImgs(Banner view, List imgs) {
    view.update(imgs);
  }

}
