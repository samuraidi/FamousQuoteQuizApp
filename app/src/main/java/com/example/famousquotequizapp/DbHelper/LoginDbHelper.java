package com.example.famousquotequizapp.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.famousquotequizapp.LoginContract.LoginTable;
import com.example.famousquotequizapp.User;

public class LoginDbHelper extends QuizDbHelper {

    public LoginDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean create(User user){
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(LoginTable.COLUMN_FIRSTNAME, user.getFirstName());
            cv.put(LoginTable.COLUMN_LASTNAME, user.getLastName());
            cv.put(LoginTable.COLUMN_USERNAME, user.getUsername());
            cv.put(LoginTable.COLUMN_PASSWORD, user.getPassword());
            result = db.insert(LoginTable.TABLE_NAME, null, cv) > 0;

        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public User login(String username, String password) {
        User user = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + LoginTable.TABLE_NAME +" WHERE " + LoginTable.COLUMN_USERNAME +"=? AND "+ LoginTable.COLUMN_PASSWORD + "=?", new String[]{username, password});
            if(cursor.moveToFirst()) {
                user = new User();
                user.setFirstName(cursor.getString(0));
                user.setLastName(cursor.getString(1));
                user.setUsername(cursor.getString(2));
                user.setPassword(cursor.getString(3));
            }
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    public User checkUsername(String username) {
        User user = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + LoginTable.TABLE_NAME +" WHERE " + LoginTable.COLUMN_USERNAME +"=?", new String[]{username});
            if(cursor.moveToFirst()) {
                user = new User();
                user.setFirstName(cursor.getString(0));
                user.setLastName(cursor.getString(1));
                user.setUsername(cursor.getString(2));
                user.setPassword(cursor.getString(3));
            }
        } catch (Exception e) {
            user = null;
        }
        return user;
    }
}
