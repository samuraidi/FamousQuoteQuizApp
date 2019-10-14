package com.example.famousquotequizapp;

import android.provider.BaseColumns;

public final class LoginContract {

    private LoginContract() {}

    public static class LoginTable implements BaseColumns {
        public static final String TABLE_NAME = "login";
        public static final String COLUMN_FIRSTNAME = "firstname";
        public static final String COLUMN_LASTNAME = "lastname";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
    }
}
