package kingson09.architecturelab.view;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;

import com.youth.banner.Banner;

public class BaseBindingBanner extends Banner implements IRender {
  private List<?> bindingData;

  public BaseBindingBanner(Context context) {
    this(context, null);
  }

  public BaseBindingBanner(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public BaseBindingBanner(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public Banner setImages(List<?> imageUrls) {
    this.bindingData = imageUrls;
    render();
    return this;
  }

  @Override
  public void render() {
    if (this.bindingData != null) {
      super.update(this.bindingData);
    }
  }
}
