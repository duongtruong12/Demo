package com.liferay.mobile.android.callservice;

import android.util.Log;

import com.liferay.mobile.android.callback.typed.JSONObjectCallback;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v7.user.UserService;

import org.json.JSONArray;
import org.json.JSONObject;

public class User {
    private static final String TAG = User.class.getSimpleName();

    public static void addUser(final Session session,
                               final long companyId, long groupId, String password,
                               String screenName, final String emailAddress, final String locale,
                               final String firstName, String middleName, String lastName,
                               String jobTitle, final JSONObjectCallback callback) {
        try {
            middleName = (middleName != null) ? middleName : "";
            lastName = (lastName != null) ? lastName : "";
            screenName = (screenName != null) ? screenName : "";
            password = (password != null) ? password : "";
            jobTitle = (jobTitle != null) ? jobTitle : "";

            final boolean autoPassword = password.isEmpty();
            final boolean autoScreenName = screenName.isEmpty();
            final long facebookId = 0;
            final String openId = "";
            final int prefixId = 0;
            final int suffixId = 0;
            final boolean male = true;
            final int birthdayMonth = 1;
            final int birthdayDay = 1;
            final int birthdayYear = 1970;
            final JSONArray groupIds = new JSONArray();
            groupIds.put(groupId);

            final JSONArray organizationIds = new JSONArray();
            final JSONArray roleIds = new JSONArray();
            final JSONArray userGroupIds = new JSONArray();
            final boolean sendEmail = true;

            final String finalPassword = password;
            final String finalPassword1 = password;
            final String finalScreenName = screenName;
            final String finalMiddleName = middleName;
            final String finalLastName = lastName;
            final String finalJobTitle = jobTitle;
            UserService user = new UserService(session);
            session.setCallback(new JSONObjectCallback() {

                @Override
                public void onSuccess(JSONObject sites) {
                    Log.d(TAG, "onSuccess: " + sites);
                    if (sites != null) {
                        callback.onSuccess(sites);
                    } else {
                        callback.onFailure(new Exception("NULL_OBJECT"));
                    }
                }

                @Override
                public void onFailure(Exception exception) {
                    callback.onFailure(exception);
                }

            });
            user.addUser(companyId, autoPassword, finalPassword, finalPassword1, autoScreenName, finalScreenName,
                    emailAddress, facebookId, openId, locale, firstName, finalMiddleName, finalLastName, prefixId, suffixId,
                    male, birthdayMonth, birthdayDay, birthdayYear, finalJobTitle, groupIds, organizationIds, roleIds, userGroupIds,
                    sendEmail, null);
        } catch (Exception e) {
            callback.onFailure(e);
        }
    }
}
