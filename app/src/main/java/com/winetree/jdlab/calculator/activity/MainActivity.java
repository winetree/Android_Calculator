package com.winetree.jdlab.calculator.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.winetree.jdlab.calculator.presenter.MainPresenter;
import com.winetree.jdlab.calculator.presenter.MainPresenterImpl;
import com.winetree.jdlab.calculator.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter.Views, NavigationView.OnNavigationItemSelectedListener {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 데이터 바인딩
        setContentView(R.layout.activity_main);

        // Presenter Setup
        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.setViews(this);

        //action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        List<Button> numberPad = new ArrayList<>();

        numberPad.add((Button) findViewById(R.id.button_0));
        numberPad.add((Button) findViewById(R.id.button_1));
        numberPad.add((Button) findViewById(R.id.button_2));
        numberPad.add((Button) findViewById(R.id.button_3));
        numberPad.add((Button) findViewById(R.id.button_4));
        numberPad.add((Button) findViewById(R.id.button_5));
        numberPad.add((Button) findViewById(R.id.button_6));
        numberPad.add((Button) findViewById(R.id.button_7));
        numberPad.add((Button) findViewById(R.id.button_8));
        numberPad.add((Button) findViewById(R.id.button_9));

        TextView resultText = findViewById(R.id.result);
        TextView operatorText = findViewById(R.id.operator);
        TextView stateText = findViewById(R.id.state);

        Button plusMinus = findViewById(R.id.button_plus_minus);
        Button backSpace = findViewById(R.id.button_backspace);
        Button decimalPoint = findViewById(R.id.button_decimal_point);
        Button clear = findViewById(R.id.button_clear);
        Button clearEntry = findViewById(R.id.button_clear_entry);
        Button division = findViewById(R.id.button_division);
        Button multiplication = findViewById(R.id.button_multiplication);
        Button plus = findViewById(R.id.button_plus);
        Button minus = findViewById(R.id.button_minus);
        Button calculate = findViewById(R.id.button_calculate);
        Button percentage = findViewById(R.id.button_percent);
        Button root = findViewById(R.id.button_root);
        Button square = findViewById(R.id.button_square);
        Button division1 = findViewById(R.id.button_division_1);



        /**
         * 넘버패드 클릭 이벤트 제어
         */
        for (int i = 0; i < numberPad.size(); i++) {
            numberPad.get(i).setOnClickListener(new View.OnClickListener() {
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
        decimalPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.decimalPoint();
            }
        });

        /**
         * 데이터 전체 삭제
         */
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.clear();
            }
        });

        /**
         * 현재 입력값 삭제
         */
        clearEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.clearEntry();
            }
        });

        /**
         * 나누기 버튼 클릭 이벤트 제어
         */
        division.setOnClickListener(new View.OnClickListener() {
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
        minus.setOnClickListener(new View.OnClickListener() {
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
        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.percentage();
            }
        });
        /**
         * 근
         */
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.root();
            }
        });
        /**
         * 제곱
         */
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.square();
            }
        });
        /**
         * 백분율
         */
        division1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.division1();
            }
        });

    }

    /**
     * CALLBACK
     */

    @Override
    public void setConfirmText(String text) {

    }

    /**
     * Action Bar
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.standard) {


        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
