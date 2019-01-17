package com.winetree.jdlab.calculator.programmer;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.winetree.jdlab.calculator.R;
import com.winetree.jdlab.calculator.common.CommonActivity;

public class ProgrammerActivity extends CommonActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programmer_activity);

        getNavigationBar();

        ProgrammerFragment programmerFragment = (ProgrammerFragment)getSupportFragmentManager().findFragmentById(R.id.programmer_fragment);

        if(programmerFragment == null) {
            programmerFragment = ProgrammerFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_content_main, programmerFragment).commit();
        }

    }
}
