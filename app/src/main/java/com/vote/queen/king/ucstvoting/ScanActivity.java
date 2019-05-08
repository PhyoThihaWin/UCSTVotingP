package com.vote.queen.king.ucstvoting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sdsmdg.tastytoast.TastyToast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView ScannerView;
    ProgressDialog loading;

    String code = "";
    String text = "";

//    private DatabaseReference databaseReference;
//    private FirebaseAuth mfirebaseAuth;
//    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);


    }


    @Override
    public void handleResult(Result result) {
        code += result.toString();
        // Log.i("ii", code);

        try {
            byte[] data = Base64.decode(Base64.decode(code, Base64.DEFAULT), Base64.DEFAULT);
            text = new String(data, "UTF-8");

            //---Login to server---//
            String[] ss = text.split("-");
            Log.i("rrrr",text);
            if (ss.length == 2)
                if (100 < Integer.parseInt(ss[0]) && 850 > Integer.parseInt(ss[0])
                        && 100 <= Integer.parseInt(ss[1]) && 750 >= Integer.parseInt(ss[1])) {

                    MediaPlayer mediaPlayer = MediaPlayer.create(ScanActivity.this, R.raw.success);
                    mediaPlayer.start();
                    LoginToDatabase("insert", text);
                    //TastyToast.makeText(this, "Login Successful", Toast.LENGTH_SHORT, TastyToast.SUCCESS).show();
                    //startActivity(new Intent(getApplicationContext(),MainActivity.class));

                } else {
                    onBackPressed();
                    TastyToast.makeText(this, "Incorrect QR Code", Toast.LENGTH_SHORT, TastyToast.ERROR).show();

                }

        } catch (Exception e) {
            onBackPressed();
            Toast.makeText(this, "Incorrect QR Code", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        ScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();

    }


    private void LoginToDatabase(String name, String count) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ScanActivity.this, "Please wait...", "uploading", false, false);
            }

            @Override
            protected String doInBackground(String... params) {
                String type = params[0];
                String qid = params[1];

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("type", type));
                nameValuePairs.add(new BasicNameValuePair("qid", qid));
                String str = "";
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://minhtootintaung.com/votingdata/votelogin.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    str = bufferedReader.readLine();
                } catch (ClientProtocolException e) {
                } catch (IOException e) {
                }
                return str;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                loading.dismiss();

                //---save user king queen---//
                String[] str = result.replaceAll(" ", "").split("-");
                Log.i("ssss", result);
                if (str[0].equals("fail")) {

                    SharedPreferences sharedPreferences = getSharedPreferences("vote", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", str[0]);//1-0-0
                    editor.putString("king", str[1]);//fail-0-0
                    editor.putString("queen", str[2]);//fail-0-0
                    editor.putString("qrcode", text);
                    editor.commit();

                } else if (str[0].equals("success")) {
                    SharedPreferences sharedPreferences = getSharedPreferences("vote", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", str[0]);//1-0-0
                    editor.putString("king", "0");//fail-0-0
                    editor.putString("queen", "0");//fail-0-0
                    editor.putString("qrcode", text);
                    editor.commit();
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("vote", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", "");
                    editor.commit();

                }

                //TastyToast.makeText(ScanActivity.this, code, Toast.LENGTH_LONG, TastyToast.SUCCESS).show();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finishAffinity();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(name, count);
    }

//    public void QRGenerator(String code) {
//        //----QR code generator---//
//        QRCodeWriter writer = new QRCodeWriter();
//        try {
//            BitMatrix bitMatrix = writer.encode(code, BarcodeFormat.QR_CODE, 512, 512);
//            int width = bitMatrix.getWidth();
//            int height = bitMatrix.getHeight();
//            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//            for (int x = 0; x < width; x++) {
//                for (int y = 0; y < height; y++) {
//                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
//                }
//            }
//            // img_qr.setImageBitmap(bmp);
//            Glide.with(getApplicationContext()).load(bmp).into(img
// _qr);
//
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
}





