package kingson09.architecturelab.business;

import android.os.AsyncTask;

import kingson09.architecturelab.model.LoginBean;
import kingson09.architecturelab.viewmodel.LoginViewModel;

public class Login {

  private static final String[] DUMMY_CREDENTIALS = new String[] { "foo@example.com:hello", "bar@example.com:world" };

  private UserLoginTask mAuthTask = null;

  /**
   * @param input 用户输入
   * @return 校验结果，空表示符合格式，非空为错误原因
   */
  public static String validateEmail(String input) {
    if (input.contains("@")) {
      return "";
    } else {
      return "please input correct email!";
    }

  }

  /**
   * @param input 用户输入
   * @return 校验结果，空表示符合格式，非空为错误原因
   */
  public static String validatePassword(String input) {
    if (input != null && input.length() > 4) {
      return "";
    } else {
      return "password len must above 4!";
    }
  }

  /**
   * Attempts to sign in or register the account specified by the login form.
   * If there are form errors (invalid email, missing fields, etc.), the
   * errors are presented and no actual login attempt is made.
   */
  public void attemptLogin(LoginViewModel loginViewModel,LoginBean loginInfo) {

    if (mAuthTask != null) {
      return;
    }

    mAuthTask = new UserLoginTask(loginInfo.getEmail(), loginInfo.getPassword());
    mAuthTask.execute((Void) null);

  }

  /**
   * Represents an asynchronous login/registration task used to authenticate
   * the user.
   */
  public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

    private final String mEmail;
    private final String mPassword;

    UserLoginTask(String email, String password) {
      mEmail = email;
      mPassword = password;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
      // TODO: attempt authentication against a network service.

      try {
        // Simulate network access.
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        return false;
      }

      for (String credential : DUMMY_CREDENTIALS) {
        String[] pieces = credential.split(":");
        if (pieces[0].equals(mEmail)) {
          // Account exists, return true if the password matches.
          return pieces[1].equals(mPassword);
        }
      }

      // TODO: register the new account here.
      return true;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
      mAuthTask = null;
    }

    @Override
    protected void onCancelled() {
      mAuthTask = null;
    }
  }
}
