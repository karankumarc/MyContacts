package com.techpalle.karan.mycontacts.ui;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.techpalle.karan.mycontacts.R;

public class MainActivity extends AppCompatActivity {

    Button buttonAddContact;
    Button buttonViewContacts;

    //region Dual Spinner
    /*Spinner spinner1, spinner2;
    String[] stringArr = {"A", "B", "C"};
    LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<>();
    ArrayList arrayList1 = new ArrayList();
    ArrayList arrayList2 = new ArrayList();
    ArrayList arrayList3 = new ArrayList();*/
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();

        //region Dual Spinner
        /*arrayList1.add("Apple");
        arrayList1.add("Acrobat");
        arrayList2.add("Bat");
        arrayList2.add("Bulls eye");
        arrayList3.add("Comb");
        arrayList3.add("Congress");

        map.put(stringArr[0], arrayList1);
        map.put(stringArr[1], arrayList2);
        map.put(stringArr[2], arrayList3);*/
        /*ArrayList<String> src= new ArrayList<String>();
        src.addAll(map.keySet());
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                src);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "YAY", Toast.LENGTH_SHORT).show();
                ArrayList<String> src1 = new ArrayList<String>();
                src1.addAll(map.get(stringArr[i]));
                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,
                        src1);
                arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(arrayAdapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
        //endregion

        buttonAddContact = (Button) findViewById(R.id.button_add_contact);
        buttonViewContacts = (Button) findViewById(R.id.button_view_contacts);

        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });

        buttonViewContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewContactsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupWindowAnimations() {
        Slide slide = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(slide);
        }
    }
}
