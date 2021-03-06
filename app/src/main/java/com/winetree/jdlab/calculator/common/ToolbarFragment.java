package com.winetree.jdlab.calculator.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import com.winetree.jdlab.calculator.R;
import com.winetree.jdlab.calculator.data.DataActivity;
import com.winetree.jdlab.calculator.standard.StandardActivity;

public class ToolbarFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    private AppCompatActivity activity;
    public static ToolbarFragment newInstance(){return new ToolbarFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_toolbar, container, false);
        activity = (AppCompatActivity)getActivity();

        // toolbar initialize
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);

        // DrawLayout Initialize
        DrawerLayout drawer = (DrawerLayout)view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // NavigationView Initialize
        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        return view;
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager manager = activity.getSupportFragmentManager();

        if (id == R.id.nav_standard) {
            Intent intent = new Intent(activity, StandardActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_date) {
            Intent intent = new Intent(activity, DataActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
