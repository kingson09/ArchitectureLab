package kingson09.architecturelab.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kingson09.architecturelab.R;
import kingson09.architecturelab.databinding.ActivityLoginBindingImpl;
import kingson09.architecturelab.viewmodel.LoginViewModel;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


  private View mProgressView;
  private View mLoginFormView;
  ActivityLoginBindingImpl activityLoginBinding;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityLoginBinding=DataBindingUtil.setContentView(this, R.layout.activity_login);
    LoginViewModel loginViewModel=new LoginViewModel();
    activityLoginBinding.setLoginVm(loginViewModel);
    loginViewModel.initPropertiesObserve();
    mLoginFormView = findViewById(R.id.login_form);
    mProgressView = findViewById(R.id.login_progress);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    activityLoginBinding.unbind();
    activityLoginBinding=null;
  }
  /**
   * Shows the progress UI and hides the login form.
   */
  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
  private void showProgress(final boolean show) {
    // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
    // for very easy animations. If available, use these APIs to fade-in
    // the progress spinner.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
      int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

      mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
      mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1)
          .setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
              mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
          });

      mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
      mProgressView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
          mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
      });
    } else {
      // The ViewPropertyAnimator APIs are not available, so simply show
      // and hide the relevant UI components.
      mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
      mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }
  }


}

