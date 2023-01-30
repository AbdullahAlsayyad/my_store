package com.abdullah_alsayyad.my_store.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.abdullah_alsayyad.my_store.R;
import com.abdullah_alsayyad.my_store.databinding.A3RvBinding;
import com.abdullah_alsayyad.my_store.ui.new_order.NewOrderActivity;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CODE_FROM_NEW_ORDER_ACTIVITY = 727;
    private OrderVM orderVM;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        A3RvBinding binding = DataBindingUtil.setContentView(this, R.layout.a3_rv);
        binding.addNewItemRv.setOnClickListener(this);

        OrderRV adapter = new OrderRV();
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(adapter);

        orderVM = new ViewModelProvider(this).get(OrderVM.class);
        orderVM.getI3_rv_orderMutableLiveData().observe(this, adapter::setOrders);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FROM_NEW_ORDER_ACTIVITY) {
            if (resultCode == RESULT_CANCELED) Toast.makeText(this, "CANCELED", Toast.LENGTH_SHORT).show();
            else this.orderVM.refresh();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_new_item_rv:
                newOrder();
                break;
        }
    }

    private void newOrder() {
        NewOrderAlertDialog orderAlertDialog = new NewOrderAlertDialog(this, this.orderVM) {
            @Override
            public int onClickCreateOrder(int shipmentId, String customerName) {
                int result = OrderActivity.this.orderVM.checkNewOrder(shipmentId, customerName);
                if (result != 0) return result;
                Intent intent = new Intent(OrderActivity.this, NewOrderActivity.class);
                intent.putExtra("shipmentId", shipmentId)
                        .putExtra("customerName", customerName);
                OrderActivity.this.startActivityForResult(intent, REQUEST_CODE_FROM_NEW_ORDER_ACTIVITY);
                return 0;
            }
        };
        orderAlertDialog.show();
    }
}
