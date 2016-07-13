package com.techpalle.karan.mycontacts.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.techpalle.karan.mycontacts.utils.ContactsContract;

/**
 * Created by ADMIN on 6/30/2016.
 */
public class ContactsDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Contacts.db";
    public static final int DATABASE_VERSION = 1;

    //region SQL Command Constants
    public static final String SQL_CONTACTS_TABLE_CREATE = "CREATE TABLE "+ ContactsContract.Contact.TABLE_NAME+" ("
            +ContactsContract.Contact._ID+" INTEGER PRIMARY KEY, "+ContactsContract.Contact.COLUMN_CONTACT_NAME
            +" TEXT,"+ContactsContract.Contact.COLUMN_CONTACT_PHONE+" TEXT,"+ContactsContract.Contact.COLUMN_CONTACT_EMAIL
            +" TEXT);";
    public static final String SQL_DROP_CONTACTS = "DROP TABLE IF EXISTS "+ContactsContract.Contact.TABLE_NAME;
    //endregion

    public ContactsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DB_LOG","Database created/opened successfully");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CONTACTS_TABLE_CREATE);
        Log.d("DB_LOG","Table created successfully");
    }

    public void insertContact(String name, String number, String email, SQLiteDatabase database){
        ContentValues cv= new ContentValues();
        cv.put(ContactsContract.Contact.COLUMN_CONTACT_NAME, name);
        cv.put(ContactsContract.Contact.COLUMN_CONTACT_PHONE, number);
        cv.put(ContactsContract.Contact.COLUMN_CONTACT_EMAIL, email);
        database.insert(ContactsContract.Contact.TABLE_NAME, null,cv);
        Log.d("DB_LOG","Row inserted");
    }

    public int updateContact(String name, String newName, String newMobile, String newEmail, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactsContract.Contact.COLUMN_CONTACT_NAME, newName);
        contentValues.put(ContactsContract.Contact.COLUMN_CONTACT_EMAIL, newEmail);
        contentValues.put(ContactsContract.Contact.COLUMN_CONTACT_PHONE, newMobile);
        String selection = ContactsContract.Contact.COLUMN_CONTACT_NAME + " like ?";
        String[] selectionArgs = {name};
        return database.update(ContactsContract.Contact.TABLE_NAME, contentValues, selection, selectionArgs);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d("DB_LOG","Database being re-created");
        if(oldVersion<newVersion){
            sqLiteDatabase.execSQL(SQL_DROP_CONTACTS);
            onCreate(sqLiteDatabase);
        }
    }
}
