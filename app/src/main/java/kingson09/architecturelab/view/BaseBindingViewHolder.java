package kingson09.architecturelab.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class BaseBindingViewHolder extends BaseViewHolder {
  private ViewDataBinding binding;

  public BaseBindingViewHolder(View itemView) {
    super(itemView);
    binding = DataBindingUtil.bind(itemView);
  }

  @Override
  protected BaseViewHolder setAdapter(BaseQuickAdapter adapter) {
    return super.setAdapter(adapter);
  }

  public ViewDataBinding getBinding() {
    return binding;
  }
}
