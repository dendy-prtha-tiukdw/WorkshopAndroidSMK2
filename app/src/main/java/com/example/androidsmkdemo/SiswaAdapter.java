package com.example.androidsmkdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SiswaAdapter extends ArrayAdapter<Siswa> {
    public SiswaAdapter(@NonNull Context context,
                        int resource,
                        @NonNull ArrayList<Siswa> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Siswa siswa = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_siswa, parent, false);
        }
        // mendapatkan view id dari layout list_item_siswa
        TextView id = convertView.findViewById(R.id.id);
        TextView nama = convertView.findViewById(R.id.nama);
        // membuat dan menambahkan data ke view menggunakan obyek siswa
        id.setText(siswa.getId());
        nama.setText(siswa.getName());
        return convertView;
    }
}
