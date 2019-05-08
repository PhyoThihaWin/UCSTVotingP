package com.vote.queen.king.ucstvoting;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ScanRegActivity extends AppCompatActivity {

    String code;
    // FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_reg);


        //   firebaseAuth = FirebaseAuth.getInstance();

        Random rng = new Random(); // Ideally just create one instance globally
        List<Integer> generated = new ArrayList<Integer>();


        for (int i = 100; i < 750; i++) {

            int next = rng.nextInt(750) + 100;
            code = next + "-" + i;
            Log.i("Ran", code);


           /* firebaseAuth.createUserWithEmailAndPassword(code + "@gmail.com", code).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ScanRegActivity.this, "Registration is successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ScanRegActivity.this, "Somethin is wrong", Toast.LENGTH_SHORT).show();
                    }

                }
            });*/


            //----QR code generator---//
            QRCodeWriter writer = new QRCodeWriter();
            try {
                BitMatrix bitMatrix = writer.encode(Base64.encodeToString(Base64.encodeToString(code.getBytes(), Base64.DEFAULT).getBytes(), Base64.DEFAULT), BarcodeFormat.QR_CODE, 512, 512);
                int width = bitMatrix.getWidth();
                int height = bitMatrix.getHeight();
                Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                    }
                }
                //((ImageView) findViewById(R.id.img_result_qr)).setImageBitmap(bmp);

                //---save QR code to phone storage---//
                storeImage(bmp, i + ".png");

            } catch (WriterException e) {
                e.printStackTrace();
            }

        }

    }

    /*  //---create user in firebase auth---//
      public void createUser(String code) {
          firebaseAuth.createUserWithEmailAndPassword(code + "@gmail.com", code).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()) {
                      Toast.makeText(ScanRegActivity.this, "Registration is successful", Toast.LENGTH_SHORT).show();
                  } else {
                      Toast.makeText(ScanRegActivity.this, "Somethin is wrong", Toast.LENGTH_SHORT).show();
                  }

              }
          });
      }
  */
    //----QR code image save function---//
    public boolean storeImage(Bitmap imageData, String filename) {
        // get path to external storage (SD card)
        File file = null;
        file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/QR Code/");
        // create storage directories, if they don't exist
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            String filePath = file + File.separator + filename;
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
            //Toast.makeText(m_cont, "Image Saved at----" + filePath, Toast.LENGTH_LONG).show();
            // choose another format if PNG doesn't suit you
            imageData.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();

        } catch (FileNotFoundException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        } catch (IOException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        }
        return true;
    }
}
