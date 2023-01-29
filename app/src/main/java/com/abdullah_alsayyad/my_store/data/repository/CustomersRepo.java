package com.abdullah_alsayyad.my_store.data.repository;

import android.content.Context;
import android.database.Cursor;

import com.abdullah_alsayyad.my_store.data.Database;
import com.abdullah_alsayyad.my_store.data.DatabaseStructure;
import com.abdullah_alsayyad.my_store.data.model.Customer;

import java.util.ArrayList;

public class CustomersRepo {
    private final Database db;
    private final ArrayList<Customer> customers;
    public CustomersRepo(Context context) {
        this.db = new Database(context, Customer.class);
        this.customers = new ArrayList<>();
        getData();
    }

    public long newCustomer(Customer customer){
        if (customer == null)
            throw new NullPointerException();
        long result = this.db.newRow(customer);
        if (result != -1) this.customers.add(new Customer((int) result, customer.name, customer.phoneNumber, customer.note));
        return result;
    }

    public ArrayList<Customer> getCustomers(){
        return this.customers;
    }

    public Customer getCustomer(int customerId) {
        Cursor data = this.db.getModel(customerId);
        data.moveToFirst();
        if (data.getCount() == 0) return null;
        return DatabaseStructure.CustomerTable.toCustomer(data);
    }

    public Customer getCustomer(String customerName) {
        String sql = "SELECT * FROM "+ DatabaseStructure.CustomerTable.TABLE_NAME
                + " WHERE " + DatabaseStructure.CustomerTable.NAME + " LIKE '"+ customerName + "%' LIMIT 1";
        Cursor data = Database.getDataBySQL(this.db, sql);
        data.moveToFirst();
        if (data.getCount() == 0) return null;
        return DatabaseStructure.CustomerTable.toCustomer(data);
    }

    public ArrayList<String> customersNames(){
        ArrayList<String> customersNames = new ArrayList<>();
        String sql = "SELECT "+ DatabaseStructure.CustomerTable.NAME + " FROM " + DatabaseStructure.CustomerTable.TABLE_NAME;
        Cursor data = Database.getDataBySQL(this.db, sql);
        data.moveToFirst();
        while (!data.isAfterLast()) {
            customersNames.add(data.getString(0));
            data.moveToNext();
        }
        return customersNames;
    }
    private void getData(){
        Cursor data = this.db.getData();
        data.moveToFirst();
        while (!data.isAfterLast()){
            this.customers.add(DatabaseStructure.CustomerTable.toCustomer(data));
            data.moveToNext();
        }
    }
}
