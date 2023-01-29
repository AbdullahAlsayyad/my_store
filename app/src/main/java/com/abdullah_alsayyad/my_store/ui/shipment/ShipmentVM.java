package com.abdullah_alsayyad.my_store.ui.shipment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.abdullah_alsayyad.my_store.data.Constants;
import com.abdullah_alsayyad.my_store.data.model.Shipment;
import com.abdullah_alsayyad.my_store.data.repository.ShipmentsRepo;

import java.util.ArrayList;

public class ShipmentVM extends AndroidViewModel {
    private final ShipmentsRepo shipmentsRepo;
    private final MutableLiveData<ArrayList<Shipment>> shipmentsMutableLiveData;

    public ShipmentVM(@NonNull Application application) {
        super(application);
        this.shipmentsRepo = new ShipmentsRepo(application);
        this.shipmentsMutableLiveData = new MutableLiveData<>();
        this.shipmentsMutableLiveData.setValue(this.shipmentsRepo.getShipments());
    }

    public MutableLiveData<ArrayList<Shipment>> getShipments() {
        return shipmentsMutableLiveData;
    }

    public int newShipment(String maximum, String minimum, String note) {
        if (maximum.isEmpty() || minimum.isEmpty()) return Constants.RESULT_EMPTY;
        if (Integer.parseInt(maximum.trim()) == 0) return Constants.RESULT_ZERO;

        int result = (int) this.shipmentsRepo.newShipment(new Shipment(0, Double.parseDouble(maximum), Double.parseDouble(minimum),
                false, note, 0));
        if (result != -1) this.shipmentsMutableLiveData.setValue(this.shipmentsRepo.getShipments());
        return result;
    }

    private int checkData(String maximum, String minimum) {

        return 0;
    }
}
