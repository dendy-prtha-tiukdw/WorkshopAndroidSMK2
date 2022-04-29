package com.example.androidsmkdemo;

public class Siswa {
    private String id;
    private String name;

    public Siswa(String id, String nama){
        this.setId(id);
        this.setName(nama);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
