package com.example.quangtruong.demo.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quangtruong.demo.R;
import com.example.quangtruong.demo.Utils;
import com.example.quangtruong.demo.constant.Constant;
import com.example.quangtruong.demo.constant.ErrorCode;
import com.example.quangtruong.demo.customview.PlaceHolderInfoDefault;
import com.example.quangtruong.demo.object.UserObject;
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.callback.typed.JSONObjectCallback;
import com.liferay.mobile.android.callservice.User;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;

import org.json.JSONObject;

public class SignInActivity extends CommonActivity implements View.OnClickListener {
    private static final String TAG = SignInActivity.class.getSimpleName();
    private ImageView imgErrorFname, imgErrorLname, imgErrorSname, imgErrorEmail, imgErrorPass;
    private TextView tvErrorFname, tvErrorLname, tvErrorSname, tvErrorEmail, tvErrorPass;
    private PlaceHolderInfoDefault edtFname, edtLname, edtSname, edtEmail, edtPass;
    private String fName, lName, sName, email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_sign_up);
        installView();
    }

    private void installView() {
        try {
            installToolbar();
            imgErrorFname = (ImageView) findViewById(R.id.img_error_fname);
            imgErrorLname = (ImageView) findViewById(R.id.img_error_lname);
            imgErrorSname = (ImageView) findViewById(R.id.img_error_sname);
            imgErrorEmail = (ImageView) findViewById(R.id.img_error_email);
            imgErrorPass = (ImageView) findViewById(R.id.img_error_pass);

            tvErrorFname = (TextView) findViewById(R.id.tv_error_fname);
            tvErrorLname = (TextView) findViewById(R.id.tv_error_lname);
            tvErrorSname = (TextView) findViewById(R.id.tv_error_sname);
            tvErrorEmail = (TextView) findViewById(R.id.tv_error_email);
            tvErrorPass = (TextView) findViewById(R.id.tv_error_pass);

            edtFname = (PlaceHolderInfoDefault) findViewById(R.id.edt_fname);
            edtLname = (PlaceHolderInfoDefault) findViewById(R.id.edt_lname);
            edtSname = (PlaceHolderInfoDefault) findViewById(R.id.edt_sname);
            edtEmail = (PlaceHolderInfoDefault) findViewById(R.id.edt_email);
            edtPass = (PlaceHolderInfoDefault) findViewById(R.id.edt_password);
            Button btnSignUp = (Button) findViewById(R.id.btn_sign_up);

            edtFname.setImageViewError(imgErrorFname);
            edtLname.setImageViewError(imgErrorLname);
            edtSname.setImageViewError(imgErrorSname);
            edtEmail.setImageViewError(imgErrorEmail);
            edtPass.setImageViewError(imgErrorPass);


            edtFname.setTextViewError(tvErrorFname, getString(R.string.first_name));
            edtLname.setTextViewError(tvErrorLname, getString(R.string.last_name));
            edtSname.setTextViewError(tvErrorSname, getString(R.string.screen_name));
            edtEmail.setTextViewError(tvErrorEmail, getString(R.string.email));
            edtPass.setTextViewError(tvErrorPass, getString(R.string.password));

            btnSignUp.setOnClickListener(this);

            int color = Utils.getColorPrimary(context);
            Drawable edtBackground = ContextCompat.getDrawable(context, Utils.getEditTextBackground(context));
            edtFname.setBackgroundResource(Utils.getEditTextBackground(context));
            edtLname.setBackgroundResource(Utils.getEditTextBackground(context));
            edtSname.setBackground(edtBackground);
            edtEmail.setBackground(edtBackground);
            edtPass.setBackground(edtBackground);

            imgBack.setColorFilter(color);
            btnSignUp.setBackground(Utils.getButtonBackground(context));

        } catch (Throwable e) {
            Log.e(TAG, "installView: ", e);
        }
    }


    private boolean checkInfo() {
        fName = edtFname.getText().toString().trim();
        lName = edtLname.getText().toString().trim();
        sName = edtSname.getText().toString().trim();
        email = edtEmail.getText().toString().trim();
        pass = edtPass.getText().toString().trim();

        boolean success = true;
        if (Utils.isNullOrEmpty(fName)) {
            success = false;
            Utils.setError(context, edtFname, tvErrorFname, imgErrorFname, Utils.format(getString(R.string.incorrect_format), getString(R.string.first_name)));
            edtFname.setError(true);
        }

        if (Utils.isNullOrEmpty(lName)) {
            success = false;
            Utils.setError(context, edtLname, tvErrorLname, imgErrorLname, Utils.format(getString(R.string.incorrect_format), getString(R.string.last_name)));
            edtLname.setError(true);
        }

        if (Utils.isNullOrEmpty(sName)) {
            success = false;
            Utils.setError(context, edtSname, tvErrorSname, imgErrorSname, Utils.format(getString(R.string.incorrect_format), getString(R.string.screen_name)));
            edtSname.setError(true);
        }

        if (Utils.isNullOrEmpty(email)) {
            success = false;
            Utils.setError(context, edtEmail, tvErrorEmail, imgErrorEmail, Utils.format(getString(R.string.incorrect_format), getString(R.string.email)));
            edtEmail.setError(true);
        }

        if (Utils.isNullOrEmpty(pass)) {
            success = false;
            Utils.setError(context, edtPass, tvErrorPass, imgErrorPass, Utils.format(getString(R.string.incorrect_format), getString(R.string.password)));
            edtPass.setError(true);
        }
        return success;
    }

    private void signUpMethod() {
        Session session = new SessionImpl(Constant.Url.HOST, new BasicAuthentication(Constant.UserTest.USERNAME, Constant.UserTest.PASSWORD));
        showProgress();
        User.addUser(session, Long.parseLong(getString(R.string.liferay_company_id)), Long.parseLong(getString(R.string.liferay_group_id)), pass, sName, email, "en", fName, null, lName, null, new JSONObjectCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.d(TAG, "onSuccess: " + result);
                try {
                    UserObject userObject = gson.fromJson(result.toString(), UserObject.class);
                    if (userObject != null) {
                        switchToHomeActivity(userObject, context, HomeActivity.class);
                    }
                } catch (Throwable e) {
                    Log.e(TAG, "onSuccess: ", e);
                    showError();
                }
                hideProgress();
            }

            @Override
            public void onFailure(Exception exception) {
                Log.e(TAG, "onFailure: ", exception);
                showError();
                if (ErrorCode.AddUserErrorCode.EMAIL_DUPLICATE.equals(exception.getMessage())) {
                    Utils.setError(context, edtEmail, tvErrorEmail, imgErrorEmail, Utils.format(getString(R.string.duplicate_format), getString(R.string.email)));
                    edtEmail.setError(true);
                    edtEmail.requestFocus();
                } else {
                    Utils.setError(context, edtSname, tvErrorSname, imgErrorSname, Utils.format(getString(R.string.duplicate_format), getString(R.string.screen_name)));
                    edtSname.setError(true);
                    edtSname.requestFocus();
                }
                hideProgress();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up:
                if (checkInfo()) {
                    signUpMethod();
                }
                break;
            default:
                hideKeyBroad();
                break;
        }
    }
}
