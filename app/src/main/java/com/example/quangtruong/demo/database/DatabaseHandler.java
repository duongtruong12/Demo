package com.example.quangtruong.demo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.quangtruong.demo.object.UserObject;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LoginScreenLet";

    //    TableName
    private static final String TABLE_USER = "TABLE_USER";

    //   ColumnsName
    private static final String COLUMNS_USER_UUID = "COLUMNS_USER_UUID";
    private static final String COLUMN_USER_SCREEN_NAME = "COLUMN_USER_SCREEN_NAME";
    private static final String COLUMN_USER_ID = "COLUMN_USER_ID";
    private static final String COLUMN_USER_FIRST_NAME = "COLUMN_USER_FIRST_NAME";
    private static final String COLUMN_USER_LAST_NAME = "COLUMN_USER_LAST_NAME";
    private static final String COLUMN_USER_EMAIL_ADDRESS = "COLUMN_USER_EMAIL_ADDRESS";
    private static final String COLUMN_USER_COMPANY_ID = "COLUMN_USER_COMPANY_ID";
    private static final String COLUMN_USER_PASSWORD = "COLUMN_USER_PASSWORD";
    private static final String COLUMN_USER_THEME = "COLUMN_USER_THEME";

    //USER CREATE TABLE
    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "
            + TABLE_USER + "("
            + COLUMNS_USER_UUID + " TEXT, "
            + COLUMN_USER_SCREEN_NAME + " TEXT, "
            + COLUMN_USER_ID + " TEXT,"
            + COLUMN_USER_FIRST_NAME + " TEXT,"
            + COLUMN_USER_LAST_NAME + " TEXT,"
            + COLUMN_USER_EMAIL_ADDRESS + " TEXT,"
            + COLUMN_USER_COMPANY_ID + " TEXT,"
            + COLUMN_USER_THEME + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=1;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void addUser(UserObject userObject, String password) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            dropTableUser(db);

            ContentValues values = new ContentValues();
            values.put(COLUMNS_USER_UUID, userObject.getUuid());
            values.put(COLUMN_USER_SCREEN_NAME, userObject.getScreenName());
            values.put(COLUMN_USER_ID, userObject.getUserId());
            values.put(COLUMN_USER_FIRST_NAME, userObject.getFirstName());
            values.put(COLUMN_USER_LAST_NAME, userObject.getLastName());
            values.put(COLUMN_USER_EMAIL_ADDRESS, userObject.getEmailAddress());
            values.put(COLUMN_USER_COMPANY_ID, userObject.getCompanyId());
            values.put(COLUMN_USER_THEME, userObject.getUserTheme());
            values.put(COLUMN_USER_PASSWORD, password);

            // Inserting Row
            db.insert(TABLE_USER, null, values);
            if (db.isOpen())
                db.close();
        } catch (Throwable e) {
            Log.e(TAG, "addUser: ", e);
        }
    }

    public UserObject getUser() {
        UserObject object = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectQuery = "SELECT  * FROM " + TABLE_USER;

            Cursor c = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (c.moveToFirst()) {
                object = new UserObject();

                object.setUuid(c.getString(c.getColumnIndex(COLUMNS_USER_UUID)));
                object.setScreenName(c.getString(c.getColumnIndex(COLUMN_USER_SCREEN_NAME)));
                object.setUserId(c.getString(c.getColumnIndex(COLUMN_USER_ID)));
                object.setFirstName(c.getString(c.getColumnIndex(COLUMN_USER_FIRST_NAME)));
                object.setLastName(c.getString(c.getColumnIndex(COLUMN_USER_LAST_NAME)));
                object.setEmailAddress(c.getString(c.getColumnIndex(COLUMN_USER_EMAIL_ADDRESS)));
                object.setCompanyId(c.getString(c.getColumnIndex(COLUMN_USER_COMPANY_ID)));
                object.setUserPassword(c.getString(c.getColumnIndex(COLUMN_USER_PASSWORD)));
                object.setUserTheme(c.getString(c.getColumnIndex(COLUMN_USER_THEME)));
            }
            c.close();
        } catch (Throwable e) {
            Log.e(TAG, "findRiderDTO: ", e);
        }
        return object;
    }

    public void dropTableUser(SQLiteDatabase db) {
        try {
            if (db == null)
                db = this.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            db.execSQL(CREATE_TABLE_USER);
        } catch (Exception e) {
            Log.e(TAG, "deleteLatLgn: ", e);
        }
    }
}