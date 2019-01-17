package com.winetree.jdlab.calculator.data;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winetree.jdlab.calculator.R;

public class DataFragment extends Fragment implements DataContract.View {

    public static DataFragment newInstance() {
        return new DataFragment();
    }

    @Override
    public void setPresenter(DataContract.Presenter presenter) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_fragment, container, false);


        return view;
    }
}
