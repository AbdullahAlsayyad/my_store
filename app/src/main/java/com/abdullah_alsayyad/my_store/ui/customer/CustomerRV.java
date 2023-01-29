package com.abdullah_alsayyad.my_store.ui.customer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdullah_alsayyad.my_store.R;
import com.abdullah_alsayyad.my_store.data.model.Customer;

import java.util.ArrayList;

public class CustomerRV extends RecyclerView.Adapter<CustomerRV.ViewHolder>{

    private ArrayList<Customer> customers = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i1_rv_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tVid.setText(String.valueOf(customers.get(position).customerId));
        holder.tVname.setText(customers.get(position).name);
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tVid, tVname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tVid = itemView.findViewById(R.id.i1_customer_id);
            tVname = itemView.findViewById(R.id.i1_customer_name);
        }
    }
}
