package com.example.loginscreenletliferay.view;

import com.liferay.mobile.screens.auth.BasicAuthViewModel;
import com.liferay.mobile.screens.context.AuthenticationType;
import com.liferay.mobile.screens.context.User;

public interface LoginViewModel extends BasicAuthViewModel {

	/**
	 * Gets the login information: email, screen name or user ID.
	 *
	 * @return login
	 */
	String getLogin();

	/**
	 * Gets the user password.
	 *
	 * @return password.
	 */
	String getPassword();

	/**
	 * Sets the {@link AuthenticationType}.
	 */
	void setAuthenticationType(AuthenticationType authenticationType);

	/**
	 * Called when the login is successfully completed.
	 */
	void showFinishOperation(User user);
}
