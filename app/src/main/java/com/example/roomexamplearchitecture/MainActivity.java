package com.example.roomexamplearchitecture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    public static final int SAVE_PURCHASE_REQUEST = 1;
    private PurchasesViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PurchasesAdpter adpter = new PurchasesAdpter();
        recyclerView.setAdapter(adpter);

        myViewModel = ViewModelProviders.of(this).get(PurchasesViewModel.class);
        myViewModel.getAllPurchases().observe(this, new Observer<List<Purchases>>() {
            @Override
            public void onChanged(@Nullable List<Purchases> purchases) {
                Toast.makeText(MainActivity.this, "dsfdsf", LENGTH_LONG).show();
                adpter.set_Purchase(purchases);
            }
        });
        FloatingActionButton add_button = (FloatingActionButton) findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add_Purchase_Activity.class);
                startActivityForResult(intent, SAVE_PURCHASE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SAVE_PURCHASE_REQUEST && resultCode == RESULT_OK) {
            String product = data.getStringExtra(Add_Purchase_Activity.EXTRA_PRODUCT);
            String price = data.getStringExtra(Add_Purchase_Activity.EXTRA_PRICE);
            String date = data.getStringExtra(Add_Purchase_Activity.EXTRA_DATE);
            Purchases purchases = new Purchases(product, price, date);
            myViewModel.insert(purchases);
            Toast.makeText(MainActivity.this, "Product saved", LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Product not saved", LENGTH_LONG).show();


        }
    }
}
