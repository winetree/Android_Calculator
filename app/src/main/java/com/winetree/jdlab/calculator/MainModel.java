package com.winetree.jdlab.calculator;

import java.math.BigDecimal;

public class MainModel {

  private MainConfig mainConfig;

  private int MAX_DECIMAL = 10;

  private boolean isOperatorInitialized;

  private String state;
  private String result;
  private String operator;

  public void setData(
    String state,
    String operator,
    String result
  ){
    this.state = state;
    this.operator = operator;
    this.result = result;
    this.isOperatorInitialized = true;
  }

  @Override
  public String toString() {
    return "DataStorage{" +
      "isOperatorInitialized=" + isOperatorInitialized +
      ", state='" + state + '\'' +
      ", result='" + result + '\'' +
      ", operator='" + operator + '\'' +
      '}';
  }

  public boolean isOperatorInitialized() {
    return isOperatorInitialized;
  }

  public void setOperatorInitialized(boolean operatorInitialized) {
    this.isOperatorInitialized = operatorInitialized;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public MainModel() {
    this.mainConfig = new MainConfig();
  }

  public MainModel(boolean isOperatorInitialized, String state, String result, String operator) {
    this.isOperatorInitialized = isOperatorInitialized;
    this.state = state;
    this.result = result;
    this.operator = operator;
  }

  public String calculate(){

    BigDecimal state = new BigDecimal(this.state);
    BigDecimal result = new BigDecimal(this.result);
    BigDecimal calculated = new BigDecimal("0");

    switch (this.operator) {
      case "/" : calculated = state.divide(result, MAX_DECIMAL, BigDecimal.ROUND_HALF_UP); break;
      case "*" : calculated = state.multiply(result); break;
      case "+" : calculated = state.add(result); break;
      case "-" : calculated = state.subtract(result); break;
    }

    return calculated.toString();
  }

}
