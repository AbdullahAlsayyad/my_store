package com.abdullah_alsayyad.my_store.ui.new_order;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.abdullah_alsayyad.my_store.data.Constants;
import com.abdullah_alsayyad.my_store.data.model.Customer;
import com.abdullah_alsayyad.my_store.data.model.Order;
import com.abdullah_alsayyad.my_store.data.model.OrderLine;
import com.abdullah_alsayyad.my_store.data.model.Shipment;
import com.abdullah_alsayyad.my_store.data.repository.CustomersRepo;
import com.abdullah_alsayyad.my_store.data.repository.OrderRepo;
import com.abdullah_alsayyad.my_store.data.repository.ShipmentsRepo;

import java.util.ArrayList;
import java.util.Objects;

public class NewOrderVM extends AndroidViewModel {
    private final MutableLiveData<ArrayList<OrderLine>> orderLinesMutableLiveData;
    private final MutableLiveData<Shipment> shipmentMutableLiveData;
    private final MutableLiveData<Customer> customerMutableLiveData;
    private final MutableLiveData<Order> orderMutableLiveData;
    private final OrderRepo orderRepo;

    public NewOrderVM(@NonNull Application application, int shipmentId, String customerName) {
        super(application);
        this.orderRepo = new OrderRepo(application);
        this.orderLinesMutableLiveData = new MutableLiveData<>();
        this.shipmentMutableLiveData = new MutableLiveData<>();
        this.customerMutableLiveData = new MutableLiveData<>();
        this.orderMutableLiveData = new MutableLiveData<>();
        this.orderLinesMutableLiveData.setValue(new ArrayList<>());
        getData(application, shipmentId, customerName);
    }

    public MutableLiveData<ArrayList<OrderLine>> getOrderLinesMutableLiveData() {
        return orderLinesMutableLiveData;
    }

    public MutableLiveData<Shipment> getShipmentMutableLiveData() {
        return shipmentMutableLiveData;
    }

    public MutableLiveData<Customer> getCustomerMutableLiveData() {
        return customerMutableLiveData;
    }

    public MutableLiveData<Order> getOrderMutableLiveData() {
        return orderMutableLiveData;
    }

    private void getData(Context context, int shipmentId, String customerName) {
        ShipmentsRepo shipmentsRepo = new ShipmentsRepo(context);
        Shipment shipment = shipmentsRepo.getShipment(shipmentId);
        shipmentsRepo = null;
        this.shipmentMutableLiveData.setValue(shipment);

        CustomersRepo customersRepo = new CustomersRepo(context);
        Customer customer = customersRepo.getCustomer(customerName);
        customersRepo = null;
        this.customerMutableLiveData.setValue(customer);

        this.orderMutableLiveData.setValue(new Order(-1, customer.customerId, shipment.shipmentId, 0.0, 0.0, false, ""));
    }

    /**
     * For add new order line
     * @param productName product name
     * @param productNumber product number
     * @param units units
     * @param unitPrice unit price
     * @param note note
     * @return
     *          if any parameter is empty return = {@link Constants#RESULT_EMPTY}<br/>
     *          if units or unitPrice equal zero return = {@link Constants#RESULT_ZERO}<br/>
     *          if is already exist return = {@link Constants#RESULT_IS_EXIST}<br/>
     *          if added return = zero
     */
    public int newOrderLine(String productName, String productNumber, String units, String unitPrice, String note) {
        if (productName.isEmpty() || productNumber.isEmpty() || units.isEmpty() || unitPrice.isEmpty())
            return Constants.RESULT_EMPTY;

        final String _productName = productName.trim();
        final String _productNumber = productNumber.trim();
        final int _units = Integer.parseInt(units);
        final double _unitPrice = Double.parseDouble(unitPrice);
        final String _note = note.trim();
        if (_units == 0 || _unitPrice == 0.0) return Constants.RESULT_ZERO;

        int result = checkProductIsExist(_productName, _productNumber);
        if (result != 0) return result;
        ArrayList<OrderLine> orderLines = this.orderLinesMutableLiveData.getValue();
        assert orderLines != null;
        OrderLine orderLine = new OrderLine(-1, Objects.requireNonNull(this.orderMutableLiveData.getValue()).orderId, _productName, _productNumber, _unitPrice, _units, false, _note);
        orderLines.add(orderLine);
        this.orderLinesMutableLiveData.setValue(orderLines);

        Order order = this.orderMutableLiveData.getValue();
        updateOrderDetails(order.total+orderLine.unitPrice*orderLine.units, order.paid, order.note);

        return 0;
    }

    /**
     * When add new order line check if line is already exist
     * @param productName product name
     * @param productNumber product number
     * @return if is already exist return = {@link Constants#RESULT_IS_EXIST}<br/>
     *          else return = zero
     */
    private int checkProductIsExist(String productName, String productNumber) {
        for (OrderLine o :
                Objects.requireNonNull(this.orderLinesMutableLiveData.getValue())) {
            if (productName.equals(o.productName) || productNumber.equals(o.productNumber))
                return Constants.RESULT_IS_EXIST;
        }
        return 0;
    }

    /**
     * Update order details
     * @param total new total
     * @param paid new paid
     * @param note new note
     */
    private void updateOrderDetails(double total, double paid, String note) {
        Order order = this.orderMutableLiveData.getValue();
        assert order != null;
        this.orderMutableLiveData.setValue(new Order(order.orderId, order.customerId, order.shipmentId, total, paid, false, note));
    }

    /**
     * When done add orderLiens this methode for save it
     * @return
     *          if not added order line return = {@link Constants#RESULT_EMPTY}<br/>
     *          if can't add to shipment because is more than maximum return = {@link Constants#RESULT_SHIPMENT_FULL}<br/>
     *          if happened another error return = {@link Constants#RESULT_ERROR}<br/>
     *          if added return = positive Integer
     */
    public int saveOrder() {
        int result = checkOrder();
        if (result != 0) return result;
        Order order = this.orderMutableLiveData.getValue();
        ArrayList<OrderLine> orderLines = this.orderLinesMutableLiveData.getValue();
        Shipment oldShipment = this.shipmentMutableLiveData.getValue();
        assert order != null && orderLines != null && oldShipment != null;
        Shipment shipment = new Shipment(oldShipment.shipmentId, oldShipment.maximum, oldShipment.minimum, oldShipment.status, oldShipment.note, oldShipment.totalAdded+order.total);
        return this.orderRepo.newOrder(order, orderLines, shipment);
    }

    /**
     * Check Order is empty or shipment is full
     * @return
     *          if not added order line return = {@link Constants#RESULT_EMPTY}<br/>
     *          if can't add to shipment because is more than maximum return = {@link Constants#RESULT_SHIPMENT_FULL}<br/>
     *          else return = 0
     */
    private int checkOrder() {
        if (Objects.requireNonNull(this.orderLinesMutableLiveData.getValue()).isEmpty()) return Constants.RESULT_EMPTY;
        Shipment shipment = this.shipmentMutableLiveData.getValue();
        Order order = this.orderMutableLiveData.getValue();
        assert order != null && shipment != null;
        if (order.total+shipment.totalAdded > shipment.maximum) return Constants.RESULT_SHIPMENT_FULL;
        return 0;
    }

}
