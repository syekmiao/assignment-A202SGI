package com.example.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactDBHelper dbHelper = new ContactDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ContactAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
                removeItem((long) viewHolder.itemView.getTag());
            }

        }).attachToRecyclerView(recyclerView);
    }

    //this is for the toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    //this is for the toolbar menu click add menu button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newContact:
                startActivity(new Intent(MainActivity.this, addNew.class));
                break;
            default:
                break;
        }

        return true;
    }

    //when user swipe the item in the recycler view, it will remove the item from the database
    private void removeItem(long id){
        mDatabase.delete(ContactContract.ContactEntry.TABLE_NAME,
                ContactContract.ContactEntry._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
    }

    //this is to get all the item in the database, arranging by time, descending
    private Cursor getAllItems(){
        return mDatabase.query(
                ContactContract.ContactEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                ContactContract.ContactEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
