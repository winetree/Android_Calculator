package com.winetree.jdlab.calculator;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.winetree.jdlab.calculator.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter.Views {

  private MainPresenter mainPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // 데이터 바인딩
    final ActivityMainBinding bind = DataBindingUtil.setContentView(this, R.layout.activity_main);

    // Presenter Setup
    mainPresenter = new MainPresenterImpl(bind);
    mainPresenter.setViews(this);

    List<Button> numberPad = new ArrayList<>();

    numberPad.add(bind.button0);
    numberPad.add(bind.button1);
    numberPad.add(bind.button2);
    numberPad.add(bind.button3);
    numberPad.add(bind.button4);
    numberPad.add(bind.button5);
    numberPad.add(bind.button6);
    numberPad.add(bind.button7);
    numberPad.add(bind.button8);
    numberPad.add(bind.button9);


    Button plusMinus = bind.buttonPlusMinus;
    Button backSpace = bind.buttonBackspace;
    Button decimalPoint = bind.buttonDecimalPoint;
    Button clear = bind.buttonClear;
    Button clearEntry = bind.buttonClearEntry;
    Button division = bind.buttonDivision;
    Button multiplication = bind.buttonMultiplication;
    Button plus = bind.buttonPlus;
    Button minus = bind.buttonMinus;
    Button calculate = bind.buttonCalculate;
    Button percentage = bind.buttonPercent;
    Button root = bind.buttonRoot;
    Button square = bind.buttonSquare;
    Button division1 = bind.buttonDivision1;

    /**
     * 넘버패드 클릭 이벤트 제어
     */
    for (int i = 0; i < numberPad.size(); i++) {
      numberPad.get(i).setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
          mainPresenter.numberPadHandler(v);
        }
      });
    }

    /**
     * 플러스 마이너스 전환 클릭 이벤트 제어
     */
    plusMinus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mainPresenter.plusMinus();
      }
    });

    /**
     * 백스페이스 버튼 클릭 이벤트 제어
     */
    backSpace.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mainPresenter.backSpace();
      }
    });

    /**
     * 소수점 추가 버튼 클릭 이벤트 제어
     */
    decimalPoint.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        mainPresenter.decimalPoint();
      }
    });

    /**
     * 데이터 전체 삭제
     */
    clear.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        mainPresenter.clear();
      }
    });

    /**
     * 현재 입력값 삭제
     */
    clearEntry.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        mainPresenter.clearEntry();
      }
    });

    /**
     * 나누기 버튼 클릭 이벤트 제어
     */
    division.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        mainPresenter.division();
      }
    });

    /**
     * 곱하기
     */
    multiplication.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mainPresenter.multiplication();
      }
    });

    /**
     * 더하기
     */
    plus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mainPresenter.plus();
      }
    });

    /**
     * 빼기
     */
    minus.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        mainPresenter.minus();
      }
    });

    /**
     * 계산
     */
    calculate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mainPresenter.calculate();
      }
    });

    /**
     * 퍼센티지
     */
    percentage.setOnClickListener(new View.OnClickListener(){
    	@Override
	    public void onClick(View v){
    	  mainPresenter.percentage();
	    }
    });
    /**
     * 근
     */
    root.setOnClickListener(new View.OnClickListener(){
    	@Override
	    public void onClick(View v){
    	  mainPresenter.root();
	    }
    });
    /**
     * 제곱
     */
    square.setOnClickListener(new View.OnClickListener(){
    	@Override
	    public void onClick(View v){
    	  mainPresenter.square();
	    }
    });
    /**
     * 백분율
     */
    division1.setOnClickListener(new View.OnClickListener(){
    	@Override
	    public void onClick(View v){
    	  mainPresenter.division();
	    }
    });

  }

  /**
   * CALLBACK
   */

  @Override
  public void setConfirmText(String text) {

  }

}
