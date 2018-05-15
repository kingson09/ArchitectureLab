package kingson09.architecturelab.view.binding.bindingAttrs;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class SmartRefreshLayoutAttrs {
  @BindingAdapter(value = { "loading_more" }, requireAll = false)
  public static void bindRefreshLoadMore(SmartRefreshLayout view, Boolean loading) {
    if (!loading) {
      view.finishLoadMore();
    }
  }

  @BindingAdapter(value = { "refreshing" }, requireAll = false)
  public static void bindRefreshing(SmartRefreshLayout view, Boolean refreshing) {
    if (!refreshing) {
      view.finishRefresh();
    }
  }

  @InverseBindingAdapter(attribute = "refreshing", event = "refreshingStateChanged")
  public static boolean getRefreshing(SmartRefreshLayout view) {
    return true;
  }

  @InverseBindingAdapter(attribute = "loading_more", event = "loadingMoreStateChanged")
  public static boolean getLoadingMore(SmartRefreshLayout view) {
    return true;
  }

  @BindingAdapter(value = { "refreshingStateChanged" }, requireAll = false)
  public static void onRefreshingStateChanged(SmartRefreshLayout view, final InverseBindingListener bindingListener) {
    view.setOnRefreshListener(new OnRefreshListener() {
      @Override
      public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        bindingListener.onChange();
      }
    });
  }

  @BindingAdapter(value = { "loadingMoreStateChanged" }, requireAll = false)
  public static void onLoadingMoreStateChanged(SmartRefreshLayout view, final InverseBindingListener bindingListener) {
    view.setOnLoadMoreListener(new OnLoadMoreListener() {
      @Override
      public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        bindingListener.onChange();
      }
    });
  }
}
