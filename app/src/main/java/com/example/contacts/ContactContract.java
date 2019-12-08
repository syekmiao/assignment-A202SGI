package com.example.contacts;

import android.provider.BaseColumns;

public class ContactContract {

    private  ContactContract(){}

    public static final class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "contactList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
