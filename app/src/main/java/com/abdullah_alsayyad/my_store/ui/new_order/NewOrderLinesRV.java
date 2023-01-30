package com.abdullah_alsayyad.my_store.ui.new_order;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdullah_alsayyad.my_store.R;
import com.abdullah_alsayyad.my_store.data.model.OrderLine;

import java.util.ArrayList;

public class NewOrderLinesRV extends RecyclerView.Adapter<NewOrderLinesRV.ViewHolder> {
    private ArrayList<OrderLine> orderLines = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i4_rv_order_line, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setProductNumberText(orderLines.get(position).productNumber);
        holder.setUnitsText(orderLines.get(position).units.toString());
    }

    @Override
    public int getItemCount() {
        return this.orderLines.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tVProductNumber, tVUnits;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tVProductNumber = itemView.findViewById(R.id.i4_tv_product_number);
            this.tVUnits = itemView.findViewById(R.id.i4_tv_units);
        }

        public void setProductNumberText(String productNumber) {
            this.tVProductNumber.setText(productNumber);
        }

        public void setUnitsText(String units) {
            this.tVUnits.setText(units);
        }
    }

}
