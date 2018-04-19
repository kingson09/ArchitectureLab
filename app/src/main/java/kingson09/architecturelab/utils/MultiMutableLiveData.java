package kingson09.architecturelab.utils;

import java.util.ArrayList;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

public class MultiMutableLiveData<T> extends MutableLiveData<T> {
  private ArrayList<Observer<T>> mObservers = new ArrayList<>();

  public void observe(@NonNull Observer<T> observer) {
    mObservers.add(observer);
  }

  public void removeObserver(@NonNull final Observer<T> observer) {
    mObservers.remove(observer);
  }

  @Override
  public void setValue(T value) {
    super.setValue(value);
    notifyObservers();
  }

  public void notifyObservers() {
    for (Observer observer : mObservers) {
      observer.onChanged(getValue());
    }
  }
}
