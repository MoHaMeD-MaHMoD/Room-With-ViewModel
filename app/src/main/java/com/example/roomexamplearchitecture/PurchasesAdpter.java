package com.example.roomexamplearchitecture;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PurchasesAdpter extends RecyclerView.Adapter<PurchasesAdpter.PurchaseHolder> {
    List<Purchases> purchasesList = new ArrayList<>();


    @NonNull
    @Override
    public PurchaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.purchase_item, parent, false);

        return new PurchaseHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseHolder holder, int position) {
        Purchases current_purchases = purchasesList.get(position);
        holder.productView.setText(current_purchases.getProduct());
        holder.priceview.setText(String.valueOf(current_purchases.getPrice()));
        holder.dateView.setText(current_purchases.getDate());
    }



    @Override
    public int getItemCount() {
        return purchasesList.size();
    }

    public void set_Purchase(List<Purchases> purchase) {
        this.purchasesList = purchase;
        notifyDataSetChanged();

    }

    public Purchases getPurchaseAt(int position){
        return purchasesList.get(position);
    }

    class PurchaseHolder extends RecyclerView.ViewHolder {
        private TextView productView;
        private TextView priceview;
        private TextView dateView;


        public PurchaseHolder(View itemView) {
            super(itemView);
            productView = itemView.findViewById(R.id.product);
            priceview = itemView.findViewById(R.id.price);
            dateView = itemView.findViewById(R.id.date);


        }
    }

}
