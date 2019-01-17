package com.winetree.jdlab.calculator.programmer;

import android.view.View;

import com.winetree.jdlab.calculator.BaseView;
import com.winetree.jdlab.calculator.domain.ProgrammerDomain;

public interface ProgrammerContract {

    interface View extends BaseView<Presenter> {
        void UIUpdator(ProgrammerDomain domain);
    }

    interface Presenter {

        void initialize();

        void setView(View view);

        void numbarPadListener(android.view.View v);
    }

}
