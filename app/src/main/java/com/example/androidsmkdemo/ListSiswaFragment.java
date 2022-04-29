package com.example.androidsmkdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ListSiswaFragment extends Fragment {
    private DBHelper mydb;
    //inisialisasi variable SiswaAdapater dan lisSiswa
    private SiswaAdapter siswaAdapter;
    private ListView listSiswa;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_siswa_fragment,
                container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //inisialisasi obyek DBHelper
        mydb = new DBHelper(getActivity());
        //mendapatkan semua data siswa dari database
        ArrayList arrayList = mydb.getAllSiswa();
        listSiswa = view.findViewById(R.id.list_siswa);

        //memberikan data-data siswa ke
        // adapter ListView siswa untuk dapat ditampilkan
        siswaAdapter = new SiswaAdapter(getActivity(),0, arrayList);
        listSiswa.setAdapter(siswaAdapter);
    }

    //method untuk meng-refresh ArrayList
    // bila ada perubahan data pada database
    public void refreshList() {
        Log.d(ListSiswaFragment.class.getName(), "refreshList: clicked");
        ArrayList array_list = mydb.getAllSiswa();
        siswaAdapter.clear();
        siswaAdapter.addAll(array_list);
        siswaAdapter.notifyDataSetChanged();
    }
}
