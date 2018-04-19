package kingson09.architecturelab.view.bindingAttrs;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageViewAttrs {
  @BindingAdapter("img")
  public static void bindIVImg(ImageView view, String pic) {
    Glide.with(view.getContext()).load(pic).into(view);
  }
}
