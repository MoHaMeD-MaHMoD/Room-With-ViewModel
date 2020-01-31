package com.example.roomexamplearchitecture;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class PurchasesViewModel extends AndroidViewModel{
    PurchasesRepository repository;
    LiveData<List<Purchases>> allPurchases;

    public PurchasesViewModel(@NonNull Application application) {
        super(application);
        repository = new PurchasesRepository(application);
        allPurchases = repository.getAllPurchases();
    }

    public void insert(Purchases purchases) {
        repository.insert(purchases);
    }

    public void delete(Purchases purchases) {
        repository.delete(purchases);
    }

    public void update(Purchases purchases) {
        repository.update(purchases);
    }

    public void deleteAllPurchases() {
        repository.deleteAllPurchases();
    }

    public LiveData<List<Purchases>> getAllPurchases() {
        return allPurchases;
    }
}
