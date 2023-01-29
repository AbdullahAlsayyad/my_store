package com.abdullah_alsayyad.my_store.ui.order;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.abdullah_alsayyad.my_store.data.Constants;
import com.abdullah_alsayyad.my_store.data.model.Shipment;
import com.abdullah_alsayyad.my_store.data.repository.CustomersRepo;
import com.abdullah_alsayyad.my_store.data.repository.OrderRepo;
import com.abdullah_alsayyad.my_store.data.repository.ShipmentsRepo;

import java.util.ArrayList;

public class OrderVM extends AndroidViewModel {
    private final MutableLiveData<ArrayList<I3_RV_Order>> i3_rv_orderMutableLiveData =  new MutableLiveData<>();
    private final Context context;
    private final OrderRepo orderRepo;
    public OrderVM(@NonNull Application application) {
        super(application);
        this.context = application;
        this.orderRepo = new OrderRepo(this.context);
        this.i3_rv_orderMutableLiveData.setValue(this.orderRepo.i3_rv_orders());
    }

    public MutableLiveData<ArrayList<I3_RV_Order>> getI3_rv_orderMutableLiveData() {
        return i3_rv_orderMutableLiveData;
    }

    public ArrayList<String> customersNames() {
        CustomersRepo customersRepo = new CustomersRepo(this.context);
        return customersRepo.customersNames();
    }

    public ArrayList<String> activeShipments() {
        ShipmentsRepo shipmentsRepo = new ShipmentsRepo(this.context);
        return shipmentsRepo.activeShipments();
    }

    public int checkNewOrder(int shipmentId, String customerName) {
        int result = checkShipment(shipmentId);
        if (result != 0) return result;
        result = checkCustomer(customerName.trim());
        return result;
    }
    private int checkShipment(int shipmentId) {
        ShipmentsRepo shipmentsRepo = new ShipmentsRepo(this.context);
        Shipment shipment = shipmentsRepo.getShipment(shipmentId);
        if (shipment == null) return Constants.RESULT_SHIPMENT_NOT_EXIST;
        if (shipment.totalAdded.equals(shipment.maximum)) return Constants.RESULT_SHIPMENT_FULL;
        return 0;
    }
    private int checkCustomer(String customerName) {
        for (String name : customersNames())
            if (name.equals(customerName)) return 0;
        return Constants.RESULT_CUSTOMER_NOT_EXIST;
    }

    public void refresh() {
        this.i3_rv_orderMutableLiveData.setValue(this.orderRepo.i3_rv_orders());
    }

    public static class I3_RV_Order {
        public final int orderID;
        public final int customerID;
        public final String customerName;
        public final boolean status;

        public I3_RV_Order(int orderID, int customerID, String customerName, boolean status) {
            this.orderID = orderID;
            this.customerID = customerID;
            this.customerName = customerName;
            this.status = status;
        }
    }
}
