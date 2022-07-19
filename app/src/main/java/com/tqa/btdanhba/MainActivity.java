package com.tqa.btdanhba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.tqa.btdanhba.adapter.CustomAdapter;
import com.tqa.btdanhba.model.Contact;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private CustomAdapter customAdapter;
    private ArrayList<Contact> arrayContact;


    private ListView lv_danhBa;
    private ImageButton imgbtn_add;
    FragmentManager fragmentManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWiget();
        arrayContact = new ArrayList<>();
        customAdapter = new CustomAdapter(this, R.layout.row_item_contact, arrayContact);
        lv_danhBa.setAdapter(customAdapter);

        imgbtn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.main_activity, new FragmentAdd(), "FragmentAdd");
                fragmentTransaction.addToBackStack("add");
                fragmentTransaction.commit();

            }
        });
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
        Toast.makeText(this, "OnCreate", Toast.LENGTH_SHORT).show();
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
            if (o.getmPhoneNumber().indexOf(contact.getmPhoneNumber()) >= 0) {
                return true;
            }
        }
        return false;
    }
//    public void add_person(View view) {
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.main_activity, new FragmentAdd(), "FragmentAdd");
//        fragmentTransaction.addToBackStack("aaa");
//        fragmentTransaction.commit();
//    }

    @Override
    protected void onStart() {
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
        super.onStart();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}