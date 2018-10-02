package com.example.quangtruong.demo.object;

import android.os.Parcel;
import android.os.Parcelable;

public class UserObject implements Parcelable {
    private String portraitId;
    private boolean agreedToTermsOfUse;
    private String screenName;
    private String facebookId;
    private String reminderQueryAnswer;
    private boolean defaultUser;
    private String lastFailedLoginDate;
    private String userId;
    private String loginDate;
    private String ldapServerId;
    private String lockoutDate;
    private String firstName;
    private String externalReferenceCode;
    private long createDate;
    private String jobTitle;
    private String middleName;
    private String lastName;
    private String lastLoginDate;
    private String googleUserId;
    private int status;
    private String greeting;
    private String reminderQueryQuestion;
    private String emailAddress;
    private boolean lockout;
    private String languageId;
    private long modifiedDate;
    private String lastLoginIP;
    private String contactId;
    private int failedLoginAttempts;
    private String loginIP;
    private boolean emailAddressVerified;
    private int graceLoginCount;
    private String uuid;
    private String companyId;
    private String mvccVersion;
    private String comments;
    private String timeZoneId;
    private String openId;
    private String userTheme;
    private String userPassword;

    private UserObject(Parcel in) {
        portraitId = in.readString();
        agreedToTermsOfUse = in.readByte() != 0;
        screenName = in.readString();
        facebookId = in.readString();
        reminderQueryAnswer = in.readString();
        defaultUser = in.readByte() != 0;
        lastFailedLoginDate = in.readString();
        userId = in.readString();
        loginDate = in.readString();
        ldapServerId = in.readString();
        lockoutDate = in.readString();
        firstName = in.readString();
        externalReferenceCode = in.readString();
        createDate = in.readLong();
        jobTitle = in.readString();
        middleName = in.readString();
        lastName = in.readString();
        lastLoginDate = in.readString();
        googleUserId = in.readString();
        status = in.readInt();
        greeting = in.readString();
        reminderQueryQuestion = in.readString();
        emailAddress = in.readString();
        lockout = in.readByte() != 0;
        languageId = in.readString();
        modifiedDate = in.readLong();
        lastLoginIP = in.readString();
        contactId = in.readString();
        failedLoginAttempts = in.readInt();
        loginIP = in.readString();
        emailAddressVerified = in.readByte() != 0;
        graceLoginCount = in.readInt();
        uuid = in.readString();
        companyId = in.readString();
        mvccVersion = in.readString();
        comments = in.readString();
        timeZoneId = in.readString();
        openId = in.readString();
        userTheme = in.readString();
    }

    public UserObject() {
    }

    public String getUserTheme() {
        return userTheme;
    }

    public void setUserTheme(String userTheme) {
        this.userTheme = userTheme;
    }

    public String getPortraitId() {
        return portraitId;
    }

    public void setPortraitId(String portraitId) {
        this.portraitId = portraitId;
    }

    public boolean isAgreedToTermsOfUse() {
        return agreedToTermsOfUse;
    }

    public void setAgreedToTermsOfUse(boolean agreedToTermsOfUse) {
        this.agreedToTermsOfUse = agreedToTermsOfUse;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getReminderQueryAnswer() {
        return reminderQueryAnswer;
    }

    public void setReminderQueryAnswer(String reminderQueryAnswer) {
        this.reminderQueryAnswer = reminderQueryAnswer;
    }

    public boolean isDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(boolean defaultUser) {
        this.defaultUser = defaultUser;
    }

    public String getLastFailedLoginDate() {
        return lastFailedLoginDate;
    }

    public void setLastFailedLoginDate(String lastFailedLoginDate) {
        this.lastFailedLoginDate = lastFailedLoginDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getLdapServerId() {
        return ldapServerId;
    }

    public void setLdapServerId(String ldapServerId) {
        this.ldapServerId = ldapServerId;
    }

    public String getLockoutDate() {
        return lockoutDate;
    }

    public void setLockoutDate(String lockoutDate) {
        this.lockoutDate = lockoutDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getExternalReferenceCode() {
        return externalReferenceCode;
    }

    public void setExternalReferenceCode(String externalReferenceCode) {
        this.externalReferenceCode = externalReferenceCode;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getGoogleUserId() {
        return googleUserId;
    }

    public void setGoogleUserId(String googleUserId) {
        this.googleUserId = googleUserId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getReminderQueryQuestion() {
        return reminderQueryQuestion;
    }

    public void setReminderQueryQuestion(String reminderQueryQuestion) {
        this.reminderQueryQuestion = reminderQueryQuestion;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isLockout() {
        return lockout;
    }

    public void setLockout(boolean lockout) {
        this.lockout = lockout;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public boolean isEmailAddressVerified() {
        return emailAddressVerified;
    }

    public void setEmailAddressVerified(boolean emailAddressVerified) {
        this.emailAddressVerified = emailAddressVerified;
    }

    public int getGraceLoginCount() {
        return graceLoginCount;
    }

    public void setGraceLoginCount(int graceLoginCount) {
        this.graceLoginCount = graceLoginCount;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getMvccVersion() {
        return mvccVersion;
    }

    public void setMvccVersion(String mvccVersion) {
        this.mvccVersion = mvccVersion;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(portraitId);
        out.writeByte((byte) (agreedToTermsOfUse ? 1 : 0));
        out.writeString(screenName);
        out.writeString(facebookId);
        out.writeString(reminderQueryAnswer);
        out.writeByte((byte) (defaultUser ? 1 : 0));
        out.writeString(lastFailedLoginDate);
        out.writeString(userId);
        out.writeString(loginDate);
        out.writeString(ldapServerId);
        out.writeString(lockoutDate);
        out.writeString(firstName);
        out.writeString(externalReferenceCode);
        out.writeLong(createDate);
        out.writeString(jobTitle);
        out.writeString(middleName);
        out.writeString(lastName);
        out.writeString(lastLoginDate);
        out.writeString(googleUserId);
        out.writeInt(status);
        out.writeString(greeting);
        out.writeString(reminderQueryQuestion);
        out.writeString(emailAddress);
        out.writeByte((byte) (lockout ? 1 : 0));
        out.writeString(languageId);
        out.writeLong(modifiedDate);
        out.writeString(lastLoginIP);
        out.writeString(contactId);
        out.writeInt(failedLoginAttempts);
        out.writeString(loginIP);
        out.writeByte((byte) (emailAddressVerified ? 1 : 0));
        out.writeInt(graceLoginCount);
        out.writeString(uuid);
        out.writeString(companyId);
        out.writeString(mvccVersion);
        out.writeString(comments);
        out.writeString(timeZoneId);
        out.writeString(openId);
        out.writeString(userTheme);
    }

    public static final Creator<UserObject> CREATOR = new Creator<UserObject>() {
        public UserObject createFromParcel(Parcel in) {
            return new UserObject(in);
        }

        public UserObject[] newArray(int size) {
            return new UserObject[size];
        }
    };
}
