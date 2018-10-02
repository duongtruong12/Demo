package com.example.quangtruong.demo.constant;

public interface ErrorCode {
    interface AddUserErrorCode {
        String SCREEN_NAME_DUPLICATE = "UserScreenNameException$MustNotBeDuplicate";
        String EMAIL_DUPLICATE = "com.liferay.portal.kernel.exception.UserEmailAddressException$MustNotBeDuplicate";
    }
}
