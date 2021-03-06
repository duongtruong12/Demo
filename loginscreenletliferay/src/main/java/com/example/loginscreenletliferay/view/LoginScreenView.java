package com.example.loginscreenletliferay.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.liferay.mobile.screens.auth.BasicAuthMethod;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.auth.login.view.LoginViewModel;
import com.liferay.mobile.screens.base.BaseScreenlet;
import com.liferay.mobile.screens.base.ModalProgressBar;
import com.liferay.mobile.screens.context.AuthenticationType;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.util.LiferayLogger;

public class LoginScreenView extends LinearLayout implements LoginViewModel, View.OnClickListener {

	protected EditText loginEditText;
	protected EditText passwordEditText;
	protected Button submitButton;
	protected LinearLayout basicAuthenticationLayout;
	protected Button oAuth2Button;
	protected ModalProgressBar progressBar;
	private AuthenticationType authenticationType;
	private BasicAuthMethod basicAuthMethod;
	private BaseScreenlet screenlet;

	public LoginScreenView(Context context) {
		super(context);
	}

	public LoginScreenView(Context context, AttributeSet attributes) {
		super(context, attributes);
	}

	public LoginScreenView(Context context, AttributeSet attributes, int defaultStyle) {
		super(context, attributes, defaultStyle);
	}

	@Override
	public BasicAuthMethod getBasicAuthMethod() {
		return basicAuthMethod;
	}

	public void setBasicAuthMethod(BasicAuthMethod basicAuthMethod) {
		this.basicAuthMethod = basicAuthMethod;

		refreshLoginEditTextStyle();
	}

	@Override
	public String getLogin() {
		return loginEditText.getText().toString();
	}

	public AuthenticationType getAuthenticationType() {
		return authenticationType;
	}

	@Override
	public void setAuthenticationType(AuthenticationType authenticationType) {
		this.authenticationType = authenticationType;
	}

	@Override
	public String getPassword() {
		return passwordEditText.getText().toString();
	}

	@Override
	public void showStartOperation(String actionName) {
		if (progressBar != null) {
			progressBar.startProgress();
		}
	}

	@Override
	public void showFinishOperation(String actionName) {
		throw new AssertionError("Use showFinishOperation(user) instead");
	}

	@Override
	public void showFinishOperation(User user) {
		if (progressBar != null) {
			progressBar.finishProgress();
		}

		LiferayLogger.i("Login successful: " + user.getId());
	}

	@Override
	public void showFailedOperation(String actionName, Exception e) {
		if (progressBar != null) {
			progressBar.finishProgress();
		}

		LiferayLogger.e("Could not login", e);
	}

	@Override
	public BaseScreenlet getScreenlet() {
		return screenlet;
	}

	@Override
	public void setScreenlet(BaseScreenlet screenlet) {
		this.screenlet = screenlet;
	}

	@Override
	public void onClick(View view) {

		LoginScreenlet loginScreenlet = (LoginScreenlet) getScreenlet();
		loginScreenlet.performUserAction(LoginScreenlet.BASIC_AUTH);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		loginEditText = findViewById(com.liferay.mobile.screens.R.id.liferay_login);
		passwordEditText = findViewById(com.liferay.mobile.screens.R.id.liferay_password);
		progressBar = findViewById(com.liferay.mobile.screens.R.id.liferay_progress);

		basicAuthenticationLayout = findViewById(com.liferay.mobile.screens.R.id.basic_authentication_login);

		oAuth2Button = findViewById(com.liferay.mobile.screens.R.id.oauth2_authentication_login);
		if (oAuth2Button != null) {
			oAuth2Button.setOnClickListener(this);
		}

		submitButton = findViewById(com.liferay.mobile.screens.R.id.liferay_login_button);
		submitButton.setOnClickListener(this);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		if (oAuth2Button != null) {
			oAuth2Button.setVisibility(AuthenticationType.OAUTH2REDIRECT.equals(authenticationType) ? VISIBLE : GONE);
		}

		if (basicAuthenticationLayout != null) {
			basicAuthenticationLayout.setVisibility(
					AuthenticationType.OAUTH2REDIRECT.equals(authenticationType) ? GONE : VISIBLE);
		}

		refreshLoginEditTextStyle();
	}

	protected void refreshLoginEditTextStyle() {
		if (basicAuthMethod != null) {
			loginEditText.setHint(getResources().getString(getLabelResourceForAuthMode()));
			loginEditText.setInputType(basicAuthMethod.getInputType());
			loginEditText.setCompoundDrawablesWithIntrinsicBounds(
					ContextCompat.getDrawable(getContext(), getLoginEditTextDrawableId()), null, null, null);
		}
	}

	protected int getLoginEditTextDrawableId() {
		if (BasicAuthMethod.USER_ID.equals(basicAuthMethod)) {
			return com.liferay.mobile.screens.R.drawable.default_user_icon;
		} else if (BasicAuthMethod.EMAIL.equals(basicAuthMethod)) {
			return com.liferay.mobile.screens.R.drawable.default_mail_icon;
		}
		return com.liferay.mobile.screens.R.drawable.default_user_icon;
	}

	protected EditText getLoginEditText() {
		return loginEditText;
	}

	protected EditText getPasswordEditText() {
		return passwordEditText;
	}

	protected Button getSubmitButton() {
		return submitButton;
	}

	private int getLabelResourceForAuthMode() {
		if (basicAuthMethod != null) {
			switch (basicAuthMethod) {
				case SCREEN_NAME:
					return com.liferay.mobile.screens.R.string.screen_name;
				case USER_ID:
					return com.liferay.mobile.screens.R.string.user_id;
				default:
					return com.liferay.mobile.screens.R.string.email_address;
			}
		}
		return com.liferay.mobile.screens.R.string.email_address;
	}
}
