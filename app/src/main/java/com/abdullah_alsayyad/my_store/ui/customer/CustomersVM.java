package com.abdullah_alsayyad.my_store.ui.customer;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import com.abdullah_alsayyad.my_store.data.Constants;
import com.abdullah_alsayyad.my_store.data.model.Customer;
import com.abdullah_alsayyad.my_store.data.repository.CustomersRepo;

public class CustomersVM extends AndroidViewModel {
    private final CustomersRepo customersRepo;
    private final MutableLiveData<ArrayList<Customer>> customersMutableLiveData;

    public CustomersVM(Application application) {
        super(application);
        customersRepo = new CustomersRepo(application);
        customersMutableLiveData = new MutableLiveData<>();
        customersMutableLiveData.setValue(customersRepo.getCustomers());
    }

    public MutableLiveData<ArrayList<Customer>> getCustomers() {
        return customersMutableLiveData;
    }

    public int newCustomer(String name, String phoneNumber, String note){
        int result = checkAndAddCustomer(name.trim(), phoneNumber.trim(), note.trim());
        if (result != Constants.RESULT_ERROR) customersMutableLiveData.setValue(customersRepo.getCustomers());

        return (int) result;
    }

    private int checkAndAddCustomer(String name, String phone, String note){
        if (name.isEmpty() || phone.isEmpty()) return Constants.RESULT_EMPTY;

        for (Customer c : customersMutableLiveData.getValue()) {
            if (c.name.equals(name)) return Constants.RESULT_NAME_IS_EXIST;
            if (c.phoneNumber.equals(phone)) return Constants.RESULT_PHONE_IS_EXIST;
        }

        long result = customersRepo.newCustomer(new Customer(0, name, phone, note));
        return (int) result;
    }
}
