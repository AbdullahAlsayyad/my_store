package com.abdullah_alsayyad.my_store.data.repository;

import android.content.Context;
import android.database.Cursor;

import com.abdullah_alsayyad.my_store.data.Database;
import com.abdullah_alsayyad.my_store.data.DatabaseStructure;
import com.abdullah_alsayyad.my_store.data.model.Order;
import com.abdullah_alsayyad.my_store.data.model.OrderLine;
import com.abdullah_alsayyad.my_store.data.model.Shipment;
import com.abdullah_alsayyad.my_store.ui.MainActivity;
import com.abdullah_alsayyad.my_store.ui.order.OrderVM;

import java.util.ArrayList;

public class OrderRepo {
    private static final String TAG = MainActivity.MAIN_TAG+"OrderRepo";
    private final Database db;

    public OrderRepo(Context context) {
        this.db = new Database(context, Order.class);
    }

    public ArrayList<OrderVM.I3_RV_Order> i3_rv_orders(){
        ArrayList<OrderVM.I3_RV_Order> i3_rv_orders = new ArrayList<>();
        String sql = I3_RV_OrderSQLStatement();
        Cursor data = Database.getDataBySQL(this.db, sql);
        data.moveToFirst();
        while (!data.isAfterLast()){
            i3_rv_orders.add(new OrderVM.I3_RV_Order(data.getInt(0), data.getInt(1), data.getString(2), data.getInt(3)!=0));
            data.moveToNext();
        }
        return i3_rv_orders;
    }

    private String I3_RV_OrderSQLStatement(){
        return "SELECT "
                + DatabaseStructure.OrderTable.TABLE_NAME +'.'+ DatabaseStructure.OrderTable.ORDER_ID+ ','
                + DatabaseStructure.CustomerTable.TABLE_NAME +'.'+ DatabaseStructure.CustomerTable.CUSTOMER_ID+ ','
                + DatabaseStructure.CustomerTable.TABLE_NAME +'.'+DatabaseStructure.CustomerTable.NAME+','
                + DatabaseStructure.OrderTable.TABLE_NAME +'.'+DatabaseStructure.OrderTable.STATUS
                + " FROM " + DatabaseStructure.CustomerTable.TABLE_NAME +','+ DatabaseStructure.OrderTable.TABLE_NAME
                + " WHERE "
                + DatabaseStructure.CustomerTable.TABLE_NAME +'.'+ DatabaseStructure.CustomerTable.CUSTOMER_ID
                +'='
                + DatabaseStructure.OrderTable.TABLE_NAME +'.'+DatabaseStructure.OrderTable.CUSTOMER_ID;
    }

    public int newOrder(Order order, ArrayList<OrderLine> orderLines, Shipment shipment) {
        int orderId = (int) this.db.newRow(order);
        if (orderId == -1) return orderId;

        int result = newOrderLiens(orderId, orderLines);
        if (result < 1) {
            deleteOrder(orderId);
            return result;
        }
        result = Database.updateRow(this.db, shipment);
        if (result < 1) deleteOrder(orderId);
        return result;
    }

    private int newOrderLiens(int orderId, ArrayList<OrderLine> orderLines) {
        for (OrderLine ol : orderLines)
            ol.orderId = orderId;

        int result = -1;
        for (OrderLine ol: orderLines)
            result = (int) this.db.newRow(ol);
        return result;
    }

    private void deleteOrder(int orderId) {
        this.db.deleteRow(orderId);
        Database.deleteRow(this.db, DatabaseStructure.OrderLineTable.TABLE_NAME,
                " "+ DatabaseStructure.OrderLineTable.ORDER_ID+" = "+ orderId+" ");
    }
}
