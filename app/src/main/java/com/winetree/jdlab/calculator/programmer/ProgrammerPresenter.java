package com.winetree.jdlab.calculator.programmer;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.winetree.jdlab.calculator.R;
import com.winetree.jdlab.calculator.config.MainConfig;
import com.winetree.jdlab.calculator.domain.ProgrammerDomain;

public class ProgrammerPresenter implements ProgrammerContract.Presenter {

	private static ProgrammerPresenter instance;
	private ProgrammerContract.View view;
	private ProgrammerDomain domain;
	private MainConfig config;

	private String operator;
	private String state_number;

	@Override
	public void setConfig(MainConfig config) {
		this.config = config;
	}

	public static ProgrammerPresenter getInstance() {
		if (instance == null) {
			instance = new ProgrammerPresenter();
		}
		return instance;
	}

	@Override
	public void initialize() {
		this.domain = ProgrammerDomain.getInstance();
		view.UIUpdator(domain);
	}

	@Override
	public void setView(ProgrammerContract.View view) {
		this.view = view;
	}

	@Override
	public void numbarPadListener(View v) {

		Button clickedButton = (Button) v;
		int button_id = clickedButton.getId();

		String input = "";

		switch (button_id) {
			case R.id.calculator_decimal_point:
				input = ".";
				break;
			case R.id.calculator_0:
				input = "0";
				break;
			case R.id.calculator_1:
				input = "1";
				break;
			case R.id.calculator_2:
				input = "2";
				break;
			case R.id.calculator_3:
				input = "3";
				break;
			case R.id.calculator_4:
				input = "4";
				break;
			case R.id.calculator_5:
				input = "5";
				break;
			case R.id.calculator_6:
				input = "6";
				break;
			case R.id.calculator_7:
				input = "7";
				break;
			case R.id.calculator_8:
				input = "8";
				break;
			case R.id.calculator_9:
				input = "9";
				break;
			case R.id.calculator_b:
				input = "b";
				break;
			case R.id.calculator_c:
				input = "c";
				break;
			case R.id.calculator_d:
				input = "d";
				break;
			case R.id.calculator_e:
				input = "e";
				break;
			case R.id.calculator_f:
				input = "f";
				break;
			case R.id.calculator_parenthese_open:
				input = "(";
				break;
			case R.id.calculator_parenthese_close:
				input = ")";
				break;
		}

		numberPadAppender(input);
	}

