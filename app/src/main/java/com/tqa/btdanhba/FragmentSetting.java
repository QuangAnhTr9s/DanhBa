package com.tqa.btdanhba;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentSetting extends Fragment {
    private EditText edt_name_frag_setting, edt_phoneNumber_frag_setting, edt_is_male;
    private Button btn_delete, btn_update;
    private String dataPhoneNumber, dataName;
    private boolean dataIsMale = true;
    private int mPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        setWiget(view);

        recieveDataFromMainActivity();
        edt_phoneNumber_frag_setting.setText(dataPhoneNumber);
        edt_name_frag_setting.setText(dataName);
        if (dataIsMale) {
            edt_is_male.setText("Nam");
        } else {
            edt_is_male.setText("Nữ");
        }
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("position_choose", mPosition);
                startActivity(intent);
            }
        });
        return view;
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