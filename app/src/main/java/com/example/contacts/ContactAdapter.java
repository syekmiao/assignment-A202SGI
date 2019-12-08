package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public ContactAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName;
        public TextView txtPhone;
        public TextView txtEmail;
        public RelativeLayout mrelative;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.contact_name);
            txtPhone = itemView.findViewById(R.id.contact_phone);
            txtEmail = itemView.findViewById(R.id.contact_email);
            mrelative = itemView.findViewById(R.id.relative_item);
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.contact_item, viewGroup, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME));
        String phone = mCursor.getString(mCursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_PHONE));
        String email = mCursor.getString(mCursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_EMAIL));
        long id = mCursor.getLong(mCursor.getColumnIndex(ContactContract.ContactEntry._ID));
        final long id_pass = id;

        holder.txtName.setText(name);
        holder.txtPhone.setText(phone);
        holder.txtEmail.setText(email);
        holder.itemView.setTag(id);
        /*
        holder.mrelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, addNew.class);
                intent.putExtra("onclick_id", id_pass);
                mContext.startActivity(intent);
            }
        });
        these codes is me trying let user click on the item to edit the item
        but the database code have some problem that i could not solve at this time,
        so i disable the onclick listener to prevent the user from using it.
        */
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }

        mCursor = newCursor;

        if(newCursor != null){
            notifyDataSetChanged();
        }
    }
}
