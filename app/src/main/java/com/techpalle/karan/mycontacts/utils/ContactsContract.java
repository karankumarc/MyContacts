package com.techpalle.karan.mycontacts.utils;

import android.provider.BaseColumns;

/**
 * Created by ADMIN on 6/30/2016.
 */
public class ContactsContract {

    public static abstract class Contact implements BaseColumns{
        public static final String TABLE_NAME = "contact";
        public static final String COLUMN_CONTACT_NAME = "name";
        public static final String COLUMN_CONTACT_PHONE = "phone";
        public static final String COLUMN_CONTACT_EMAIL = "email";
    }

    public static abstract class Employee{

    }

    public static abstract class Department{

    }
}
