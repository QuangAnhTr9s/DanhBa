package com.tqa.btdanhba;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tqa.btdanhba.model.Contact;

import java.util.ArrayList;


public class FragmentSetting extends Fragment {
    private EditText edt_name_frag_setting, edt_phoneNumber_frag_setting, edt_is_male;
    private Button btn_delete, btn_update;
    private String dataPhoneNumber, dataName;
    private boolean dataIsMale = true;
    private int mPosition;
    private MainActivity mMainActivity;
    private ArrayList<Contact> listContactFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        setWiget(view);
        mMainActivity = (MainActivity) getActivity();
        listContactFrag = new ArrayList<>();
        listContactFrag = (ArrayList<Contact>) mMainActivity.getListContact();
//        for (Contact contact : listContactFrag = (ArrayList<Contact>) mMainActivity.getListContact()) {
//            Toast.makeText(mMainActivity, contact.toString(), Toast.LENGTH_SHORT).show();
//        }

        recieveDataFromMainActivity();
        edt_phoneNumber_frag_setting.setText(dataPhoneNumber);
        edt_name_frag_setting.setText(dataName);
        if (dataIsMale) {
            edt_is_male.setText("Nam");
        } else {
            edt_is_male.setText("Nữ");
        }
        btn_delete.setOnClickListener(v -> {
            listContactFrag.remove(mPosition);
            sendToMain(listContactFrag);

        });
        btn_update.setOnClickListener(v -> {
            String edtNameFragSetting = edt_name_frag_setting.getText().toString().trim();
            if (!edtNameFragSetting.equalsIgnoreCase("")) {
                listContactFrag.get(mPosition).setmName(edtNameFragSetting);
            }
            String edtPhoneNumberFragSetting = edt_phoneNumber_frag_setting.getText().toString().trim();
            if (!edtPhoneNumberFragSetting.equalsIgnoreCase("")) {
                listContactFrag.get(mPosition).setmPhoneNumber(edtPhoneNumberFragSetting);
            }
            if (edt_is_male.getText().toString().trim().equalsIgnoreCase("Nam")) {
                listContactFrag.get(mPosition).setMale((true));
                sendToMain(listContactFrag);
            } else if (edt_is_male.getText().toString().trim().equalsIgnoreCase("Nữ")) {
                listContactFrag.get(mPosition).setMale((false));
                sendToMain(listContactFrag);
            } else {
                Toast.makeText(mMainActivity, "Bạn nhập sai giới tính!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void sendToMain(ArrayList<Contact> listContactFrag) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("listContactFrag", listContactFrag);
        intent.putExtra("bundle_listContactFrag",bundle);
        startActivity(intent);
    }

    private void setWiget(View view) {
        edt_name_frag_setting = view.findViewById(R.id.edt_name_frag_setting);
        edt_phoneNumber_frag_setting = view.findViewById(R.id.edt_phoneNumber_frag_setting);
        edt_is_male = view.findViewById(R.id.edt_is_male);
        btn_update = view.findViewById(R.id.btn_update);
        btn_delete = view.findViewById(R.id.btn_delete);
    }

    private void recieveDataFromMainActivity() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            dataPhoneNumber = bundle.getString("data_phone_number");
            dataName = bundle.getString("data_name");
            dataIsMale = bundle.getBoolean("data_is_male");
            mPosition = bundle.getInt("position");
        }
    }
}