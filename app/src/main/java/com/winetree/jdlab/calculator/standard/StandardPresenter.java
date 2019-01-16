package com.winetree.jdlab.calculator.standard;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.winetree.jdlab.calculator.R;

import java.math.BigDecimal;

public class StandardPresenter implements StandardContract.Presenter {
	
	private StringBuffer stateInput = new StringBuffer();
	private StringBuffer operatorInput = new StringBuffer();
	private StringBuffer resultInput = new StringBuffer("0");
	private boolean isMinus = false;
	private boolean isOperatorInitialized = false;


	/**
	 * activity 로부터 의존성 주입
	 */
	private View activity;
	private StandardContract.View view;

	@Override
	public void setView(View activity, StandardContract.View view) {
		this.activity = activity;
		this.view = view;
	}

	public StandardPresenter(View activity, StandardContract.View view) {
		this.activity = activity;
		this.view = view;
		view.setPresenter(this);
	}

	@Override
	public void start() {
	
	}
	
	@Override
	public void UIUpdator() {
		
		if (
			resultInput.length() == 0
		) {
			resultInput.append("0");
		}
		
		if (
			stateInput.length() == 0
		) {
			stateInput.append("0");
		}
		
		if (resultInput.length() == 1 &&
			resultInput.substring(0, 1).equals("0") &&
			isMinus) {
			isMinus = false;
		}
		
		if (
			stateInput.indexOf(".") == stateInput.length() - 1
		) {
			stateInput.setLength(stateInput.length() - 1);
		}
		
		String result = "";
		
		if (isMinus) {
			result = "-" + resultInput.toString();
		} else {
			result = resultInput.toString();
		}
		
		
		TextView resultText = activity.findViewById(R.id.result);
		resultText.setText(result);
		TextView operatorText = activity.findViewById(R.id.operator);
		operatorText.setText(operatorInput);
		TextView stateText = activity.findViewById(R.id.state);
		stateText.setText(stateInput);
		
	}
	
	@Override
	public void plusMinus() {
		isMinus = !isMinus;
		UIUpdator();
	}
	
	@Override
	public void backSpace() {
		if (
			resultInput.length() > 0
		) {
			resultInput.setLength(resultInput.length() - 1);
		}
		UIUpdator();
	}
	
	@Override
	public void decimalPoint() {
		if (
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
			stateInput.append("-");
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
		operatorInput.setLength(0);
		UIUpdator();
	}
	
	@Override
	public void calculate() {
		if(operatorInput != null && operatorInput.length() > 0) {
			calculator();
		}
	}
	
	@Override
	public void plus() {
		pendding("+");
	}
	
	@Override
	public void numberPadHandler(android.view.View v) {
		
		Button button = (Button) v;
		
		String number = button.getText().toString();
		
		if (isOperatorInitialized) {
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
	public void percentage() {
		pendding("%");
		calculate();
	}
	
	@Override
	public void root() {
		pendding("root");
		calculator();
	}
	
	@Override
	public void square() {
		pendding("2");
		calculator();
	}
	
	@Override
	public void division1() {
		pendding("1/100");
		calculator();
	}
	
	
	private void calculator() {
		
		if (
			resultInput.indexOf(".") == resultInput.length() - 1
		) {
			resultInput.substring(0, resultInput.length() - 1);
		}
		
		StringBuffer temp = new StringBuffer();
		
		if (isMinus) {
			temp.append("-");
		}
		temp.append(resultInput);
		
		BigDecimal result = new BigDecimal(temp.toString());
		BigDecimal state = new BigDecimal(stateInput.toString());
		BigDecimal fin = new BigDecimal("0");
		
		switch (operatorInput.toString()) {
			
			case "+":
				fin = state.add(result);
				break;
			case "-":
				fin = state.subtract(result);
				break;
			case "*":
				fin = state.multiply(result);
				break;
			case "/":
				fin = state.divide(result, 10, BigDecimal.ROUND_HALF_UP);
				break;
			case "2":
				fin = state.multiply(state);
				break;
			case "root":
				fin = new BigDecimal(Math.sqrt(state.doubleValue())).divide(new BigDecimal("1"), 10, BigDecimal.ROUND_HALF_UP);
				break;
			case "1/100":
				if (state.doubleValue() != 0) {
					fin = new BigDecimal("1").divide(state, 10, BigDecimal.ROUND_HALF_UP);
				}
				break;
			case "%":
				fin = fin.multiply(new BigDecimal(100));
				break;
		}
		
		if (fin.doubleValue() == fin.longValue()) {
			fin = new BigDecimal(fin.longValue());
		}
		String last = fin.toString();
		int loop = last.length();
		
		// 1.500
		
		// 소수점 자르기
		if (last.contains(".")) {
			for (int i = 0; i < loop; i++) {
				if (last.lastIndexOf("0") == last.length() - 1) {
					last = last.substring(0, last.length() - 1);
					continue;
				} else if (last.lastIndexOf(".") == last.length() - 1) {
					last = last.substring(0, last.length() - 1);
					continue;
				} else {
					break;
				}
			}
		}
		
		stateInput.setLength(0);
		stateInput.append(0);
		
		resultInput.setLength(0);
		resultInput.append(last);
		
		if (Double.parseDouble(last) >= 0) {
			isMinus = false;
		} else {
			isMinus = true;
			resultInput.replace(0, resultInput.length() - 1, resultInput.substring(1, resultInput.length() - 1));
		}
		
		UIUpdator();
	}
	
	
}
