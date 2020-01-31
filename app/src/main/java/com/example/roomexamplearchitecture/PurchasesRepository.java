package com.example.roomexamplearchitecture;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PurchasesRepository {

    private PurchasesDao purchasesDao;
    private LiveData<List<Purchases>> allPurchases;

    public PurchasesRepository(Application application) {

        PurchasesDataBase dataBase = PurchasesDataBase.getInstance(application);
        purchasesDao = dataBase.purchasesDao();
        allPurchases = purchasesDao.getAllPurchases();
    }

    public void insert(Purchases purchases) {
        new insertAsyncTask(purchasesDao).execute(purchases);
    }

    public void update(Purchases purchases) {
        new updateAsyncTask(purchasesDao).execute(purchases);
    }

    public void delete(Purchases purchases) {
        new deleteAsyncTask(purchasesDao).execute(purchases);
    }

    public void deleteAllPurchases() {
        new deleteAllPurchasesAsyncTask(purchasesDao).execute();

    }

    public LiveData<List<Purchases>> getAllPurchases() {
        return allPurchases;
    }

    private static class insertAsyncTask extends AsyncTask<Purchases, Void, Void> {
        private PurchasesDao purchasesDao;

        public insertAsyncTask(PurchasesDao purchasesDao) {
            this.purchasesDao = purchasesDao;
        }

        @Override
        protected Void doInBackground(Purchases... purchases) {
            purchasesDao.insert(purchases[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Purchases, Void, Void> {
        private PurchasesDao purchasesDao;

        public updateAsyncTask(PurchasesDao purchasesDao) {
            this.purchasesDao = purchasesDao;
        }

        @Override
        protected Void doInBackground(Purchases... purchases) {
            purchasesDao.update(purchases[0]);
            return null;
        }
    }
    private static class deleteAsyncTask extends AsyncTask<Purchases, Void, Void> {
        private PurchasesDao purchasesDao;

        public deleteAsyncTask(PurchasesDao purchasesDao) {
            this.purchasesDao = purchasesDao;
        }

        @Override
        protected Void doInBackground(Purchases... purchases) {
            purchasesDao.delete(purchases[0]);
            return null;
        }
    }
    private static class deleteAllPurchasesAsyncTask extends AsyncTask<Purchases, Void, Void> {
        private PurchasesDao purchasesDao;

        public deleteAllPurchasesAsyncTask(PurchasesDao purchasesDao) {
            this.purchasesDao = purchasesDao;
        }

        @Override
        protected Void doInBackground(Purchases... purchases) {
            purchasesDao.deleteAllPurchases();
            return null;
        }
    }
}
