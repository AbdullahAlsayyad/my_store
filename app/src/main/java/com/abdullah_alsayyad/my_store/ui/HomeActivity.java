package com.abdullah_alsayyad.my_store.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abdullah_alsayyad.my_store.R;
import com.abdullah_alsayyad.my_store.ui.customer.CustomersActivity;
import com.abdullah_alsayyad.my_store.ui.order.OrderActivity;
import com.abdullah_alsayyad.my_store.ui.shipment.ShipmentsActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a2_home);
        findViewById(R.id.a2_btn_customers).setOnClickListener(this);
        findViewById(R.id.a2_btn_shipments).setOnClickListener(this);
        findViewById(R.id.a2_btn_orders).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.a2_btn_customers:
                intent = new Intent(HomeActivity.this, CustomersActivity.class);
                break;
            case R.id.a2_btn_shipments:
                intent = new Intent(HomeActivity.this, ShipmentsActivity.class);
                break;
            case R.id.a2_btn_orders:
                intent = new Intent(HomeActivity.this, OrderActivity.class);
                break;
        }
        if (intent != null) startActivity(intent);
    }
}