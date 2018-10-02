package com.example.quangtruong.demo.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.quangtruong.demo.MainActivity;
import com.example.quangtruong.demo.R;
import com.example.quangtruong.demo.Utils;
import com.example.quangtruong.demo.constant.Constant;
import com.example.quangtruong.demo.customview.TransitionHelper;
import com.example.quangtruong.demo.database.DatabaseHandler;
import com.example.quangtruong.demo.dialog.ProgressBarDialog;
import com.example.quangtruong.demo.object.UserObject;
import com.google.gson.Gson;

@SuppressLint("Registered")
public class CommonActivity extends AppCompatActivity {
    protected Context context = this;
    protected ProgressBarDialog progressDialog;
    protected TextView tvTitleToolbar;
    protected RelativeLayout rlTotalToolbar, rlToolbar, rlViewToolbar;
    protected LinearLayout llError;
    protected ImageView imgBack;
    protected DatabaseHandler databaseHandler;
    protected Gson gson = new Gson();

    private static final String TAG = CommonActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setStatusBarColor();
        progressDialog = new ProgressBarDialog(context);
        databaseHandler = new DatabaseHandler(context);
    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Utils.getColorPrimary(context));
        }
    }

    @Override
    public void onBackPressed() {
        hideKeyBroad();
        super.onBackPressed();
    }

    protected void installToolbar() {
        try {
            rlTotalToolbar = (RelativeLayout) findViewById(R.id.rl_total_toolbar);
            rlToolbar = (RelativeLayout) findViewById(R.id.rl_toolbar);
            rlViewToolbar = (RelativeLayout) findViewById(R.id.rl_view_toolbar);
            llError = (LinearLayout) findViewById(R.id.ll_error_toolbar);
            imgBack = (ImageView) findViewById(R.id.img_back_toolbar);
            tvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
            imgBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        } catch (Throwable e) {
            Log.e(TAG, "installToolbar: ", e);
        }
    }

    protected void logout() {
        databaseHandler.dropTableUser(null);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @SuppressLint("RestrictedApi")
    protected void switchActivityTrasition(View v, Context packageContext, Class<?> cls) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, false,
                new Pair<>(v, context.getString(R.string.logo_transition)));
        Intent i = new Intent(packageContext, cls);
        if (pairs != null) {
            ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
            startActivityForResult(i, Constant.ResultCode.SIGN_UP, transitionActivityOptions.toBundle());
        } else {
            startActivityForResult(i, Constant.ResultCode.SIGN_UP);
        }
    }

    protected void showError() {
        llError.setVisibility(View.VISIBLE);
        Animation showNetWorkError = AnimationUtils.loadAnimation(this, R.anim.slide_in_then_out_top);
        showNetWorkError.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llError.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        llError.startAnimation(showNetWorkError);
    }

    protected void hideProgress() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Throwable e) {
            Log.e(TAG, "Progress Hide: " + e);
        }
    }

    protected void showProgress() {
        try {
            if (progressDialog != null && !progressDialog.isShowing()) {
                progressDialog.show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Progress Show: " + e);
        }
    }

    protected void hideKeyBroad() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    protected void setViewBackground(View v) {
        if (v != null) {
            int color = Utils.getColorPrimary(context);
            v.setBackgroundColor(color);
        }
    }

    protected void switchToHomeActivity(UserObject userObject, Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        Bundle b = new Bundle();
        b.putParcelable(Constant.ParcelKey.USER_OBJECT, userObject);
        startActivity(intent);
    }
}
