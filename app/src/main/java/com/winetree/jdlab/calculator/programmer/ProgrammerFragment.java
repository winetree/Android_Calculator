package com.winetree.jdlab.calculator.programmer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.winetree.jdlab.calculator.R;
import com.winetree.jdlab.calculator.config.MainConfig;
import com.winetree.jdlab.calculator.domain.ProgrammerDomain;

import java.util.ArrayList;
import java.util.List;

public class ProgrammerFragment extends Fragment implements ProgrammerContract.View {

	private AppCompatActivity appCompatActivity;
	private ProgrammerContract.Presenter presenter;

	private TextView display_result;
	private TextView display_state;

	private TextView display_hex_value;
	private TextView display_dec_value;
	private TextView display_oct_value;
	private TextView display_bin_value;

	private List<Button> hexadecimalList = new ArrayList<>();
	private List<Button> decimalList = new ArrayList<>();
	private List<Button> octatLst = new ArrayList<>();
	private List<Button> binaryList = new ArrayList<>();

	public static ProgrammerFragment newInstance() {
		return new ProgrammerFragment();
	}

	@Override
	public void setPresenter(ProgrammerContract.Presenter presenter) {

	}

	private MainConfig config = MainConfig.getInstance();

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.programmer_fragment, container, false);

		this.appCompatActivity = (AppCompatActivity) getActivity();

		display_result = view.findViewById(R.id.calculator_result_display);
		display_state = view.findViewById(R.id.calculator_state_display);

		display_hex_value = view.findViewById(R.id.calculator_hex_value);
		display_dec_value = view.findViewById(R.id.calculator_dec_value);
		display_oct_value = view.findViewById(R.id.calculator_oct_value);
		display_bin_value = view.findViewById(R.id.calculator_bin_value);

		Button pad_parenthese_open = view.findViewById(R.id.calculator_parenthese_open);
		Button pad_parenthese_close = view.findViewById(R.id.calculator_parenthese_close);
		Button pad_backspace = view.findViewById(R.id.calculator_backspace);
		Button pad_clear = view.findViewById(R.id.calculator_clear);
		Button pad_clear_entry = view.findViewById(R.id.calculator_clear_entry);
		Button pad_mod = view.findViewById(R.id.calculator_mod);
		Button pad_shift = view.findViewById(R.id.calculator_shift);
		Button pad_and = view.findViewById(R.id.calculator_and);
		Button pad_not = view.findViewById(R.id.calculator_not);
		Button pad_xor = view.findViewById(R.id.calculator_xor);
		Button pad_or = view.findViewById(R.id.calculator_or);
		Button pad_rsh = view.findViewById(R.id.calculator_rsh);
		Button pad_lsh = view.findViewById(R.id.calculator_lsh);
		Button pad_mdown = view.findViewById(R.id.calculator_mdown);
		Button pad_ms = view.findViewById(R.id.calculator_ms);
		Button pad_word = view.findViewById(R.id.calculator_word);
		Button pad_binary = view.findViewById(R.id.calculator_binary);
		Button pad_decimal = view.findViewById(R.id.calculator_decimal);
		Button pad_plus_minus = view.findViewById(R.id.calculator_plus_minus);
		Button pad_equal = view.findViewById(R.id.calculator_equal);
		Button pad_plus = view.findViewById(R.id.calculator_plus);
		Button pad_minus = view.findViewById(R.id.calculator_minus);
		Button pad_multiplicaion = view.findViewById(R.id.calculator_multiplication);
		Button pad_division = view.findViewById(R.id.calculator_division);

		binaryList.add((Button) view.findViewById(R.id.calculator_0));
		binaryList.add((Button) view.findViewById(R.id.calculator_1));
		binaryList.add((Button) view.findViewById(R.id.calculator_parenthese_open));
		binaryList.add((Button) view.findViewById(R.id.calculator_parenthese_close));

		octatLst.add((Button) view.findViewById(R.id.calculator_2));
		octatLst.add((Button) view.findViewById(R.id.calculator_3));
		octatLst.add((Button) view.findViewById(R.id.calculator_4));
		octatLst.add((Button) view.findViewById(R.id.calculator_5));
		octatLst.add((Button) view.findViewById(R.id.calculator_6));
		octatLst.add((Button) view.findViewById(R.id.calculator_7));

		decimalList.add((Button) view.findViewById(R.id.calculator_8));
		decimalList.add((Button) view.findViewById(R.id.calculator_9));


		hexadecimalList.add((Button) view.findViewById(R.id.calculator_a));
		hexadecimalList.add((Button) view.findViewById(R.id.calculator_b));
		hexadecimalList.add((Button) view.findViewById(R.id.calculator_c));
		hexadecimalList.add((Button) view.findViewById(R.id.calculator_d));
		hexadecimalList.add((Button) view.findViewById(R.id.calculator_e));
		hexadecimalList.add((Button) view.findViewById(R.id.calculator_f));

		View modSelector[] = {
			view.findViewById(R.id.calculator_hex),
			view.findViewById(R.id.calculator_dec),
			view.findViewById(R.id.calculator_oct),
			view.findViewById(R.id.calculator_bin)
		};

		// Disable Useless Button
		Button pad_decimal_point = view.findViewById(R.id.calculator_decimal_point);
		pad_decimal_point.setClickable(false);
		pad_decimal_point.setEnabled(false);

		/**
		 * Presenter Initialize
		 */
		this.presenter = ProgrammerPresenter.getInstance();
		presenter.setView(this);
		presenter.setConfig(this.config);
		presenter.initialize();

		pad_backspace.setOnClickListener((View v) -> {presenter.backSpace();});

		pad_clear.setOnClickListener((View v) -> {presenter.clear();});

		for (Button bin : binaryList) {
			bin.setOnClickListener((View v) -> presenter.numbarPadListener(v));
		}

		for(Button dec : decimalList) {
			dec.setOnClickListener((View v) -> presenter.numbarPadListener(v));
		}

		for(Button oct : octatLst) {
			oct.setOnClickListener((View v) -> presenter.numbarPadListener(v));
		}

		for(Button hex : hexadecimalList) {
			hex.setOnClickListener((View v) -> presenter.numbarPadListener(v));
		}

		// Initialize Default Mode
		modeUpdator(config.getProgrammerCalculatorMod());

		pad_plus_minus.setOnClickListener((View v) -> presenter.plusMinusChacner(v));

		/**
		 * Mode Selector Initialize
		 */
		for(int i = 0; i < modSelector.length; i ++) {
			modSelector[i].setOnClickListener((View v) -> presenter.modeSelector(v));
		}

		pad_word.setOnClickListener((View v) -> {
			Button button = (Button)v;

			String type = button.getText().toString();

			switch (type) {
				case "BYTE" :
					config.setPROGRAMMER_CALCULATOR_TYPE("word");
					button.setText("WORD");
					break;
				case "WORD" :
					config.setPROGRAMMER_CALCULATOR_TYPE("dword");
					button.setText("DWORD");
					break;
				case "DWORD" :
					config.setPROGRAMMER_CALCULATOR_TYPE("qword");
					button.setText("QWORD");
					break;
				case "QWORD" :
					config.setPROGRAMMER_CALCULATOR_TYPE("byte");
					button.setText("BYTE");
					break;
			}

			presenter.validChecker();
			presenter.numberConverter();
		});

		return view;
	}

	@Override
	public void modeUpdator(String mod) {
		String mode = mod;
		Log.d("mode is : ", mode);

		switch (mode) {
			case "hex" :

				for (Button bin : binaryList) {
					bin.setClickable(true);
					bin.setEnabled(true);
				}

				for(Button dec : decimalList) {
					dec.setClickable(true);
					dec.setEnabled(true);
				}

				for(Button oct : octatLst) {
					oct.setClickable(true);
					oct.setEnabled(true);
				}

				for(Button hex : hexadecimalList) {
					hex.setClickable(true);
					hex.setEnabled(true);
				}

				break;
			case "dec" :

				for (Button bin : binaryList) {
					bin.setClickable(true);
					bin.setEnabled(true);
				}

				for(Button dec : decimalList) {
					dec.setClickable(true);
					dec.setEnabled(true);
				}

				for(Button oct : octatLst) {
					oct.setClickable(true);
					oct.setEnabled(true);
				}

				for(Button hex : hexadecimalList) {
					hex.setClickable(false);
					hex.setEnabled(false);
				}

				break;
			case "oct" :

				for (Button bin : binaryList) {
					bin.setClickable(true);
					bin.setEnabled(true);
				}

				for(Button dec : decimalList) {
					dec.setClickable(false);
					dec.setEnabled(false);
				}

				for(Button oct : octatLst) {
					oct.setClickable(true);
					oct.setEnabled(true);
				}

				for(Button hex : hexadecimalList) {
					hex.setClickable(false);
					hex.setEnabled(false);
				}

				break;
			case "bin" :

				for (Button bin : binaryList) {
					bin.setClickable(true);
					bin.setEnabled(true);
				}

				for(Button dec : decimalList) {
					dec.setClickable(false);
					dec.setEnabled(false);
				}

				for(Button oct : octatLst) {
					oct.setClickable(false);
					oct.setEnabled(false);
				}

				for(Button hex : hexadecimalList) {
					hex.setClickable(false);
					hex.setEnabled(false);
				}
				break;
		}
	}

	@Override
	public void UIUpdator(ProgrammerDomain domain) {

		String calculator_result_display = domain.getCalculator_result_display().toString();
		String calculator_state_display = domain.getCalculator_state_display().toString();
		String calculator_hex_display = domain.getCalculator_hex_display().toString();
		String calculator_dec_display = domain.getCalculator_dec_display().toString();
		String calculator_oct_display = domain.getCalculator_oct_display().toString();
		String calculator_bin_display = domain.getCalculator_bin_display().toString();

		if (!this.display_result.getText().equals(calculator_result_display)) {
			this.display_result.setText(calculator_result_display);
		}

		if (!this.display_state.getText().equals(calculator_state_display)) {
			this.display_state.setText(calculator_state_display);
		}

		if (!this.display_hex_value.getText().equals(calculator_hex_display)) {
			this.display_hex_value.setText(calculator_hex_display);
		}

		if (!this.display_dec_value.getText().equals(calculator_dec_display)) {
			this.display_dec_value.setText(calculator_dec_display);
		}

		if (!this.display_oct_value.getText().equals(calculator_oct_display)) {
			this.display_oct_value.setText(calculator_oct_display);
		}

		if (!this.display_bin_value.getText().equals(calculator_bin_display)) {
			this.display_bin_value.setText(calculator_bin_display);
		}

	}

	@Override
	public void toastMessage(String message){
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}

}
