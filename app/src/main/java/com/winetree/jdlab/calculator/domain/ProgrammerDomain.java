package com.winetree.jdlab.calculator.domain;

public class ProgrammerDomain {

    private static ProgrammerDomain instance;

    public static ProgrammerDomain getInstance() {
        if(instance == null) {
            instance = new ProgrammerDomain();
        }
        return instance;
    }

    private String calculator_result_display = "0";
    private String calculator_state_display = "0";
    private String calculator_hex_display = "0";
    private String calculator_dec_display = "0";
    private String calculator_oct_display = "0";
    private String calculator_bin_display = "0";

    public ProgrammerDomain() {
    }

    public ProgrammerDomain(String calculator_result_display, String calculator_state_display, String calculator_hex_display, String calculator_dec_display, String calculator_oct_display, String calculator_bin_display) {
        this.calculator_result_display = calculator_result_display;
        this.calculator_state_display = calculator_state_display;
        this.calculator_hex_display = calculator_hex_display;
        this.calculator_dec_display = calculator_dec_display;
        this.calculator_oct_display = calculator_oct_display;
        this.calculator_bin_display = calculator_bin_display;
    }

    @Override
    public String toString() {
        return "ProgrammerDomain{" +
                "calculator_result_display='" + calculator_result_display + '\'' +
                ", calculator_state_display='" + calculator_state_display + '\'' +
                ", calculator_hex_display='" + calculator_hex_display + '\'' +
                ", calculator_dec_display='" + calculator_dec_display + '\'' +
                ", calculator_oct_display='" + calculator_oct_display + '\'' +
                ", calculator_bin_display='" + calculator_bin_display + '\'' +
                '}';
    }

    public String getCalculator_result_display() {
        return calculator_result_display;
    }

    public void setCalculator_result_display(String calculator_result_display) {
        this.calculator_result_display = calculator_result_display;
    }

    public String getCalculator_state_display() {
        return calculator_state_display;
    }

    public void setCalculator_state_display(String calculator_state_display) {
        this.calculator_state_display = calculator_state_display;
    }

    public String getCalculator_hex_display() {
        return calculator_hex_display;
    }

    public void setCalculator_hex_display(String calculator_hex_display) {
        this.calculator_hex_display = calculator_hex_display;
    }

    public String getCalculator_dec_display() {
        return calculator_dec_display;
    }

    public void setCalculator_dec_display(String calculator_dec_display) {
        this.calculator_dec_display = calculator_dec_display;
    }

    public String getCalculator_oct_display() {
        return calculator_oct_display;
    }

    public void setCalculator_oct_display(String calculator_oct_display) {
        this.calculator_oct_display = calculator_oct_display;
    }

    public String getCalculator_bin_display() {
        return calculator_bin_display;
    }

    public void setCalculator_bin_display(String calculator_bin_display) {
        this.calculator_bin_display = calculator_bin_display;
    }
}
