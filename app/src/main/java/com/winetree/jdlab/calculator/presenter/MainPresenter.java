package com.winetree.jdlab.calculator.presenter;

import android.view.View;

public interface MainPresenter {

  void setViews(Views Views);
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
  void numberPadHandler(View v);
  void UIUpdator();
	
	void percentage();
	
	void root();
	
	void square();
	
	void division1();
	
	public interface Views {
    void setConfirmText(String text);
  }

}
