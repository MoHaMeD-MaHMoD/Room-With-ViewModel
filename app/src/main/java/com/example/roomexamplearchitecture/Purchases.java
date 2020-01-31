package com.example.roomexamplearchitecture;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Purchases_table")
public class Purchases {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String product;
    private  String price;
    private String date;


    public Purchases(String product, String price, String date) {
        this.product = product;
        this.price = price;
        this.date = date;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getProduct() {
        return product;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }
}
