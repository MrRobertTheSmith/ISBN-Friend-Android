package com.example.isbnfriend.View;

import android.Manifest;
import android.content.Context;
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

    //Interface
    BarcodeScannerFragmentInterface scannerInterface;

    //This method initialises new fragments
    public static BarcodeScannerFragment newInstance() {
        return new BarcodeScannerFragment();
    }

    public BarcodeScannerFragment() {
        // Required empty public constructor
    }

    //LifecycleMethods
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //As long as the activity extends the inteface link 'scannerInterface' to the
        //activity.
        if (context instanceof BarcodeScannerFragmentInterface) {
            scannerInterface = (BarcodeScannerFragmentInterface) context;
        }
        else{
            throw new ClassCastException(context.toString() + getString(R.string.Fragment_Launch_Error_Message));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragmentView = inflater.inflate(R.layout.fragment_barcode_scanner, container, false);

        cameraView = fragmentView.findViewById(R.id.surfaceView);

        //Set up the barcode detector to scan for all formats
        bDetector = new BarcodeDetector.Builder(getActivity())
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        //Add new Detector.Processor and watch for detections
        bDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections detections) {

                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                //If at least one item is returned cal the interface method passing in the barcode as a string.
                if(barcodes.size() > 0){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            scannerInterface.passBackISBN(barcodes.valueAt(0).displayValue);
                        }
                    });
                }
            }
        });

        //Set up a new CameraSource
        camSource = new CameraSource.Builder(getActivity(), bDetector)
                .setRequestedPreviewSize(1024, 1680)
                .setAutoFocusEnabled(true)
                .build();

        //Link it to the View if the User has given permission
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        Log.e("CAMERASOURCE", "No Camera Permission");
                        return;
                    }
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
        camSource.stop();
        camSource.release();
        bDetector.release();
    }
}
