package com.tqa.btdanhba;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tqa.btdanhba.model.Contact;


public class FragmentAdd extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //    public ArrayList<Contact> arrayContact;
//    private MainActivity mMainActivity;
    private EditText edt_name;
    private EditText edt_phoneNumber;
    private RadioButton radioButton_male;
    private RadioButton radioButton_female;
    private Button btn_addPhoneNumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        setWiget(view);

        btn_addPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name.getText().toString().trim();
                String phoneNumber = edt_phoneNumber.getText().toString().trim();
                boolean isMale = true;

                //kiem tra thong tin da nhap chua
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ tên và số điện thoại!", Toast.LENGTH_SHORT).show();
                } else {
                    //kiem tra gioi tinh
                    if (radioButton_male.isChecked()) {
                        isMale = true;
                    } else if (radioButton_female.isChecked()) {
                        isMale = false;
                    } else {
                        Toast.makeText(getActivity(), "Vui lòng chọn giới tính!", Toast.LENGTH_SHORT).show();
                    }
                    if (radioButton_male.isChecked() || radioButton_female.isChecked()) {
                        Contact contact = new Contact(isMale, name, phoneNumber);
//                    Intent intent = new Intent(getActivity(), MainActivity.class);
//                    intent.putExtra("obj_contact", contact);
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("obj_contact", contact);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            }
        });
        return view;
    }

    public void setWiget(View view) {
        edt_name = view.findViewById(R.id.edt_name);
        edt_phoneNumber = view.findViewById(R.id.edt_phoneNumber);
        radioButton_male = view.findViewById(R.id.radioButton_male);
        radioButton_female = view.findViewById(R.id.radioButton_female);
        btn_addPhoneNumber = view.findViewById(R.id.btn_addPhoneNumber);

    }
}
