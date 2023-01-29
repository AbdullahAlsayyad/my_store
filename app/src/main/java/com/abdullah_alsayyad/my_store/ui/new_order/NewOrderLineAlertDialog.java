package com.abdullah_alsayyad.my_store.ui.new_order;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.abdullah_alsayyad.my_store.R;

public abstract class NewOrderLineAlertDialog implements View.OnClickListener {
    public abstract int onCreateOrderLine(String productName, String productNumber, String units, String unitPrice, String note);


    private final AlertDialog.Builder builder;
    private final AlertDialog dialog;
    private final View view;

    public NewOrderLineAlertDialog(Context context) {
        this.builder = new AlertDialog.Builder(context);
        this.view = LayoutInflater.from(context).inflate(R.layout.d4_new_order_line, null, false);
        buildView();
        this.dialog = builder.create();
    }

    public void show() {
        this.dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() != R.id.d4_btn_add) return;

        final String productName = ( (EditText) view.findViewById(R.id.d4_et_product_name)).getText().toString();
        final String productNumber = ( (EditText) view.findViewById(R.id.d4_et_product_number)).getText().toString();
        final String units = ( (EditText) view.findViewById(R.id.d4_et_units)).getText().toString();
        final String unisPrice = ( (EditText) view.findViewById(R.id.d4_et_unit_price)).getText().toString();
        final String note = ( (EditText) view.findViewById(R.id.d4_et_note)).getText().toString();
        int result = onCreateOrderLine(productName, productNumber, units, unisPrice, note);
        if (result > -1) this.dialog.dismiss();
    }

    private void buildView() {
        builder.setView(this.view);
        view.findViewById(R.id.d4_btn_add).setOnClickListener(this);
    }
}
