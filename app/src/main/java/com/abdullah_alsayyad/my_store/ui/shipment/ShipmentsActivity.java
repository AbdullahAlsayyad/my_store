package com.abdullah_alsayyad.my_store.ui.shipment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.abdullah_alsayyad.my_store.R;

import com.abdullah_alsayyad.my_store.databinding.A3RvBinding;

public class ShipmentsActivity extends AppCompatActivity implements View.OnClickListener {
    private ShipmentVM shipmentVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        A3RvBinding binding = DataBindingUtil.setContentView(this, R.layout.a3_rv);

        shipmentVM = new ViewModelProvider(this).get(ShipmentVM.class);
        ShipmentRV adapter = new ShipmentRV();
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(adapter);

        shipmentVM.getShipments().observe(this, adapter::setShipments);

        binding.addNewItemRv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_new_item_rv:
                newShipment();
                break;
        }
    }

    private void newShipment(){
        NewShipmentAlertDialog shipmentAlertDialog = new NewShipmentAlertDialog(this) {
            @Override
            public int onClickCreateShipment(String maximum, String minimum, String note) {
                return shipmentVM.newShipment(maximum, minimum, note);
            }
        };

        shipmentAlertDialog.show();
    }
}