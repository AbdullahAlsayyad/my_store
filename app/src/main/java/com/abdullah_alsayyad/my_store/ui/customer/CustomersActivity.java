package com.abdullah_alsayyad.my_store.ui.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.abdullah_alsayyad.my_store.R;
import com.abdullah_alsayyad.my_store.databinding.A3RvBinding;

public class CustomersActivity extends AppCompatActivity implements View.OnClickListener {
    CustomersVM customersVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        A3RvBinding binding = DataBindingUtil.setContentView(this, R.layout.a3_rv);

        CustomerRV adapter = new CustomerRV();

        customersVM = new ViewModelProvider(this).get(CustomersVM.class);
        customersVM.getCustomers().observe(this, adapter::setCustomers);

        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(adapter);

        binding.addNewItemRv.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_new_item_rv:
                newCustomer();
                break;
        }
    }

    private void newCustomer(){
        NewCustomerAlertDialog customerAlertDialog = new NewCustomerAlertDialog(this) {
            @Override
            public int onClickCreateCustomer(String name, String phoneNumber, String note) {
                return customersVM.newCustomer(name, phoneNumber, note);
            }
        };
        customerAlertDialog.show();
    }
}