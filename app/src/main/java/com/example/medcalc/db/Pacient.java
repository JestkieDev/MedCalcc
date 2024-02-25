package com.example.medcalc.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pacient {

    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "age")
    public int age;
    @ColumnInfo(name = "imt")
    public double imt;
    @ColumnInfo(name = "nf")
    public double nf;
    @ColumnInfo(name = "cap")
    public double cap;
    @ColumnInfo(name = "def")
    public double def;

    public Pacient(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
