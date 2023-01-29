package com.abdullah_alsayyad.my_store.ui.new_order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abdullah_alsayyad.my_store.R;
import com.abdullah_alsayyad.my_store.databinding.A4NewOrderBinding;

public class NewOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private A4NewOrderBinding binding;
    private NewOrderVM newOrderVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.a4_new_order);
        this.binding.a4BtnAddOrderLine.setOnClickListener(this);
        this.binding.a4BtnSaveOrder.setOnClickListener(this);

        Intent intent = getIntent();
        int shipmentId =  intent.getIntExtra("shipmentId", 0);
        String customerName = intent.getStringExtra("customerName");
        this.newOrderVM = new ViewModelProvider(this, new NewOrderVMF(this.getApplication(), shipmentId, customerName)).get(NewOrderVM.class);
        setData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        setResult(RESULT_CANCELED);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.a4_btn_add_order_line:
                newOrderLine();
                break;
            case R.id.a4_btn_save_order:
                saveOrder();
                break;
        }
    }

    private void saveOrder() {
        int result = this.newOrderVM.saveOrder();
        if (result > -1) {
            setResult(RESULT_OK);
            finish();
        }
        else Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
    }

    private void newOrderLine() {
        NewOrderLineAlertDialog orderLineAlertDialog = new NewOrderLineAlertDialog(this) {
            @Override
            public int onCreateOrderLine(String productName, String productNumber, String units, String unitPrice, String note) {
                return NewOrderActivity.this.newOrderVM.newOrderLine(productName, productNumber, units, unitPrice, note);
            }
        };
        orderLineAlertDialog.show();
    }

    private void setData() {
        this.newOrderVM.getCustomerMutableLiveData().observe(this, customer -> binding.a4TvCustomerName.setText(customer.name));
        this.newOrderVM.getShipmentMutableLiveData().observe(this, shipment -> binding.a4TvShipmentId.setText(String.valueOf(shipment.shipmentId)));
        this.newOrderVM.getOrderMutableLiveData().observe(this, order -> binding.a4TvTotal.setText(String.valueOf(order.total)));

        NewOrderLinesRV adapter = new NewOrderLinesRV();
        this.newOrderVM.getOrderLinesMutableLiveData().observe(this, adapter::setOrderLines);

        this.binding.a4RvOrderLines.setLayoutManager(new LinearLayoutManager(this));
        this.binding.a4RvOrderLines.setAdapter(adapter);
    }

//    private void getData() {

//    }
}