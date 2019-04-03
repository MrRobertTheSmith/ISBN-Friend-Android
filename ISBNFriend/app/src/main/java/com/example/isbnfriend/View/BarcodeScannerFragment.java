package com.example.isbnfriend.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.example.isbnfriend.R;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class BarcodeScannerFragment extends Fragment {

    //Properties
    CameraSource camSource;
    SurfaceView cameraView;
    private BarcodeDetector bDetector;


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
        final View fragmentView = inflater.inflate(R.layout.fragment_barcode_scanner, container, false);

        //Set up the surface View
        cameraView = fragmentView.findViewById(R.id.surfaceView);

        bDetector = new BarcodeDetector.Builder(getActivity())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        camSource = new CameraSource.Builder(getActivity(), bDetector)
                .setRequestedPreviewSize(1080, 720)
                .build();

        return fragmentView;
    }

}
