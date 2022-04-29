package com.example.androidsmkdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * @author dendy
 * @Date 28/04/2022
 * @Time 09.29
 * Description :
 */
public class DBHelper extends SQLiteOpenHelper {
    //definisi variable nama database
    public static final String DATABASE_NAME = "siswasmk2yk.db";
    //definisi nama tabel untuk menyimpan data siswa
    public static final String SISWA_TABLE_NAME = "daftar_siswa";
    //definisi nama kolom, sesuai dengan data apa saja yang akan disimpan
    //dalam tabel siswa
    public static final String SISWA_COLUMN_ID = "id";
    public static final String SISWA_COLUMN_NAME = "name";

    //konstruktor untuk menginisialisasi pembuatan obyek DBHelper
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //method yang harus di-overide dari class induk
    //digunakan untuk melakukan inisialisasi awal database.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table siswa " +
                        "(id integer primary key, " +
                        "name text) "
        );
    }

    //method yang harus di-overide dari class induk
    //digunakan bila ingin mengupgrade database dengan skema data yang baru
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS siswa");
        onCreate(sqLiteDatabase);
    }

    //method untuk menambah data siswa.
    // data yang diinputkan adalah nama saja, karena property id dari
    //siswa akan ditambahkan incremental secara otomatis
    public void insertSiswa(String name) {
        //mengakses obyek database yang sudah ada sebelumnya
        SQLiteDatabase db = this.getWritableDatabase();
        //mempersiapkan obyek siswa dengan memberikan nilai
        //berdasarkan input dari method insertSiswa
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        //masukan data siswa yang baru ke table siswa pada database
        db.insert("siswa", null, contentValues);
    }

    //method untuk mendapatkan data-data siswa.
    public ArrayList<Siswa> getAllSiswa() {
        //mempersiapkan array dinamis unuk menyimpan data-data siswa
        ArrayList<Siswa> array_list = new ArrayList<>();

        //mengakses obyek database yang sudah ada sebelumnya
        SQLiteDatabase db = this.getReadableDatabase();
        //melakukan query untuk mendapakan semua data siswa
        Cursor res = db.rawQuery("select * from siswa", null);

        //mulai proses pembacaan data baris demi bari.
        // dimulai dari baris pertama
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            //membaca semua data siswa baris demi baris.
            //hasilnya akan disimpan pada obyek siswa
            Siswa siswa = new Siswa(
                    res.getString(res.getColumnIndexOrThrow(SISWA_COLUMN_ID)),
                    res.getString(res.getColumnIndexOrThrow(SISWA_COLUMN_NAME)));
            //obyek siswa disimpan pada array dinamis
            array_list.add(siswa);
            //lanjut ke baris berikutnya
            res.moveToNext();
        }
        //kembalikan array dinamis yang berisi obyek-obyek data siswa
        return array_list;
    }

    public Cursor getDataSiswaById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from siswa where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SISWA_TABLE_NAME);
        return numRows;
    }
}
