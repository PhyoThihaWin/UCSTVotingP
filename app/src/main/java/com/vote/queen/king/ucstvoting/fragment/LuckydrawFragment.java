package com.vote.queen.king.ucstvoting.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sdsmdg.tastytoast.TastyToast;
import com.vote.queen.king.ucstvoting.R;
import com.vote.queen.king.ucstvoting.ScanActivity;

import static android.content.Context.MODE_PRIVATE;


public class LuckydrawFragment extends Fragment {

    private ImageView imageViewScanner;
    Bitmap bitmap;


    public static LinearLayout linearLayoutLogin, linearLayoutLogout;
    public static ImageView img_qr;


    private static final int ZBAR_CAMERA_PERMISSION = 1;
    private Class<?> mClss;

    //public final static int QRcodeWidth = 300;

    public LuckydrawFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_luckydraw, container, false);
       // img_qr = view.findViewById(R.id.img_qr);
        //
        // linearLayoutLogin = view.findViewById(R.id.login);
        //linearLayoutLogout = view.findViewById(R.id.logout);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("vote", MODE_PRIVATE);
        if (!sharedPreferences.getString("user", "").equals("")) {
            //linearLayoutLogout.setVisibility(View.GONE);
            QRGenerator(sharedPreferences.getString("qrcode", "fresherwelcome"));
        } else {
            //linearLayoutLogin.setVisibility(View.GONE);
        }


        view.findViewById(R.id.img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkConnected()) {
                    launchActivity(ScanActivity.class);
                } else
                    TastyToast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT, TastyToast.ERROR).show();
            }
        });

        return view;
    }

    public void QRGenerator(String code) {
        //----QR code generator---//
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(code, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            // img_qr.setImageBitmap(bmp);
            Glide.with(getActivity()).load(bmp).into(img_qr);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA}, ZBAR_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(getContext(), clss);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case ZBAR_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (mClss != null) {
                        Intent intent = new Intent(getContext(), mClss);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getContext(), "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
                return;
        }

    }

   /* public void RefreshFragment() {
        Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.container);
        Log.i("currentFragment :", currentFragment.toString());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
        transaction.detach(currentFragment).attach(currentFragment).commit();
    }*/
}

//        imageViewScanner = view.findViewById(R.id.imv_qr);
//
//                    try {
//                        bitmap = TextToImageEncode(ScanActivity.code.toString());
//                        imageViewScanner.setImageBitmap(bitmap);
//
//
//                    } catch (WriterException e) {
//                        e.printStackTrace();
//                    }
//
//
//    }
//
//    private Bitmap TextToImageEncode(String Value) throws WriterException {
//        BitMatrix bitMatrix;
//        try {
//            bitMatrix = new MultiFormatWriter().encode(
//                    Value,
//                    BarcodeFormat.DATA_MATRIX.QR_CODE,
//                    QRcodeWidth, QRcodeWidth, null
//            );
//
//        } catch (IllegalArgumentException Illegalargumentexception) {
//
//            return null;
//        }
//        int bitMatrixWidth = bitMatrix.getWidth();
//
//        int bitMatrixHeight = bitMatrix.getHeight();
//
//        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];
//
//        for (int y = 0; y < bitMatrixHeight; y++) {
//            int offset = y * bitMatrixWidth;
//
//            for (int x = 0; x < bitMatrixWidth; x++) {
//
//                pixels[offset + x] = bitMatrix.get(x, y) ?
//                        getResources().getColor(R.color.black) : getResources().getColor(R.color.white);
//            }
//        }
//        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
//
//        bitmap.setPixels(pixels, 0, 300, 0, 0, bitMatrixWidth, bitMatrixHeight);
//        return bitmap;
//    }


