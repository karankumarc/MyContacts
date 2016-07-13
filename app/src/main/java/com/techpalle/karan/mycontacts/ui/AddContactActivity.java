package com.techpalle.karan.mycontacts.ui;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techpalle.karan.mycontacts.R;
import com.techpalle.karan.mycontacts.utils.ContactsDbHelper;

public class AddContactActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextContactName, editTextContactNumber, editTextContactEmail;
    Button buttonInsertContact;

    ContactsDbHelper helper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        helper = new ContactsDbHelper(this);

        editTextContactName = (EditText) findViewById(R.id.edit_contact_name);
        editTextContactNumber = (EditText) findViewById(R.id.edit_contact_mobile);
        editTextContactEmail = (EditText) findViewById(R.id.edit_contact_email);
        buttonInsertContact = (Button) findViewById(R.id.button_insert_contact);

        buttonInsertContact.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String name = editTextContactName.getText().toString();
        String number = editTextContactNumber.getText().toString();
        String email = editTextContactEmail.getText().toString();

        // Open connection to database
        database = helper.getWritableDatabase();

        // Perform insert operation
        helper.insertContact(name, number, email, database);

        // Close database connection
        helper.close();

        editTextContactName.setText("");
        editTextContactEmail.setText("");
        editTextContactNumber.setText("");

        editTextContactName.requestFocus();

        Toast.makeText(AddContactActivity.this, "1 contact added successfully", Toast.LENGTH_SHORT).show();
    }
}
