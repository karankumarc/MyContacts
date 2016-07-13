package com.techpalle.karan.mycontacts.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.techpalle.karan.mycontacts.R;
import com.techpalle.karan.mycontacts.model.Contact;
import com.techpalle.karan.mycontacts.utils.ContactsDbHelper;

import java.util.ArrayList;

public class ViewContactsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Contact> arrayList;
    MyAdapter adapter;

    ContactsDbHelper helper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);

        helper = new ContactsDbHelper(this);

        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<Contact>();
        adapter = new MyAdapter();

        getContactsFromDatabase();

        listView.setAdapter(adapter);
    }

    private void getContactsFromDatabase() {
        database = helper.getReadableDatabase();
        Cursor cursor = helper.getAllContacts(database);
        if(cursor.moveToFirst()){
            do{
                /**
                 * Write operation to be performed for one record
                 */
                Contact contact = new Contact(cursor.getString(1),cursor.getString(2),
                    cursor.getString(3));
                arrayList.add(contact);
            } while (cursor.moveToNext());
        }
        adapter.notifyDataSetChanged();
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return arrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // Get data from source at position i
            Contact contact = arrayList.get(i);
            // Inflate the row
            View v= getLayoutInflater().inflate(R.layout.row_contact_layout, null);
            // find the UI elements of the row
            TextView textViewName = (TextView) v.findViewById(R.id.text_name);
            TextView textViewPhone = (TextView) v.findViewById(R.id.text_number);
            TextView textViewEmail = (TextView) v.findViewById(R.id.text_email);
            // Fill data from object onto UI elements
            textViewName.setText(contact.getName());
            textViewPhone.setText(contact.getMobile());
            textViewEmail.setText(contact.getEmail());
            // Return the row
            return v;
        }
    }
}
