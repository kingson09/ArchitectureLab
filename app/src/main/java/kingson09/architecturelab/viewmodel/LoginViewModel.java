package kingson09.architecturelab.viewmodel;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.view.View;

import kingson09.architecturelab.model.LoginBean;
import kingson09.architecturelab.business.Login;
import kingson09.architecturelab.BR;

public class LoginViewModel extends Observable.OnPropertyChangedCallback {
  private Login login = new Login();
  public LoginBean loginInfo = new LoginBean();


  public ObservableField<String> emailFormatErrorMsg = new ObservableField<>();
  public ObservableField<String> passwordFormatErrorMsg = new ObservableField<>();


  public ObservableField<String> getEmailFormatErrorMsg() {
    return emailFormatErrorMsg;
  }

  public void setEmailFormatErrorMsg(String emailFormatErrorMsg) {
    this.emailFormatErrorMsg.set(emailFormatErrorMsg);
  }


  public ObservableField<String> getPasswordFormatErrorMsg() {
    return passwordFormatErrorMsg;
  }

  public void setPasswordFormatErrorMsg(String passwordFormatErrorMsg) {
    this.passwordFormatErrorMsg.set(passwordFormatErrorMsg);
  }

  public View.OnClickListener loginButton = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      login.attemptLogin(LoginViewModel.this, loginInfo);
    }
  };

  public void initPropertiesObserve() {
    loginInfo.addOnPropertyChangedCallback(this);
  }

  @Override
  public void onPropertyChanged(Observable sender, int propertyId) {
    switch (propertyId) {
      case BR.email: {
        String validate = Login.validateEmail(loginInfo.getEmail());
        setEmailFormatErrorMsg(validate);
        break;
      }
      case BR.password: {
        String validate = Login.validatePassword(loginInfo.getPassword());
        setPasswordFormatErrorMsg(validate);
      }
    }
  }
}
