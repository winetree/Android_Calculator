package com.winetree.jdlab.calculator.programmer;

import android.view.View;
import android.widget.Button;

import com.winetree.jdlab.calculator.domain.ProgrammerDomain;

public class ProgrammerPresenter implements ProgrammerContract.Presenter{


    private static ProgrammerPresenter instance;
    private ProgrammerContract.View view;
    private ProgrammerDomain domain;

    public static ProgrammerPresenter getInstance(){
        if(instance == null) {
            instance = new ProgrammerPresenter();
        }
        return instance;
    }

    @Override
    public void initialize(){
        this.domain = ProgrammerDomain.getInstance();
        view.UIUpdator(domain);
    }

    @Override
    public void setView(ProgrammerContract.View view) {
        this.view = view;
    }

    public void fds(){

    }

    @Override
    public void numbarPadListener(View v) {
        int id = v.getId();
        Button btn = (Button)v;
        domain.setCalculator_result_display(btn.getText().toString());
        view.UIUpdator(domain);
    }
}
