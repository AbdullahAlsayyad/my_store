package com.abdullah_alsayyad.my_store.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.abdullah_alsayyad.my_store.data.model.Customer;
import com.abdullah_alsayyad.my_store.data.model.Order;
import com.abdullah_alsayyad.my_store.data.model.OrderLine;
import com.abdullah_alsayyad.my_store.data.model.Shipment;

public final class DatabaseStructure {
    public static class CustomerTable {
        public static final String TABLE_NAME = "customers";
        public static final String CUSTOMER_ID = "id";
        public static final String NAME = "name";
        public static final String PHONE_NUMBER = "phone_number";
        public static final String NOTE = "note";

        public static String createTable(){
            Customer customer = new Customer(0, "", "", "");
            return "CREATE TABLE " + TABLE_NAME + " ( "
                    + CUSTOMER_ID + " " + customer.customerId.getClass().getSimpleName() + " PRIMARY KEY AUTOINCREMENT" + ", "
                    + NAME + " " + customer.name.getClass().getSimpleName() + ", "
                    + PHONE_NUMBER + " " + customer.phoneNumber.getClass().getSimpleName() + ", "
                    + NOTE + " " + customer.note.getClass().getSimpleName()
                    + " ) ";
        }

        public static ContentValues toContentValues(Customer customer){
            if (customer == null)
                throw new NullPointerException("Can not convert null object 'Customer' to ContentValues");

            ContentValues values = new ContentValues();
            values.put(NAME, customer.name);
            values.put(PHONE_NUMBER, customer.phoneNumber);
            values.put(NOTE, customer.note);
            return values;
        }

        public static Customer toCustomer(Cursor cursor){
            if (cursor == null)
                throw new NullPointerException("Can not convert null object 'Cursor' to Customer");

            int customerId = cursor.getInt(0);
            String name = cursor.getString(1);
            String phoneNumber = cursor.getString(2);
            String note = cursor.getString(3);
            return new Customer(customerId, name, phoneNumber, note);
        }
    }

    public static class ShipmentTable {
        public static final String TABLE_NAME = "shipments";
        public static final String SHIPMENT_ID = "id";
        public static final String MAXIMUM = "maximum";
        public static final String MINIMUM = "minimum";
        public static final String STATUS = "status";
        public static final String NOTE = "note";
        public static final String TOTAL_ADDED = "total_added";

        public static String createTable(){
            Shipment shipment = new Shipment(0, 0.0, 0.0, false, "", 0.0);
            return "CREATE TABLE " + TABLE_NAME + " ( "
                    + SHIPMENT_ID + " " + shipment.shipmentId.getClass().getSimpleName() + " PRIMARY KEY AUTOINCREMENT" + ", "
                    + MAXIMUM + " " + shipment.maximum.getClass().getSimpleName() + ", "
                    + MINIMUM + " " + shipment.minimum.getClass().getSimpleName() + ", "
                    + STATUS + " " + shipment.status.getClass().getSimpleName() + ", "
                    + NOTE + " " + shipment.note.getClass().getSimpleName() + ", "
                    + TOTAL_ADDED + " " + shipment.totalAdded.getClass().getSimpleName()
                    + " ) ";
        }

        public static ContentValues toContentValues(Shipment shipment){
            if (shipment == null)
                throw new NullPointerException("Can not convert null object 'Shipment' to ContentValues");

            ContentValues values = new ContentValues();
            values.put(MAXIMUM, shipment.maximum);
            values.put(MINIMUM, shipment.minimum);
            values.put(STATUS, shipment.status);
            values.put(NOTE, shipment.note);
            values.put(TOTAL_ADDED, shipment.totalAdded);
            return values;
        }

        public static Shipment toShipment(Cursor cursor){
            if (cursor == null)
                throw new NullPointerException("Can not convert null object 'Cursor' to Shipment");

            int shipmentId = cursor.getInt(0);
            double maximum = cursor.getDouble(1);
            double minimum = cursor.getDouble(2);
            boolean status = cursor.getInt(3) > 0;
            String note = cursor.getString(4);
            double totalAdded = cursor.getDouble(5);
            return new Shipment(shipmentId, maximum, minimum, status, note, totalAdded);
        }
    }

    public static class OrderTable {
        public static final String TABLE_NAME = "orders";
        public static final String ORDER_ID = "id";
        public static final String CUSTOMER_ID = "customer_id";
        public static final String SHIPMENT_ID = "shipment_id";
        public static final String TOTAL = "total";
        public static final String PAID = "paid";
        public static final String STATUS = "status";
        public static final String NOTE = "note";

