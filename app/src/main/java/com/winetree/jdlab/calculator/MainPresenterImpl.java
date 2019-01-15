package com.winetree.jdlab.calculator;

import android.view.View;
import android.widget.Button;
import com.winetree.jdlab.calculator.databinding.ActivityMainBinding;

import java.math.BigDecimal;

public class MainPresenterImpl implements MainPresenter {

  private StringBuffer stateInput = new StringBuffer(" ");
  private StringBuffer operatorInput = new StringBuffer(" ");
  private StringBuffer resultInput = new StringBuffer("0");
  private boolean isMinus = false;
  private boolean isOperatorInitialized = false;

  /**
   * activity 로부터 의존성 주입
   */
  private ActivityMainBinding activity;
  private Views views;
  private MainModel mainModel;

  public MainPresenterImpl(ActivityMainBinding activity) {
    this.activity = activity;
    this.mainModel = new MainModel();
  }

  @Override
  public void setViews(Views Views) {
    this.views = views;
  }

  @Override
  public void UIUpdator() {
	
	  if (
		  resultInput.length() == 0
	  ) {
		  resultInput.append("0");
	  }
	  
	  if(
	  	stateInput.length() == 0
	  ) {
	  	stateInput.append("0");
	  }

    if (
      stateInput.indexOf(".") == stateInput.length() - 1
    ) {
      stateInput.setLength(stateInput.length() - 1);
    }

    String result = isMinus?"-"+resultInput.toString():resultInput.toString();

    activity.result.setText(result);
    activity.operator.setText(operatorInput);
    activity.state.setText(stateInput);

  }

  @Override
  public void plusMinus() {
    isMinus = !isMinus;
    UIUpdator();
  }

  @Override
  public void backSpace() {
    if(
      resultInput.length() > 0
      ) {
      resultInput.setLength(resultInput.length() - 1);
    }
      UIUpdator();
  }

  @Override
  public void decimalPoint() {
    if(
      resultInput.indexOf(".") == -1
      ) {
      resultInput.append(".");
    }
    UIUpdator();
  }

  @Override
  public void minus() {
		pendding("-");
  }
  
  public void pendding(String operator) {
	  operatorInput.setLength(0);
	  operatorInput.append(operator);
	
	  stateInput.setLength(0);
	
	  if (isMinus) {
		  stateInput.append(operator);
	  }
	  stateInput.append(resultInput);
	
	  isOperatorInitialized = true;
	
	  UIUpdator();
  }

  @Override
  public void clearEntry() {
    resultInput.setLength(0);
    UIUpdator();
  }

  @Override
  public void multiplication() {
	  pendding("*");
  }

  @Override
  public void division() {
  	pendding("/");
  }

  @Override
  public void clear() {
    stateInput.setLength(0);
    resultInput.setLength(0);
    UIUpdator();
  }

  @Override
  public void calculate() {
    calculator();
  }

  @Override
  public void plus() {
		pendding("+");
  }

  @Override
  public void numberPadHandler(View v) {

    Button button = (Button) v;

    String number = button.getText().toString();

    if(isOperatorInitialized) {
      resultInput.setLength(0);
      isOperatorInitialized = false;
    }

    if (
         resultInput.length() == 1 &&
         resultInput.substring(0, 1).equals("0")
      ) {
      resultInput.setLength(0);
    }

    resultInput.append(number);

    UIUpdator();
  }
  
  @Override
  public void percentage(){

  }
  
  @Override
  public void root(){
    pendding("root");
    calculator();
  }
  
  @Override
  public void square(){
    pendding("2");
    calculator();
  }
  
  @Override
  public void division1(){
    pendding("1/100");
    calculator();
  }
  

  private void calculator(){

	  if (
		  resultInput.indexOf(".") == resultInput.length() - 1
	  ) {
			resultInput.substring(0, resultInput.length() - 1);
	  }
	  
	  BigDecimal result = new BigDecimal(isMinus?"-":""+resultInput.toString());
	  System.out.println(result);
	  BigDecimal state = new BigDecimal(stateInput.toString());
	  System.out.println(state);
	  BigDecimal fin = new BigDecimal("0");

    switch(operatorInput.toString()) {
    	
      case "+" :
      	fin = state.add(result);
      	break;
      case "-" :
      	fin = state.subtract(result);
      	break;
      case "*" :
	      fin = state.multiply(result);
      	break;
      case "/" :
	      fin = state.divide(result, 10, BigDecimal.ROUND_HALF_UP);
      	break;
      case "2" :
        fin =  state.multiply(state);
        break;
      case "root" :
        fin = new BigDecimal(Math.sqrt(state.doubleValue()));
        break;
      case "1/100" :
        fin = state.multiply(new BigDecimal(100));
        break;
    }
    
    if (fin.doubleValue() == fin.longValue()) {
			fin = new BigDecimal(fin.longValue());
    }
    
    stateInput.setLength(0);
    stateInput.append(0);
	
	  resultInput.setLength(0);
	  resultInput.append(fin);
    
    UIUpdator();
  }


}
