package com.abdullah_alsayyad.my_store.ui.new_order;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NewOrderVMF implements ViewModelProvider.Factory {
    private Application application;
    private int shipmentId;
    private String customerName;

    public NewOrderVMF(Application application, int shipmentId, String customerName) {
        this.application = application;
        this.shipmentId = shipmentId;
        this.customerName = customerName;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NewOrderVM(this.application, this.shipmentId, this.customerName);
    }
}
