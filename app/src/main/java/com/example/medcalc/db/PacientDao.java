package com.example.medcalc.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PacientDao {


    @Query("SELECT * FROM pacient")
    List<Pacient> getAll();

    @Query("SELECT * FROM pacient WHERE name LIKE :search")
    List<Pacient> getWithName(String search);

    @Query("SELECT * FROM pacient WHERE id = :id")
    Pacient getById(long id);

    @Insert
    void insert(Pacient pacient);

    @Update
    void update(Pacient pacient);

    @Delete
    void delete(Pacient pacient);



}
