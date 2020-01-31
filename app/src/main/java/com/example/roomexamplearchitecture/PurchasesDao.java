package com.example.roomexamplearchitecture;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;


@Dao
public interface PurchasesDao {
    @Insert
    void insert(Purchases user);

    @Update
    void update(Purchases user);

    @Delete
    void delete(Purchases user);

    @Query("DELETE FROM Purchases_table")
    void deleteAllPurchases();

    @Query("SELECT * FROM purchases_table ORDER BY price DESC")
    LiveData<List<Purchases>> getAllPurchases();

}
