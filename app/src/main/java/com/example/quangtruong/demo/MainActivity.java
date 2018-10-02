package com.example.quangtruong.demo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.quangtruong.demo.activity.CommonActivity;
import com.example.quangtruong.demo.activity.HomeActivity;
import com.example.quangtruong.demo.activity.SignInActivity;
import com.example.quangtruong.demo.customview.PlaceHolderInfoDefault;
import com.example.quangtruong.demo.dialog.ChooseThemeDialog;
import com.example.quangtruong.demo.object.UserObject;

public class MainActivity extends CommonActivity implements View.OnClickListener {
    private RelativeLayout rlLogin;
    private TextView tvSignUp, tvForgotPass, tvErrorUserName, tvErrorPassword;
    private Button btnLogin;
    private ImageView imgErrorUserName, imgErrorPassword, imgLogo;
    private CountDownTimer countDownTimer;
    private PlaceHolderInfoDefault edtUserName, edtPassword;
    private Animation animFadeOut;
    private String username, password;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        installView();
    }

    private void installView() {
        try {
            installToolbar();

            rlLogin = (RelativeLayout) findViewById(R.id.rl_login);

            tvSignUp = (TextView) findViewById(R.id.tv_sign_up);
            tvErrorUserName = (TextView) findViewById(R.id.tv_error_user_name);
            tvErrorPassword = (TextView) findViewById(R.id.tv_error_password);
            tvForgotPass = (TextView) findViewById(R.id.tv_forgot_password);

            imgErrorUserName = (ImageView) findViewById(R.id.img_error_user);
            imgErrorPassword = (ImageView) findViewById(R.id.img_error_pass);
            imgLogo = (ImageView) findViewById(R.id.img_logo);

            edtUserName = (PlaceHolderInfoDefault) findViewById(R.id.edt_username);
            edtPassword = (PlaceHolderInfoDefault) findViewById(R.id.edt_password);

            btnLogin = (Button) findViewById(R.id.btn_login);
            RelativeLayout rlTotalView = (RelativeLayout) findViewById(R.id.rl_total_view);

            animFadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out);

            edtUserName.setImageViewError(imgErrorUserName);
            edtPassword.setImageViewError(imgErrorPassword);
            edtUserName.setTextViewError(tvErrorUserName, getString(R.string.user_name));
            edtPassword.setTextViewError(tvErrorPassword, getString(R.string.password));

            setBackground();
            installSplashScreen();

            rlViewToolbar.setVisibility(View.GONE);
            btnLogin.setOnClickListener(this);
            tvSignUp.setOnClickListener(this);
            rlTotalView.setOnClickListener(this);
            tvForgotPass.setOnClickListener(this);
        } catch (Throwable e) {
            Log.e(TAG, "installView: ", e);
        }
    }

    private void setBackground() {
        int color = Utils.getColorPrimary(context);
        Drawable drawablePlaceholder = ContextCompat.getDrawable(context, Utils.getEditTextBackground(context));

        edtPassword.setBackground(drawablePlaceholder);
        edtUserName.setBackground(drawablePlaceholder);

        btnLogin.setBackground(Utils.getButtonBackground(context));

        tvForgotPass.setTextColor(color);
        tvSignUp.setTextColor(color);

    }

    private void installSplashScreen() {
        try {
            countDownTimer = new CountDownTimer(1000, 2000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    rlLogin.setVisibility(View.VISIBLE);
                    tvSignUp.setVisibility(View.VISIBLE);
                    edtUserName.setText("1");
                    edtPassword.setText("1");
                    btnLogin.performClick();
//                    UserObject userObject = databaseHandler.getUser();
////                    if (userObject != null && !Utils.isNullOrEmpty(userObject.getEmailAddress())
////                            && !Utils.isNullOrEmpty(userObject.getUserPassword())) {
////                        edtUserName.setText(userObject.getEmailAddress());
////                        edtPassword.setText(userObject.getUserPassword());
////                        btnLogin.performClick();
////                    }
                }
            };
            animFadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    countDownTimer.start();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            imgLogo.startAnimation(animFadeOut);
        } catch (Throwable e) {
            Log.e(TAG, "installSplashScreen: ", e);
        }
    }

    private boolean checkInfo() {
        username = edtUserName.getText().toString().trim();
        password = edtPassword.getText().toString().trim();
        boolean success = true;
        if (Utils.isNullOrEmpty(username)) {
            success = false;
            Utils.setError(context, edtUserName, tvErrorUserName, imgErrorUserName, Utils.format(getString(R.string.incorrect_format), getString(R.string.user_name)));
            edtUserName.setError(true);
        }

        if (Utils.isNullOrEmpty(password)) {
            success = false;
            Utils.setError(context, edtPassword, tvErrorPassword, imgErrorPassword, Utils.format(getString(R.string.incorrect_format), getString(R.string.password)));
            edtPassword.setError(true);
        }
        return success;
    }

    private void loginMethod() {
        new ChooseThemeDialog(context, new ChooseThemeDialog.OnChooseThemeListener() {
            @Override
            public void onChooseTheme(String theme) {
                Utils.saveTheme(context, theme);
                switchToHomeActivity(new UserObject(), context, HomeActivity.class);
            }
        }).show();

//        Session session = new SessionImpl(Constant.Url.HOST, new BasicAuthentication(username, password));
//        showProgress();
//        SignIn.signIn(session, new JSONObjectCallback() {
//            @Override
//            public void onSuccess(JSONObject userJSONObject) {
//                Log.d(TAG, "onSuccess: " + userJSONObject);
//                try {
//                    final UserObject userObject = gson.fromJson(userJSONObject.toString(), UserObject.class);
//                    if (userObject != null) {
//                        new ChooseThemeDialog(context, new ChooseThemeDialog.OnChooseThemeListener() {
//                            @Override
//                            public void onChooseTheme(String theme) {
//                                userObject.setUserTheme(theme);
//                                userObject.setUserPassword(password);
//                                switchToHomeActivity(userObject, context, HomeActivity.class);
//                                databaseHandler.addUser(userObject, password);
//                            }
//                        }).show();
//                    }
//                } catch (Throwable e) {
//                    Log.e(TAG, "onSuccess: ", e);
//                    showError();
//                }
//                hideProgress();
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                Log.e(TAG, "onFailure: ", e);
//                hideProgress();
//                showError();
//            }
//
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (checkInfo()) {
                    loginMethod();
                }
                break;
            case R.id.tv_sign_up:
                switchActivityTrasition(imgLogo, MainActivity.this, SignInActivity.class);
                break;
            default:
                hideKeyBroad();
                break;
        }
    }
}
