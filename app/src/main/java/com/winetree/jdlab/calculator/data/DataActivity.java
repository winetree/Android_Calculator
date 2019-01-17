package com.winetree.jdlab.calculator.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.winetree.jdlab.calculator.R;
import com.winetree.jdlab.calculator.common.CommonActivity;
import com.winetree.jdlab.calculator.standard.StandardFragment;

public class DataActivity extends CommonActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_activity);

        getNavigationBar();

        DataFragment dataFragment = (DataFragment) getSupportFragmentManager().findFragmentById(R.id.data_fragment);

        if(dataFragment == null) {
            dataFragment = dataFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_content_main, dataFragment).commit();
        }
    }
}
