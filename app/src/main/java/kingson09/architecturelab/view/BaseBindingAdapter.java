package kingson09.architecturelab.view;

import java.util.List;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import kingson09.architecturelab.view.binding.IModel;

public class BaseBindingAdapter<T extends IModel> extends BaseQuickAdapter<T, BaseViewHolder> implements IRender {
  private List<T> bindingData;
  private int variableId;

  public BaseBindingAdapter(@Nullable List<T> data, @NonNull int variableId) {
    super(null);
    this.bindingData = data;
    this.variableId = variableId;
    render();
    setMultiTypeDelegate(new MultiTypeDelegate<T>() {
      @Override
      protected int getItemType(T t) {
        return t.layoutId();
      }
    });
  }

  public void render() {
    if (this.bindingData != null) {
      replaceData(bindingData);
    }
  }

  @Override
  public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    if (viewType == EMPTY_VIEW || viewType == HEADER_VIEW || viewType == FOOTER_VIEW || viewType == LOADING_VIEW) {
      return super.onCreateViewHolder(viewGroup, viewType);
    }
    ViewDataBinding binding =
        DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), viewType, viewGroup, false);
    BaseBindingViewHolder baseViewHolder = new BaseBindingViewHolder(binding.getRoot());
    baseViewHolder.setAdapter(this);
    bindViewClickListener(baseViewHolder);
    return baseViewHolder;
  }

  @Override
  protected void convert(BaseViewHolder viewHolder, T item) {
    ((BaseBindingViewHolder) viewHolder).getBinding().setVariable(variableId, item);
    ((BaseBindingViewHolder) viewHolder).getBinding().executePendingBindings();
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
