package kingson09.architecturelab.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import kingson09.architecturelab.BR;
public class LoginBean extends BaseObservable {
  private String email;
  private String password;

  @Bindable
  public String getEmail() {
    return email;
  }
  @Bindable
  public String getPassword() {
    return password;
  }

  public void setEmail(String email) {
    this.email=email;
    notifyPropertyChanged(BR.email);
  }

  public void setPassword(String password) {
    this.password=password;
    notifyPropertyChanged(BR.password);
  }


}

