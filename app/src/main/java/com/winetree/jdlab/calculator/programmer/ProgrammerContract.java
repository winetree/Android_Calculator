package com.winetree.jdlab.calculator.programmer;

import android.view.View;

import com.winetree.jdlab.calculator.BaseView;
import com.winetree.jdlab.calculator.config.MainConfig;
import com.winetree.jdlab.calculator.domain.ProgrammerDomain;

public interface ProgrammerContract {

    interface View extends BaseView<Presenter> {
        void modeUpdator(String programmerCalculatorMod);

        void UIUpdator(ProgrammerDomain domain);

        void toastMessage(String message);
    }

    interface Presenter {

        void initialize();

        void setView(View view);

        void numbarPadListener(android.view.View v);

        void backSpace();

        void validChecker();

	    void modeSelector(android.view.View v);

        void setConfig(MainConfig config);

        void replaceResult(String value);

        void numberConverter();

        void clear();

        void plusMinusChacner(android.view.View v);

	    void operatorInitialize(android.view.View v);
    }

}
