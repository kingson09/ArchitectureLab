package kingson09.architecturelab.utils;

import android.content.Context;

public abstract class BaseComponent implements android.databinding.DataBindingComponent {
  protected Context context;

  public BaseComponent(Context context) {
    this.context = context;
  }

  public void setContext(Context context) {
    this.context = context;
  }

}
