package com.example.contacts;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addNew extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private EditText mName, mPhone, mEmail;
    private Button mSaveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        ContactDBHelper dbHelper = new ContactDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        mName = (EditText)findViewById(R.id.edit_name);
        mPhone = (EditText)findViewById(R.id.edit_phoneNo);
        mEmail = (EditText)findViewById(R.id.edit_email);
        mSaveBtn = (Button)findViewById(R.id.saveBtn);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
    }

    private void addContact(){

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

        mDatabase.insert(ContactContract.ContactEntry.TABLE_NAME, null, cv);

        mName.getText().clear();
        mPhone.getText().clear();
        mEmail.getText().clear();
        startActivity(new Intent(addNew.this, MainActivity.class));
    }
}
