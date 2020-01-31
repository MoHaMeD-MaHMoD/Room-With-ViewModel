package com.example.roomexamplearchitecture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class Add_Purchase_Activity extends AppCompatActivity {
    public static final String EXTRA_PRODUCT = "com.example.roomexamplearchitecture.EXTRA_PRODUCT";
    public static final String EXTRA_PRICE = "com.example.roomexamplearchitecture.EXTRA_PRICE";
    public static final String EXTRA_DATE = "com.example.roomexamplearchitecture.EXTRA_DATE";

    EditText editTextProduct;
    EditText editTextPrice;
    NumberPicker numberPickerMonth;
    NumberPicker numberPickerDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__purchase);
        editTextProduct = (EditText) findViewById(R.id.editText_Product);
        editTextPrice = (EditText) findViewById(R.id.editText_price);
        numberPickerMonth = (NumberPicker) findViewById(R.id.numberpicker_month);
        numberPickerMonth.setMinValue(1);
        numberPickerMonth.setMaxValue(12);
        numberPickerDay = (NumberPicker) findViewById(R.id.numberpicker_Day);
        numberPickerDay.setMinValue(1);
        numberPickerDay.setMaxValue(31);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Product");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_purchase_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_purchase:
                savePurchase();
            default:
                return super.onOptionsItemSelected(item);

        }


    }

    private void savePurchase() {
        String product = editTextProduct.getText().toString();
        String price = editTextPrice.getText().toString();
        int month = numberPickerMonth.getValue();
        int day = numberPickerDay.getValue();
        String date = month + "-" + day;
        if (product.trim().isEmpty() || price.trim().isEmpty()) {
            Toast.makeText(this, "PLEASE INSERT DATA TO SAVE IT ", Toast.LENGTH_LONG).show();
        } else {
            Intent data = new Intent(this, MainActivity.class);
            data.putExtra(EXTRA_PRODUCT, product);
            data.putExtra(EXTRA_PRICE, price);
            data.putExtra(EXTRA_DATE, date);
            setResult(RESULT_OK, data);
            finish();
        }
    }

}
