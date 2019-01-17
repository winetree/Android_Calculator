package com.winetree.jdlab.calculator.programmer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.winetree.jdlab.calculator.R;
import com.winetree.jdlab.calculator.domain.ProgrammerDomain;

public class ProgrammerFragment extends Fragment implements ProgrammerContract.View {

    private AppCompatActivity appCompatActivity;
    private ProgrammerContract.Presenter presenter;

    private TextView display_result;
    private TextView display_state;

    private TextView display_hex;
    private TextView display_dec;
    private TextView display_oct;
    private TextView display_bin;

    public static ProgrammerFragment newInstance() {
        return new ProgrammerFragment();
    }

    @Override
    public void setPresenter(ProgrammerContract.Presenter presenter) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.programmer_fragment, container, false);

        this.appCompatActivity = (AppCompatActivity) getActivity();

        display_result = view.findViewById(R.id.calculator_result_display);
        display_state = view.findViewById(R.id.calculator_state_display);

        display_hex = view.findViewById(R.id.calculator_hex_display);
        display_dec = view.findViewById(R.id.calculator_dec_display);
        display_oct = view.findViewById(R.id.calculator_oct_display);
        display_bin = view.findViewById(R.id.calculator_bin_display);

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

        Button pad_mdown = view.findViewById(R.id.calculator_lsh);
        Button pad_ms = view.findViewById(R.id.calculator_lsh);
        Button pad_word = view.findViewById(R.id.calculator_lsh);
        Button pad_binary = view.findViewById(R.id.calculator_binary);

        Button pad_decimal = view.findViewById(R.id.calculator_decimal);
        Button pad_plus_minus = view.findViewById(R.id.calculator_plus_minus);
        Button pad_equal = view.findViewById(R.id.calculator_equal);
        Button pad_plus = view.findViewById(R.id.calculator_plus);
        Button pad_minus = view.findViewById(R.id.calculator_minus);
        Button pad_multiplicaion = view.findViewById(R.id.calculator_multiplication);
        Button pad_division = view.findViewById(R.id.calculator_division);


        Button padArray[] = {
                view.findViewById(R.id.calculator_decimal_point),
                view.findViewById(R.id.calculator_0),
                view.findViewById(R.id.calculator_1),
                view.findViewById(R.id.calculator_2),
                view.findViewById(R.id.calculator_3),
                view.findViewById(R.id.calculator_4),
                view.findViewById(R.id.calculator_5),
                view.findViewById(R.id.calculator_6),
                view.findViewById(R.id.calculator_7),
                view.findViewById(R.id.calculator_8),
                view.findViewById(R.id.calculator_9),
                view.findViewById(R.id.calculator_a),
                view.findViewById(R.id.calculator_b),
                view.findViewById(R.id.calculator_c),
                view.findViewById(R.id.calculator_d),
                view.findViewById(R.id.calculator_e),
                view.findViewById(R.id.calculator_f)
        };

        this.presenter = ProgrammerPresenter.getInstance();
        presenter.setView(this);
        presenter.initialize();

        pad_parenthese_open.setOnClickListener((View v) -> Toast.makeText(getActivity(), "df", Toast.LENGTH_SHORT).show());

        /**
         * 입력 키 이벤트 핸들링
         */
        for(int i = 0; i < padArray.length; i ++) {
            padArray[i].setOnClickListener((View v) -> presenter.numbarPadListener(v));
        }

        return view;
    }

    @Override
    public void UIUpdator(ProgrammerDomain domain) {

        String calculator_result_display = domain.getCalculator_result_display();
        String calculator_state_display = domain.getCalculator_state_display();
        String calculator_hex_display = domain.getCalculator_hex_display();
        String calculator_dec_display = domain.getCalculator_dec_display();
        String calculator_oct_display = domain.getCalculator_oct_display();
        String calculator_bin_display = domain.getCalculator_bin_display();

        if (!this.display_result.getText().equals(calculator_result_display)) {
            this.display_result.setText(calculator_result_display);
        }

        if (!this.display_state.getText().equals(calculator_state_display)) {
            this.display_state.setText(calculator_state_display);
        }

        if (!this.display_hex.getText().equals(calculator_hex_display)) {
            this.display_hex.setText(calculator_hex_display);
        }

        if (!this.display_dec.getText().equals(calculator_dec_display)) {
            this.display_dec.setText(calculator_dec_display);
        }

        if (!this.display_oct.getText().equals(calculator_oct_display)) {
            this.display_oct.setText(calculator_oct_display);
        }

        if (!this.display_bin.getText().equals(calculator_bin_display)) {
            this.display_bin.setText(calculator_bin_display);
        }

    }

}
