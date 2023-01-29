package com.abdullah_alsayyad.my_store.ui.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdullah_alsayyad.my_store.R;

import java.util.ArrayList;

public class OrderRV extends RecyclerView.Adapter<OrderRV.ViewHolder> {
    private ArrayList<OrderVM.I3_RV_Order> orders = new ArrayList<>();

    public void setOrders(ArrayList<OrderVM.I3_RV_Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i3_rv_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setCustomerNameText(orders.get(position).customerName);
        holder.setOrderIdText(String.valueOf(orders.get(position).orderID));
        holder.setStatusText(String.valueOf(orders.get(position).status));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tVCustomerName, tVOrderId, tVStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tVOrderId = itemView.findViewById(R.id.i3_order_id);
            this.tVCustomerName = itemView.findViewById(R.id.i3_customer_name);
            this.tVStatus = itemView.findViewById(R.id.i3_order_status);
        }

        public void setCustomerNameText(String customerName) {
            this.tVCustomerName.setText(customerName);
        }
        public void setOrderIdText(String orderId) {
            this.tVOrderId.setText(orderId);
        }
        public void setStatusText(String status) {
            this.tVStatus.setText(status);
        }
    }
}
