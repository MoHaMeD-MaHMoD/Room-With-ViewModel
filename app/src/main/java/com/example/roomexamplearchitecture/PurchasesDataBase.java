package com.example.roomexamplearchitecture;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


import androidx.annotation.*;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import static android.widget.Toast.LENGTH_LONG;


@Database(entities = {Purchases.class}, version = 1)
public abstract class PurchasesDataBase extends RoomDatabase {
    private static PurchasesDataBase instance;

    public abstract PurchasesDao purchasesDao();

    public static synchronized PurchasesDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PurchasesDataBase.class, "hhhh")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    };
    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(instance).execute();
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {

        private PurchasesDao purchasesDao;

        public PopulateAsyncTask(PurchasesDataBase dp) {
            purchasesDao = dp.purchasesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            purchasesDao.insert(new Purchases("shelo", "20", "20-6-2018"));
            purchasesDao.insert(new Purchases("shekobon", "30", "20-6-2018"));
            purchasesDao.insert(new Purchases("temis", "50", "21-6-2018"));

            return null;
        }
    }

}
