package com.example.isbnfriend.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.*;

import com.example.isbnfriend.R;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class BarcodeScannerFragment extends Fragment {

    //Properties
    CameraSource camSource;
    SurfaceView cameraView;
    private BarcodeDetector bDetector;

    //Factory Method
    public static BarcodeScannerFragment newInstance() {
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

        bDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray barcodes = detections.getDetectedItems();

                if (barcodes.size() > 0){
                }
            }
        });

        camSource = new CameraSource.Builder(getActivity(), bDetector)
                .setRequestedPreviewSize(1024, 1680)
                .setAutoFocusEnabled(true)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        Log.e("CAMERASOURCE", "No Camera Permission");
                        return;
                    }
                    Log.e("CAMERASOURCE", "Camera Should Start");
                    camSource.start(cameraView.getHolder());
                }
                catch(IOException ie){
                    Log.e("CAMERASOURCE", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                camSource.stop();
            }
        });
        return fragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        camSource.release();
        bDetector.release();
    }
}
