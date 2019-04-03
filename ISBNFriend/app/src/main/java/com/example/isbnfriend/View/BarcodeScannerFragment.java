package com.example.isbnfriend.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.isbnfriend.R;

public class BarcodeScannerFragment extends Fragment {

    //Factory Method
    public static BarcodeScannerFragment newInstance(){
        return new BarcodeScannerFragment();
    }

    public BarcodeScannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_barcode_scanner, container, false);
    }

}