	public String hexdecimalCalculator(int decimal) {

		int temp;
		String hex = "";

		char hexchars[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

		while (decimal > 0) {
			temp = decimal % 16;
			hex = hexchars[temp] + hex;
			decimal = decimal / 16;
		}

		return hex;
	}

	@Override
	public void backSpace() {

		int inputMode = getInputMode();

		try {

			StringBuffer result = domain.getCalculator_result_display();
			String temp = result.substring(0, result.length() - 1);

			result.setLength(0);

			if(temp == null || temp.length() == 0) {
				temp = "0";
			}

			if(temp.length() == 1 && temp.substring(0, 1).equals("0") && inputMode == 2) {
				temp = "";
			}

			result.append(temp);

			numberConverter();

			view.UIUpdator(domain);

		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	private void numberPadAppender(String input) {

		if (input == null) {
			return;
		}

		StringBuffer result = domain.getCalculator_result_display();
		StringBuffer state = domain.getCalculator_state_display();

		switch (input) {
			// 기존 comma 가 없을 경우에만 입력
			case ".":
				if (!result.toString().contains(".")) {
					result.append(input);
				}
				break;
			case "(":

				break;
			case ")":

				if (
					state.length() != 1
				) {

				}

				break;

			/**
			 * 첫째자리가 0 이면 대체
			 */
			default:

				int mode = getInputMode();

				if( mode > 2) {

				if (
						result.length() == 1 &&
						result.substring(0, 1).equals("0")
				) {
					result.setLength(0);
				}

				}

				result.append(input);

				break;
		}

		domain.setCalculator_result_display(result);

		validChecker();

		numberConverter();

		view.UIUpdator(domain);
	}

	@Override
	public void validChecker() {

		try {
		StringBuffer result = domain.getCalculator_result_display();

		String temp = result.toString();
		while (!isValid(temp)) {
			temp = temp.substring(0, temp.length() - 1);
			view.toastMessage("데이터 타입의 최대 허용 범위를 넘었습니다.");
		}

		result.setLength(0);
		result.append(temp);

		numberConverter();
		view.UIUpdator(domain);
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	private boolean isValid(String number) {

		int mode = getInputMode();
		int type = getBitType();

			try {
				switch (type) {
					case 8 :
						Byte.parseByte(number, mode);
						break;
					case 16:
						Short.parseShort(number, mode);
						break;
					case 32:
						Integer.parseInt(number, mode);
						break;
					case 64:
						Long.parseLong(number, mode);
						break;
				}

				return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public void modeSelector(View v) {
		View mode = (View) v;

		StringBuffer result = domain.getCalculator_result_display();

		int id = mode.getId();
		Log.d("id is : ", String.valueOf(id));

		result.setLength(0);

		switch (id) {
			case R.id.calculator_hex:
				config.setProgrammerCalculatorMod("hex");
				result.append(domain.getCalculator_hex_display());

				break;
			case R.id.calculator_dec:
				config.setProgrammerCalculatorMod("dec");
				result.append(domain.getCalculator_dec_display());

				break;
			case R.id.calculator_oct:
				config.setProgrammerCalculatorMod("oct");
				result.append(domain.getCalculator_oct_display());

				break;
			case R.id.calculator_bin:
				config.setProgrammerCalculatorMod("bin");
				result.append(domain.getCalculator_bin_display());

				break;
			default:
				break;
		}

		domain.setCalculator_result_display(result);

		validChecker();

		numberConverter();

		view.UIUpdator(domain);

		view.modeUpdator(config.getProgrammerCalculatorMod());
	}

	@Override
	public void replaceResult(String value) {
		domain.getCalculator_result_display().setLength(0);
		domain.getCalculator_result_display().append(value);

		numberConverter();

		view.UIUpdator(domain);
	}

	private int getBitType(){
		String type = config.getPROGRAMMER_CALCULATOR_BIT_TYPE();
		int result = 10;
		switch (type) {
			case "byte" : result = 8; break;
			case "word" : result = 16; break;
			case "dword" : result = 32; break;
			case "qword" : result = 64; break;
		}
		return result;
	}

	private int getInputMode(){
		String mode = config.getProgrammerCalculatorMod();
		switch (mode) {
			case "bin" : return 2;
			case "oct" : return 8;
			case "dec" : return 10;
			case "hex" : return 16;
		}
		return -1;
	}

	@Override
	public void numberConverter() {
		numberConverter(false);
	}


	private void numberConverter(boolean isc) {

		try {
			int mode = getInputMode(); // 2, 8, 10, 16
			int bit = getBitType(); // 8bit 16bit 32bit 64bit

			String result = domain.getCalculator_result_display().toString();

			String hex = "";
			String bin = "";
			String oct = "";

			switch (bit) {
				case 64:

					Long dec_64 = Long.parseLong(result, mode);

					if (isc) {
						dec_64 = dec_64 * -1;
					}

					hex = Long.toHexString(dec_64);
					bin = Long.toBinaryString(dec_64);
					oct = Long.toOctalString(dec_64);

					domain.setCalculator_dec_display(dec_64.toString());

					break;

				case 32:

					Integer dec_32 = Integer.parseInt(result, mode);

					if (isc) {
						dec_32 = dec_32 * -1;
					}

					hex = Integer.toHexString(dec_32);
					bin = Integer.toBinaryString(dec_32);
					oct = Integer.toOctalString(dec_32);

					domain.setCalculator_dec_display(dec_32.toString());

					break;

				case 16:

					Integer dec_16 = Integer.parseInt(result, mode);

					if (isc) {
						dec_16 = dec_16 * -1;
					}

					hex = Integer.toHexString(dec_16 & 0xffff);
					bin = Integer.toBinaryString(dec_16 & 0xffff);
					oct = Integer.toOctalString(dec_16 & 0xffff);

					domain.setCalculator_dec_display(dec_16.toString());

					break;

				case 8:

					Integer dec_8 = Integer.parseInt(result, mode);

					if (isc) {
						dec_8 = dec_8 * -1;
					}

					hex = Integer.toHexString(dec_8 & 0xff);
					bin = Integer.toBinaryString(dec_8 & 0xff);
					oct = Integer.toOctalString(dec_8 & 0xff);

					domain.setCalculator_dec_display(dec_8.toString());

					break;
			}

			domain.setCalculator_hex_display(hex);
			domain.setCalculator_bin_display(bin);
			domain.setCalculator_oct_display(oct);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	@Override
	public void clear() {

		domain.setCalculator_result_display(new StringBuffer("0"));
		domain.setCalculator_state_display(new StringBuffer("0"));

		domain.setCalculator_hex_display("0");
		domain.setCalculator_dec_display("0");
		domain.setCalculator_oct_display("0");
		domain.setCalculator_bin_display("0");

		view.UIUpdator(domain);
	}

	/**
	 * 양수, 정수 Conversion
	 * @param v
	 */
	@Override
	public void plusMinusChacner(View v) {

		int mode = getInputMode();
		int type = getBitType();

		config.setProgrammerCalculatorMod("dec");

		StringBuffer result = domain.getCalculator_result_display();

		String num = domain.getCalculator_dec_display();
		Long num1 = Long.parseLong(num);
		num1 = num1 * -1;

		result.setLength(0);
		result.append(num1);
		domain.setCalculator_result_display(result);

		numberConverter();
		result.setLength(0);

		switch (mode) {
			case 16 :
					result.append(domain.getCalculator_hex_display());
					config.setProgrammerCalculatorMod("hex");
				break;
			case 10 :
					result.append(domain.getCalculator_dec_display());
					config.setProgrammerCalculatorMod("dec");
				break;
			case 8 :
					result.append(domain.getCalculator_oct_display());
					config.setProgrammerCalculatorMod("oct");
				break;
			case 2 :
					result.append(domain.getCalculator_bin_display());
					config.setProgrammerCalculatorMod("bin");
				break;
		}

		domain.setCalculator_result_display(result);

		view.UIUpdator(domain);

	}

	@Override
	public void operatorInitialize(View v){
		operatorReset();

		Button operatorButton = (Button)v;
		int id = operatorButton.getId();

		System.out.println(id);

		switch (id) {
			case R.id.calculator_plus:
				this.operator = "+";
				break;
			case R.id.calculator_minus:
				this.operator = "-";
				break;
			case R.id.calculator_multiplication:
				this.operator = "*";
				break;
			case R.id.calculator_division:
				this.operator = "/";
				break;
		}

		this.state_number = domain.getCalculator_result_display().toString();

		StringBuffer state = new StringBuffer(this.state_number + "  " + this.operator);

		domain.setCalculator_state_display(state);


		StringBuffer result = domain.getCalculator_result_display();

		result.setLength(0);
		result.append(0);

		domain.setCalculator_result_display(result);

		view.UIUpdator(domain);
	}

	public void operatorReset() {

		this.operator = "";
		this.state_number = "";

	}

}
