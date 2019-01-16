package com.winetree.jdlab.calculator.standard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.winetree.jdlab.calculator.R;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class StandardFragment extends Fragment implements StandardContract.View {

    public static StandardFragment newInstance(){return new StandardFragment();}

    private StandardContract.Presenter standardPresenter;

    @Override
    public void setPresenter(@NonNull StandardContract.Presenter presenter) {
        standardPresenter = checkNotNull(presenter);
    }

    @Override
    public void UIUpdator() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.standard, container, false);

        setPresenter(new StandardPresenter(view, this));

        List<Button> numberPad = new ArrayList<>();
        numberPad.add((Button) view.findViewById(R.id.button_0));
        numberPad.add((Button) view.findViewById(R.id.button_1));
        numberPad.add((Button) view.findViewById(R.id.button_2));
        numberPad.add((Button) view.findViewById(R.id.button_3));
        numberPad.add((Button) view.findViewById(R.id.button_4));
        numberPad.add((Button) view.findViewById(R.id.button_5));
        numberPad.add((Button) view.findViewById(R.id.button_6));
        numberPad.add((Button) view.findViewById(R.id.button_7));
        numberPad.add((Button) view.findViewById(R.id.button_8));
        numberPad.add((Button) view.findViewById(R.id.button_9));

        TextView resultText = view.findViewById(R.id.result);
        TextView operatorText = view.findViewById(R.id.operator);
        TextView stateText = view.findViewById(R.id.state);

        Button plusMinus = view.findViewById(R.id.button_plus_minus);
        Button backSpace = view.findViewById(R.id.button_backspace);
        Button decimalPoint = view.findViewById(R.id.button_decimal_point);
        Button clear = view.findViewById(R.id.button_clear);
        Button clearEntry = view.findViewById(R.id.button_clear_entry);
        Button division = view.findViewById(R.id.button_division);
        Button multiplication = view.findViewById(R.id.button_multiplication);
        Button plus = view.findViewById(R.id.button_plus);
        Button minus = view.findViewById(R.id.button_minus);
        Button calculate = view.findViewById(R.id.button_calculate);
        Button percentage = view.findViewById(R.id.button_percent);
        Button root = view.findViewById(R.id.button_root);
        Button square = view.findViewById(R.id.button_square);
        Button division1 = view.findViewById(R.id.button_division_1);

        /**
         * 넘버패드 클릭 이벤트 제어
         */
        for (int i = 0; i < numberPad.size(); i++) {
            numberPad.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    standardPresenter.numberPadHandler(v);
                }
            });
        }

        /**
         * 플러스 마이너스 전환 클릭 이벤트 제어
         */
        plusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.plusMinus();
            }
        });

        /**
         * 백스페이스 버튼 클릭 이벤트 제어
         */
        backSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.backSpace();
            }
        });

        /**
         * 소수점 추가 버튼 클릭 이벤트 제어
         */
        decimalPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.decimalPoint();
            }
        });

        /**
         * 데이터 전체 삭제
         */
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.clear();
            }
        });

        /**
         * 현재 입력값 삭제
         */
        clearEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.clearEntry();
            }
        });

        /**
         * 나누기 버튼 클릭 이벤트 제어
         */
        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.division();
            }
        });

        /**
         * 곱하기
         */
        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.multiplication();
            }
        });

        /**
         * 더하기
         */
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.plus();
            }
        });

        /**
         * 빼기
         */
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.minus();
            }
        });

        /**
         * 계산
         */
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.calculate();
            }
        });

        /**
         * 퍼센티지
         */
        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.percentage();
            }
        });
        /**
         * 근
         */
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.root();
            }
        });
        /**
         * 제곱
         */
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.square();
            }
        });
        /**
         * 백분율
         */
        division1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standardPresenter.division1();
            }
        });
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

}
