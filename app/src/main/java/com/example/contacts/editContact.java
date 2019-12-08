package com.example.contacts;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.contacts.ContactContract.ContactEntry.TABLE_NAME;

public class editContact extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private EditText mName, mPhone, mEmail;
    private Button mUpdateBtn;
    private String mUpdateID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        ContactDBHelper dbHelper = new ContactDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        mName = (EditText)findViewById(R.id.update_name);
        mPhone = (EditText)findViewById(R.id.update_phoneNo);
        mEmail = (EditText)findViewById(R.id.update_email);
        mUpdateBtn = (Button)findViewById(R.id.updateBtn);

        mUpdateID = getIntent().getStringExtra("onclick_id");

        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact(mUpdateID);
            }
        });
    }

    private void updateContact(String mUpdateID){
        if(mName.getText().toString().trim().length() == 0 ||
                mPhone.getText().toString().trim().length() == 0 ||
                mEmail.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        String cvname = mName.getText().toString();
        String cvphone = mPhone.getText().toString();
        String cvemail = mEmail.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(ContactContract.ContactEntry.COLUMN_NAME, cvname);
        cv.put(ContactContract.ContactEntry.COLUMN_PHONE, cvphone);
        cv.put(ContactContract.ContactEntry.COLUMN_EMAIL, cvemail);

        /* mDatabase.update(ContactContract.ContactEntry.TABLE_NAME,
                ContactContract.ContactEntry._ID + "=" + mUpdateID, null);

        these codes is me trying to update the database with the newest information,
        but it have errors, so i just leave it here
        the problem probably is because it cannot accept the mUpdateID,
        because the way it is passed to this class(by intent), and it's a String, not a long.
        */

        startActivity(new Intent(editContact.this, MainActivity.class));
    }
}
