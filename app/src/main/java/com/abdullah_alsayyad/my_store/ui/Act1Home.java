package com.abdullah_alsayyad.my_store.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.abdullah_alsayyad.my_store.R;
public class Act1Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a2_home);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            ((LinearLayout) findViewById(R.id.ll)).setOrientation(LinearLayout.HORIZONTAL);
    }

}