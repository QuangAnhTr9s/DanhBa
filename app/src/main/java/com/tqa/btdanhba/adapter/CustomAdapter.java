package com.tqa.btdanhba.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tqa.btdanhba.R;
import com.tqa.btdanhba.model.Contact;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private int resource;
    private List<Contact> arrContact;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrContact = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.row_item_contact, parent, false);
            viewHolder.imgBtn_avatar = convertView.findViewById(R.id.imgBtn_avatar);
            viewHolder.txt_name = convertView.findViewById(R.id.txt_name);
            viewHolder.txt_phoneNumber = convertView.findViewById(R.id.txt_phoneNumber);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contact contact = arrContact.get(position);
        viewHolder.txt_name.setText(contact.getmName());
        viewHolder.txt_phoneNumber.setText(contact.getmPhoneNumber());



//        Bitmap bitmap_man = BitmapFactory.decodeResource(Resources.getSystem() , R.drawable.man_avatar_png);
//        Bitmap bitmap_woman = BitmapFactory.decodeResource(Resources.getSystem() , R.drawable.woman_avatar_png);


        if (contact.isMale()) {
            viewHolder.imgBtn_avatar.setImageResource(R.drawable.man_avatar_1048);
//            viewHolder.imgBtn_avatar.setImageBitmap(bitmap_man);
        } else {
//            viewHolder.imgBtn_avatar.setImageBitmap(bitmap_woman);
            viewHolder.imgBtn_avatar.setImageResource(R.drawable.woman_avatar_1048);
        }
        return convertView;
    }


    public class ViewHolder {
        ImageView imgBtn_avatar;
        TextView txt_name;
        TextView txt_phoneNumber;
    }
}
