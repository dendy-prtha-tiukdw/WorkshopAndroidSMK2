package com.example.androidsmkdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class InsertSiswaFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.insert_fragment,
                container, false);
        return view;
    }

    //inisialisasi variable DBHelper, Button dan EditTest
    private DBHelper mydb;
    Button btnSave;
    EditText editTextNama;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //inisialisasi obyek DBHelper, tombol simpan,
        // dan text field input nama
        mydb = new DBHelper(getActivity());
        btnSave = view.findViewById(R.id.btn_save);
        editTextNama = view.findViewById(R.id.etext_nama);
        //mengeset aksi click jika tombol simpan ditekan
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //menginputkan data siswa dengan nama yang diinputkan pada database
//                mydb.insertSiswa(editTextNama.getText().toString());
                //karena terdapat data baru pada database, maka
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String id = db.collection("siswa").document().getId();
                Map<String, Object> siswa = new HashMap<>();
                siswa.put("id", id);
                siswa.put("nama", editTextNama.getText().toString());

                db.collection("siswa")
                        .add(siswa)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("TAG", "onSuccess input data siswa: " + id);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("TAG", "onFailure input data siswa " + e.getMessage());
                            }
                        });

                //melakukan refresh daftar siswa pada fragment ListSiswaFragment
                FragmentManager fm = getActivity().getSupportFragmentManager();
                ListSiswaFragment fragm = (ListSiswaFragment) fm.findFragmentById(R.id.fragment_list_siswa);
                fragm.refreshList();
            }
        });
    }
}
