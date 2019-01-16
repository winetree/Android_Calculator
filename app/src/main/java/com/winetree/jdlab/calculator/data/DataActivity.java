package com.winetree.jdlab.calculator.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.winetree.jdlab.calculator.R;
import com.winetree.jdlab.calculator.common.CommonActivity;
import com.winetree.jdlab.calculator.common.ToolbarFragment;

public class DataActivity extends CommonActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_activity);

        getNavigationBar();

//        ToolbarFragment toolbarFragment = (ToolbarFragment)getSupportFragmentManager().findFragmentById(R.id.toolbar);
//
//        if(toolbarFragment == null) {
//            toolbarFragment = toolbarFragment.newInstance();
//            getSupportFragmentManager().beginTransaction().replace(R.id.toolbar_frame, toolbarFragment).commit();
//        }

    }
}
