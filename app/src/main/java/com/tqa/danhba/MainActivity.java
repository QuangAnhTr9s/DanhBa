package com.tqa.danhba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.tqa.danhba.adapter.CustomAdapter;
import com.tqa.danhba.model.Contact;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private CustomAdapter customAdapter;
    private List<Contact> listContact;
    private ListView lv_danhBa;
    private ImageButton imgbtn_add;
    private SharedPreferences sharedPreferences;

    public List<Contact> getListContact() {
        return listContact;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWiget();
        listContact = new ArrayList<>();
        getDataFromSharedPreferences();

        recieveDataFragmentAdd();
        recieveDataFragmentSetting();

        imgbtn_add.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.main_activity, new FragmentAdd(), "FragmentAdd");
            fragmentTransaction.addToBackStack("FragAdd");
            fragmentTransaction.commit();
        });
        customAdapter = new CustomAdapter(this, R.layout.row_item_contact, listContact);
        lv_danhBa.setAdapter(customAdapter);
        lv_danhBa.setOnItemClickListener((parent, view, position, id) -> sendDataToFragmentSetting(position));
        customAdapter.notifyDataSetChanged();
    }

    private void recieveDataFragmentSetting() {
        Intent intentRDFS = getIntent();
        Bundle bundleListContactFrag = intentRDFS.getBundleExtra("bundle_listContactFrag");
        if (bundleListContactFrag != null) {
            listContact = bundleListContactFrag.getParcelableArrayList("listContactFrag");
            saveData(listContact);
        }
    }

    private void sendDataToFragmentSetting(int position) {
        String dataPhoneNumber = listContact.get(position).getmPhoneNumber();
        String dataName = listContact.get(position).getmName();
        boolean dataIsMale = listContact.get(position).isMale();

        Bundle bundle = new Bundle();
        bundle.putString("data_phone_number", dataPhoneNumber);
        bundle.putString("data_name", dataName);
        bundle.putBoolean("data_is_male", dataIsMale);
        bundle.putInt("position", position);

        FragmentSetting fragmentSetting = new FragmentSetting();
        fragmentSetting.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_activity, fragmentSetting);
        fragmentTransaction.addToBackStack("FragSetting");
        fragmentTransaction.commit();
    }

    private void getDataFromSharedPreferences() {
        //lay du lieu tu sharedPreferences
        sharedPreferences = getSharedPreferences("dataContact", MODE_PRIVATE);
        //lay du lieu tu sharedPreferences ra list
        String stringListContact = sharedPreferences.getString("list_contact", null); //lay gia tri ra, neu k co thi gan la null
        Gson gson = new Gson();
        if (stringListContact != null) {
            Type type = new TypeToken<List<Contact>>() {
            }.getType();
            listContact = gson.fromJson(stringListContact, type);
        }
    }

    private void recieveDataFragmentAdd() {
        Intent intentRDFA = getIntent();
        Bundle bundleObjContact = intentRDFA.getBundleExtra("bundle_obj_contact");

        if (bundleObjContact != null) {
            Contact contact = bundleObjContact.getParcelable("obj_contact");
            if (checkTK(contact)) {
                Toast.makeText(this, "Số điện thoại này đã tồn tại!", Toast.LENGTH_SHORT).show();
            } else {
                listContact.add(contact);
                Toast.makeText(this, "Đã thêm số điện thoại vào danh bạ!", Toast.LENGTH_SHORT).show();
                //cap nhat thay doi cho customAdapter
            }
        }
        saveData(listContact);
    }

    private void saveData(List<Contact> listContact) {
        //luu list vao sharedPreferences
        Gson gson = new Gson();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(listContact);
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
        if (!listContact.isEmpty()) {
            for (Contact o :
                    listContact) {
                if (contact.getmPhoneNumber() != null && o.getmPhoneNumber().contains(contact.getmPhoneNumber())) {
                    return true;
                }
            }
        }
        return false;
    }

}