        public static String createTable(){
            Order order = new Order(0, 0, 0, 0.0, 0.0, false, "");
            return "CREATE TABLE " + TABLE_NAME + " ( "
                    + ORDER_ID + " " + order.orderId.getClass().getSimpleName() + " PRIMARY KEY AUTOINCREMENT" + ", "
                    + CUSTOMER_ID + " " + order.customerId.getClass().getSimpleName() + ", "
                    + SHIPMENT_ID + " " + order.shipmentId.getClass().getSimpleName() + ", "
                    + TOTAL + " " + order.total.getClass().getSimpleName() + ", "
                    + PAID + " " + order.paid.getClass().getSimpleName() + ", "
                    + STATUS + " " + order.status.getClass().getSimpleName() + ", "
                    + NOTE + " " + order.note.getClass().getSimpleName()
                    + " ) ";
        }

        public static ContentValues toContentValues(Order order){
            if (order == null)
                throw new NullPointerException("Can not convert null object 'Order' to ContentValues");

            ContentValues values = new ContentValues();
            values.put(CUSTOMER_ID, order.customerId);
            values.put(SHIPMENT_ID, order.shipmentId);
            values.put(TOTAL, order.total);
            values.put(PAID, order.paid);
            values.put(STATUS, order.status);
            values.put(NOTE, order.note);
            return values;
        }

        public static Order toOrder(Cursor cursor){
            if (cursor == null)
                throw new NullPointerException("Can not convert null object 'Cursor' to Order");

            int orderId = cursor.getInt(0);
            int customerId = cursor.getInt(1);
            int shipmentId = cursor.getInt(2);
            double total = cursor.getDouble(3);
            double paid = cursor.getDouble(4);
            boolean status = cursor.getInt(5) != 0;
            String note = cursor.getString(6);
            return new  Order(orderId, customerId, shipmentId, total, paid, status, note);
        }
    }

    public static class OrderLineTable {
        public static final String TABLE_NAME = "order_lines";
        public static final String ORDER_LINE_ID = "id";
        public static final String ORDER_ID = "order_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String PRODUCT_NUMBER = "product_number";
        public static final String UNIT_PRICE = "unit_price";
        public static final String UNITS = "units";
        public static final String STATUS = "status";
        public static final String NOTE = "note";

        public static String createTable(){
            OrderLine orderLine = new OrderLine(0, 0, "", "", 0.0, 0, false, "");
            return "CREATE TABLE " + TABLE_NAME + " ( "
                    + ORDER_LINE_ID + " " + orderLine.orderLineId.getClass().getSimpleName() + " PRIMARY KEY AUTOINCREMENT" + ", "
                    + ORDER_ID + " " + orderLine.orderId.getClass().getSimpleName() + ", "
                    + PRODUCT_NAME + " " + orderLine.productName.getClass().getSimpleName() + ", "
                    + PRODUCT_NUMBER + " " + orderLine.productNumber.getClass().getSimpleName() + ", "
                    + UNIT_PRICE + " " + orderLine.unitPrice.getClass().getSimpleName() + ", "
                    + UNITS + " " + orderLine.units.getClass().getSimpleName() + ", "
                    + STATUS + " " + orderLine.status.getClass().getSimpleName() + ", "
                    + NOTE + " " + orderLine.note.getClass().getSimpleName()
                    + " ) ";
        }

        public static ContentValues toContentValues(OrderLine orderLine){
            if (orderLine == null)
                throw new NullPointerException("Can not convert null object 'OrderLine' to ContentValues");

            ContentValues values = new ContentValues();
            values.put(ORDER_ID, orderLine.orderId);
            values.put(PRODUCT_NAME, orderLine.productName);
            values.put(PRODUCT_NUMBER, orderLine.productNumber);
            values.put(UNIT_PRICE, orderLine.unitPrice);
            values.put(UNITS, orderLine.units);
            values.put(STATUS, orderLine.status);
            values.put(NOTE, orderLine.note);
            return values;
        }

        public static OrderLine toOrderLine(Cursor cursor){
            if (cursor == null)
                throw new NullPointerException("Can not convert null object 'Cursor' to OrderLine");

            int orderLineId = cursor.getInt(0);
            int orderId = cursor.getInt(1);
            String productName = cursor.getString(2);
            String productNumber = cursor.getString(3);
            double unitPrice = cursor.getDouble(4);
            int units = cursor.getInt(5);
            boolean status = cursor.getInt(6) != 0;
            String note = cursor.getString(7);
            return new  OrderLine(orderLineId, orderId, productName, productNumber, unitPrice, units, status, note);
        }
    }
}