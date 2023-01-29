package com.abdullah_alsayyad.my_store.ui.shipment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdullah_alsayyad.my_store.R;
import com.abdullah_alsayyad.my_store.data.model.Shipment;

import java.util.ArrayList;

public class ShipmentRV extends RecyclerView.Adapter<ShipmentRV.ViewHolder> {

    private ArrayList<Shipment> shipments = new ArrayList<>();

    public void setShipments(ArrayList<Shipment> shipments) {
        this.shipments = shipments;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i2_rv_shipment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tVid.setText(String.valueOf(this.shipments.get(position).shipmentId));
        holder.tVstatus.setText(String.valueOf(this.shipments.get(position).status));
    }

    @Override
    public int getItemCount() {
        return this.shipments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tVid, tVstatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tVid = itemView.findViewById(R.id.i2_shipment_id);
            tVstatus = itemView.findViewById(R.id.i2_shipment_status);
        }
    }
}
