package com.winetree.jdlab.calculator.config;

public class MainConfig {

  /**
   * Instance Generator
   */
  private static MainConfig instance;
  public static MainConfig getInstance() {
    if(instance == null) {
      instance = new MainConfig();
    }
    return instance;
  }

  /**
   * MAX_DECIMAL_POINT
   */
  private int MAX_DECIMAL_POINT = 20;



  /**
   * hex : Hexdecimal 16
   * dec : Decimal 10 (Default)
   * oct : Octal 8
   * bin : Binary 2
   */
  private String PROGRAMMER_CALCULATOR_INPUT_MOD = "dec";

  /**
   * byte : BYTE
   * word : WORD
   * dword : DWORD
   * qword : QWORD (Default)
   */
  private String PROGRAMMER_CALCULATOR_BIT_TYPE = "word";


  public String getProgrammerCalculatorMod() {
    return this.PROGRAMMER_CALCULATOR_INPUT_MOD;
  }

  public void setProgrammerCalculatorMod(String programmerCalculatorMod) {
    this.PROGRAMMER_CALCULATOR_INPUT_MOD = programmerCalculatorMod;
  }

  public String getPROGRAMMER_CALCULATOR_BIT_TYPE() {
    return PROGRAMMER_CALCULATOR_BIT_TYPE;
  }

  public void setPROGRAMMER_CALCULATOR_BIT_TYPE(String PROGRAMMER_CALCULATOR_BIT_TYPE) {
    this.PROGRAMMER_CALCULATOR_BIT_TYPE = PROGRAMMER_CALCULATOR_BIT_TYPE;
  }
}
