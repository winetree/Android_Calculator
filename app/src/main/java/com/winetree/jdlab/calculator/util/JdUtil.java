package com.winetree.jdlab.calculator.util;

public class JdUtil {


  public String removeOperator(String number) {

    if(number == null || number.length() == 0) {
      return null;
    } else if(
      number.substring(0, 1).equals("-")
      ) {
      number = number.substring(1, number.length());
    }

    return number;
  }


}
