package com.winetree.jdlab.calculator.standard;

import android.view.View;

import com.winetree.jdlab.calculator.BasePresenter;
import com.winetree.jdlab.calculator.BaseView;

public interface StandardContract {

    interface View extends BaseView<Presenter> {
        void UIUpdator();

    }

    interface Presenter extends BasePresenter {
        void plusMinus();

        void backSpace();

        void decimalPoint();

        void minus();

        void clearEntry();

        void multiplication();

        void division();

        void clear();

        void calculate();

        void plus();

        void numberPadHandler(android.view.View v);

        void setView(android.view.View activity, View view);

        void UIUpdator();

        void percentage();

        void root();

        void square();

        void division1();
    }


}
