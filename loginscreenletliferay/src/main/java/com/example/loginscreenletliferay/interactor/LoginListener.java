package com.example.loginscreenletliferay.interactor;

import com.liferay.mobile.screens.context.User;

public interface LoginListener {

	/**
	 * Called when login successfully completes. The `user` parameter contains
	 * a set of the logged in user’s attributes. The supported keys are
	 * the same as those in the portal’s {@link User} entity.
	 */
	void onLoginSuccess(User user);

	/**
	 * Called when an error occurs in the process.
	 *
	 * @param e exception
	 */
	void onLoginFailure(Exception e);


	void onAuthenticationBrowserShown();
}