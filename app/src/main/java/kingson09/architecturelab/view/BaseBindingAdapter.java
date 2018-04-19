package kingson09.architecturelab.view;

import java.util.List;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class BaseBindingAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> implements IRender {
  private BindView bindView;
  private List<T> bindingData;

  public BaseBindingAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
    super(layoutResId);
    this.bindingData = data;
    render();
  }

  public void render() {
    if (this.bindingData != null) {
      replaceData(bindingData);
    }
  }

  public BaseBindingAdapter(@Nullable List<T> data) {
    this(0, data);
  }

  public BaseBindingAdapter(@LayoutRes int layoutResId) {
    this(layoutResId, null);
  }

  @Override
  public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    if (viewType == EMPTY_VIEW || viewType == HEADER_VIEW || viewType == FOOTER_VIEW || viewType == LOADING_VIEW) {
      return super.onCreateViewHolder(viewGroup, viewType);
    }
    ViewDataBinding binding =
        DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), mLayoutResId, viewGroup, false);
    BaseBindingViewHolder baseViewHolder = new BaseBindingViewHolder(binding.getRoot());
    baseViewHolder.setAdapter(this);
    bindViewClickListener(baseViewHolder);
    return baseViewHolder;
  }

  @Override
  protected void convert(BaseViewHolder viewHolder, T item) {
    bindView.onBindViewHolder(((BaseBindingViewHolder) viewHolder).getBinding(), item);
    ((BaseBindingViewHolder) viewHolder).getBinding().executePendingBindings();
  }

  public interface BindView<T> {
    void onBindViewHolder(ViewDataBinding b, T item);
  }

  public void setOnBindViewHolder(BindView bindView) {
    this.bindView = bindView;
  }

  private void bindViewClickListener(final BaseViewHolder baseViewHolder) {
    if (baseViewHolder == null) {
      return;
    }
    final View view = baseViewHolder.itemView;
    if (view == null) {
      return;
    }
    if (getOnItemClickListener() != null) {
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          setOnItemClick(v, baseViewHolder.getLayoutPosition() - getHeaderLayoutCount());
        }
      });
    }
    if (getOnItemLongClickListener() != null) {
      view.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          return setOnItemLongClick(v, baseViewHolder.getLayoutPosition() - getHeaderLayoutCount());
        }
      });
    }
  }
}
