package com.winetree.jdlab.calculator.programmer;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.winetree.jdlab.calculator.R;
import com.winetree.jdlab.calculator.config.MainConfig;
import com.winetree.jdlab.calculator.domain.ProgrammerDomain;

import java.math.BigInteger;

public class ProgrammerPresenter implements ProgrammerContract.Presenter {

	private static ProgrammerPresenter instance;
	private ProgrammerContract.View view;
	private ProgrammerDomain domain;

	private MainConfig config;

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
		try {

			StringBuffer result = domain.getCalculator_result_display();
			String temp = result.substring(0, result.length() - 1);

			result.setLength(0);

			if(temp == null || temp.length() == 0) {
				temp = "0";
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
				if (
					result.length() == 1 &&
						result.substring(0, 1).equals("0")
				) {
					result.setLength(0);
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
		StringBuffer result = domain.getCalculator_result_display();

		if (!isValid(result.toString())) {
			String temp = result.substring(0, result.length() - 1);
			while (!isValid(temp)) {
				temp = temp.substring(0, temp.length() - 1);
			}
			view.toastMessage("데이터 타입의 최대 허용 범위를 넘었습니다.");
			result.setLength(0);
			result.append(temp);
		}

		numberConverter();
		view.UIUpdator(domain);
	}

	private boolean isValid(String number) {

		String mode = config.getProgrammerCalculatorMod();
		int modeInt = 10;

		switch (mode) {
			case "hex":
				modeInt = 16;
				break;
			case "dec":
				modeInt = 10;
				break;
			case "oct":
				modeInt = 8;
				break;
			case "bin":
				modeInt = 2;
				break;
			default:
				modeInt = 10;
				break;
		}

		String type = config.getPROGRAMMER_CALCULATOR_TYPE();

		try {
			switch (type) {
				case "byte":
					Byte.parseByte(number, modeInt);
					break;
				case "word":
					Short.parseShort(number, modeInt);
					break;
				case "dword":
					Integer.parseInt(number, modeInt);
					break;
				case "qword":
					Long.parseLong(number, modeInt);
					break;
				default:
					return false;
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

	private int getType(){
		String type = config.getPROGRAMMER_CALCULATOR_TYPE();
		int result = 10;
		switch (type) {
			case "hex" : result = 16; break;
			case "dec" : result = 10; break;
			case "oct" : result = 8; break;
			case "bin" : result = 2; break;
		}
		return result;
	}

	@Override
	public void numberConverter() {
		String mode = config.getProgrammerCalculatorMod();

		int type = getType();

		String result = domain.getCalculator_result_display().toString();

		switch (mode) {
			case "hex" :
				Long hex = Long.parseLong(result, 16);
				Log.d("hex : ", hex.toString());

				domain.setCalculator_hex_display(Long.toHexString(hex));
				domain.setCalculator_dec_display(hex.toString());
				domain.setCalculator_oct_display(Long.toOctalString(hex));
				domain.setCalculator_bin_display(Long.toBinaryString(hex));

				break;
			case "dec" :
				Integer dec = Integer.parseInt(result, 10);

				domain.setCalculator_hex_display(Integer.toHexString(dec));
				domain.setCalculator_dec_display(dec.toString());
				domain.setCalculator_oct_display(Integer.toOctalString(dec));
				domain.setCalculator_bin_display(Integer.toBinaryString(dec));

				break;
			case "oct" :
				Short oct = Short.parseShort(result, 8);

				domain.setCalculator_hex_display(Integer.toHexString(oct & 0xffff));
				domain.setCalculator_dec_display(oct.toString());
				domain.setCalculator_oct_display(Integer.toOctalString(oct & 0xffff));
				domain.setCalculator_bin_display(Integer.toBinaryString(oct & 0xffff));

				break;
			case "bin" :
				Byte bin = Byte.parseByte(result, 2);



				break;
		}

//		domain.setCalculator_hex_display(number.toString(16).toUpperCase());
//		domain.setCalculator_dec_display(number.toString(10));
//		domain.setCalculator_oct_display(number.toString(8));
//		domain.setCalculator_bin_display(number.toString(2));
//		view.UIUpdator(domain);
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

	@Override
	public void plusMinusChacner(View v) {
		StringBuffer result = domain.getCalculator_result_display();

		int mod = 10;

		switch (config.getProgrammerCalculatorMod()) {
			case "hex":
				mod = 16;
				break;
			case "dec":
				mod = 10;
				break;
			case "oct":
				mod = 8;
				break;
			case "bin":
				mod = 2;
				break;
		}

		BigInteger number = new BigInteger(domain.getCalculator_dec_display(), 10);
		number = number.negate();

		Log.d("tag", Long.toHexString(new Long("-55")));
		Log.d("tag", Long.toBinaryString(new Long("-55")));
		Log.d("tag", Integer.toHexString(new Integer("-55")));
		Log.d("tag", Integer.toHexString(new Integer("-55")));

		result.setLength(0);
		result.append(number.toString(mod));

		config.setProgrammerCalculatorMod("dec");
		domain.setCalculator_result_display(result);

		numberConverter();
		view.UIUpdator(domain);
	}

}
