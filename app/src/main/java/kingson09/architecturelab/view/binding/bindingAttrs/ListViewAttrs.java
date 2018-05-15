package kingson09.architecturelab.view.binding.bindingAttrs;

import android.databinding.BindingAdapter;

import kingson09.architecturelab.view.IRender;
import kingson09.architecturelab.view.binding.state.ListViewState;

public class ListViewAttrs {
  @BindingAdapter("list_state")
  public static void bindRecyclerViewData(android.support.v7.widget.RecyclerView view, int movieListState) {
    ((IRender) view.getAdapter()).render();
    switch (movieListState) {
      case ListViewState.LIST_STATE_NORMAL:
        break;
      case ListViewState.LIST_STATE_EMPTY:
        //TODO
        break;
      case ListViewState.LIST_STATE_NET_ERROR:
        //TODO
        break;
    }
  }
}
