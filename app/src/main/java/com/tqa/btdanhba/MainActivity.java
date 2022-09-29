package com.tqa.btdanhba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tqa.btdanhba.adapter.CustomAdapter;
import com.tqa.btdanhba.model.Contact;
import com.tqa.btdanhba.model.FragmentSetting;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private CustomAdapter customAdapter;
    private ArrayList<Contact> arrayContact;
    private ListView lv_danhBa;
    private ImageButton imgbtn_add;
    private SharedPreferences sharedPreferences;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWiget();
        arrayContact = new ArrayList<>();
        //lay du lieu tu sharedPreferences
        sharedPreferences = getSharedPreferences("dataContact", MODE_PRIVATE);
        //lay du lieu tu sharedPreferences ra list
        String stringListContact = sharedPreferences.getString("list_contact", null); //lay gia tri ra, neu k co thi gan la null
        Gson gson = new Gson();
        if (stringListContact != null) {
            Type type = new TypeToken<List<Contact>>() {
            }.getType();
            arrayContact = gson.fromJson(stringListContact, type);
        }
        customAdapter = new CustomAdapter(this, R.layout.row_item_contact, arrayContact);
        lv_danhBa.setAdapter(customAdapter);
        lv_danhBa.setOnClickListener(view -> {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main_activity, new FragmentSetting(), "FragmentSetting");
            fragmentTransaction.addToBackStack("FragSetting");
            fragmentTransaction.commit();
        });
        recieveDataFragmentAdd();

        imgbtn_add.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.main_activity, new FragmentAdd(), "FragmentAdd");
            fragmentTransaction.addToBackStack("FragAdd");
            fragmentTransaction.commit();
        });

    }

    private void recieveDataFragmentAdd() {
        //        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
//        Bundle bundle = getIntent().getExtras();
//        Contact contact = (Contact) bundle.get("obj_contact");

        if (bundle != null) {
            Contact contact = (Contact) bundle.getParcelable("obj_contact");
            if (checkTK(contact)) {
                Toast.makeText(this, "Số điện thoại này đã tồn tại!", Toast.LENGTH_SHORT).show();
            } else {
                arrayContact.add(contact);
                Toast.makeText(this, "Đã thêm số điện thoại vào danh bạ!", Toast.LENGTH_SHORT).show();
                //cap nhat thay doi cho customAdapter
                customAdapter.notifyDataSetChanged();
            }
        }
        //luu list vao sharedPreferences
        Gson gson = new Gson();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(arrayContact);
        editor.putString("list_contact", json);
        editor.apply();
    }

    public void setWiget() {
        lv_danhBa = findViewById(R.id.lv_danhBa);
        imgbtn_add = findViewById(R.id.imgbtn_add);
    }

    public boolean checkTK(Contact contact) {
//     duyet tung phan tu trong arrayContact, dem vi tri xuat hien cua so dien thoai vua nhap,
//    neu xuat hien o vi tri >=0  thi return true
        for (Contact o :
                arrayContact) {
            if (o.getmPhoneNumber().contains(contact.getmPhoneNumber())) {
                return true;
            }
        }
        return false;
    }

}