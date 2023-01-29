package com.abdullah_alsayyad.my_store.ui.customer;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.abdullah_alsayyad.my_store.R;
import com.abdullah_alsayyad.my_store.ui.customer.CustomersVM;

public abstract class NewCustomerAlertDialog implements View.OnClickListener {
    private final AlertDialog.Builder builder;
    private final AlertDialog dialog;
    private final View view;



    /**
     * on click {@link R.id#d1_btn_save} in {@link R.layout#d1_new_customer}
     * @param name Customer name
     * @param phoneNumber phone number of customer
     * @param note any notes
     * @return result when trying to add it
     */
    public abstract int onClickCreateCustomer(String name, String phoneNumber, String note);

    public NewCustomerAlertDialog(Context context) {
        builder = new AlertDialog.Builder(context);
        view = LayoutInflater.from(context).inflate(R.layout.d1_new_customer, null, false);
        buildView();
        dialog = builder.create();
    }

    public void show(){
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() != R.id.d1_btn_save) return;

        Context context = v.getContext();
        final EditText eTName, eTPhone, eTNote;
        eTName = view.findViewById(R.id.d1_et_name);
        eTPhone = view.findViewById(R.id.d1_et_phone);
        eTNote = view.findViewById(R.id.d1_et_note);

        int result = onClickCreateCustomer(eTName.getText().toString(),
                eTPhone.getText().toString(), eTNote.getText().toString());

        if (result != -1) {
            dialog.dismiss();
        }

//        Change result to message
//        String message;
//        switch (result){
//            case CustomersVM.RESULT_EMPTY:
//                //        Ex: StToRe
//                message = "Must be add Data";
//                break;
//            case CustomersVM.RESULT_NAME_IS_EXIST:
//                //        Ex: StToRe
//                message = "Name is Exist";
//                break;
//            case CustomersVM.RESULT_PHONE_IS_EXIST:
//                //        Ex: StToRe
//                message = "Phone is Exist";
//                break;
//            case CustomersVM.RESULT_ERROR:
//                //        Ex: StToRe
//                message = "Error";
//                break;
//            default:
//                //        Ex: StToRe
//                message = "ID: "+result;
//                //        Create Pop-up when success
//                dialog.dismiss();
//                break;
//        }
//
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void buildView(){
        //        Ex: StToRe
        builder.setTitle("TEST")
                .setView(view);
        view.findViewById(R.id.d1_btn_save).setOnClickListener(this);
    }


}
