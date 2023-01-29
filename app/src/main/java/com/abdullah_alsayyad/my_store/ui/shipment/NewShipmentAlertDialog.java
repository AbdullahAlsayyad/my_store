package com.abdullah_alsayyad.my_store.ui.shipment;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.abdullah_alsayyad.my_store.R;

public abstract class NewShipmentAlertDialog implements View.OnClickListener {
    public abstract int onClickCreateShipment(String maximum, String minimum, String note);
    private final AlertDialog.Builder builder;
    private final AlertDialog dialog;

    private final View view;

    public NewShipmentAlertDialog(Context context) {
        builder = new AlertDialog.Builder(context);
        view = LayoutInflater.from(context).inflate(R.layout.d2_new_shipment, null, false);
        buildView();
        dialog = builder.create();
    }

    public void show() {
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() != R.id.d2_btn_save) return;

        final EditText eTMaximum, eTMinimum, eTNote;
        eTMaximum = view.findViewById(R.id.d2_et_maximum);
        eTMinimum = view.findViewById(R.id.d2_et_minimum);
        eTNote = view.findViewById(R.id.d2_et_note);

        int result = onClickCreateShipment(eTMaximum.getText().toString(), eTMinimum.getText().toString(),
                eTNote.getText().toString());

        if (result > -1) {
            dialog.dismiss();
        }

//        Change result to message
//        String message;
//        switch (result) {
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
//                message = "ID: " + result;
//                //        Create Pop-up when success
//                dialog.dismiss();
//                break;
//        }
//
//        Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void buildView() {
        //        Ex: StToRe
        builder.setTitle("TEST")
                .setView(view);
        view.findViewById(R.id.d2_btn_save).setOnClickListener(this);
    }
}