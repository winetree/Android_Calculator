package com.winetree.jdlab.calculator.standard;

import android.os.Bundle;
import com.winetree.jdlab.calculator.R;
import com.winetree.jdlab.calculator.common.CommonActivity;

public class StandardActivity extends CommonActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standard_activity);

        getNavigationBar();

        /**
         * Fragment Initialize
         */
        StandardFragment standardFragment = (StandardFragment)getSupportFragmentManager().findFragmentById(R.id.standard_fragment);

        if(standardFragment == null) {
            standardFragment = StandardFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_content_main, standardFragment).commit();
        }

    }

}
