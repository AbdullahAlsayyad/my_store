package com.abdullah_alsayyad.my_store.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.abdullah_alsayyad.my_store.R;
import com.abdullah_alsayyad.my_store.data.Database;
import com.abdullah_alsayyad.my_store.data.model.Customer;
import com.abdullah_alsayyad.my_store.data.repository.CustomersRepo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String MAIN_TAG = "alsayyad.my_store.";
    private static final String TAG = MAIN_TAG+"MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomersRepo customersRepo = new CustomersRepo(this);
        ArrayList<Customer> customers = customersRepo.getCustomers();
        Log.d(TAG, "onCreate: ID=" + customersRepo.newCustomer(new Customer(0, "A", "a", "aaa")));
    }
}