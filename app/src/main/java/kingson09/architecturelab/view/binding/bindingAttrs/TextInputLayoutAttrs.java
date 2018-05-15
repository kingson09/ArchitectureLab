package kingson09.architecturelab.view.binding.bindingAttrs;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;

public class TextInputLayoutAttrs {
  @BindingAdapter("tilError")
  public static void bindTilError(TextInputLayout view, String error) {
    view.setError(error);
  }
}
