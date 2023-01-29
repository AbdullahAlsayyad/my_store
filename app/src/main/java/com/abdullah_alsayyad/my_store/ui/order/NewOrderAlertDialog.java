package com.abdullah_alsayyad.my_store.ui.order;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.abdullah_alsayyad.my_store.R;
import com.abdullah_alsayyad.my_store.ui.order.OrderVM;

public abstract class NewOrderAlertDialog implements View.OnClickListener {
    public abstract int onClickCreateOrder(int shipmentId, String customerName);

    private final AlertDialog.Builder builder;
    private final AlertDialog dialog;
    private final View view;
    private final OrderVM orderVM;
    private final Context context;

    public NewOrderAlertDialog(Context context, OrderVM orderVM) {
        this.orderVM = orderVM;
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.d3_new_order, null, false);
        this.builder = new AlertDialog.Builder(context);
        buildView();
        this.dialog = builder.create();
    }

    private void buildView() {
        this.builder.setView(this.view);
        this.view.findViewById(R.id.d3_create).setOnClickListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, orderVM.customersNames());
        ((AutoCompleteTextView) this.view.findViewById(R.id.d3_customer_name)).setAdapter(adapter);
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, orderVM.activeShipments());
        ((Spinner) this.view.findViewById(R.id.d3_shipment_id)).setAdapter(adapter);
    }

    public void show(){
        this.dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() != R.id.d3_create) return;
        int shipmentId = Integer.parseInt(((Spinner) this.view.findViewById(R.id.d3_shipment_id)).getSelectedItem().toString());
        String customerName =  ((AutoCompleteTextView) this.view.findViewById(R.id.d3_customer_name)).getText().toString();
        int result = onClickCreateOrder(shipmentId, customerName);
        Toast.makeText(this.context, String.valueOf(result), Toast.LENGTH_SHORT).show();
        if (result == 0) this.dialog.dismiss();
    }
}
