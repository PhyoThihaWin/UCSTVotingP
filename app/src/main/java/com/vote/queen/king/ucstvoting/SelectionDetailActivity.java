package com.vote.queen.king.ucstvoting;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.sdsmdg.tastytoast.TastyToast;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

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
import java.util.ArrayList;
import java.util.List;

import static com.vote.queen.king.ucstvoting.MainActivity.tvk;
import static com.vote.queen.king.ucstvoting.MainActivity.tvq;

public class SelectionDetailActivity extends AppCompatActivity {

    SliderLayout sliderLayout;
    //SliderView sliderView = new SliderView(SelectionDetailActivity.this);
    ProgressDialog loading;
    String[] index;

    String[] selection;

    int server;

    @Override
    protected void onStart() {
        super.onStart();
        getServer("0", "getstatus"); // get server start stop
    }

    public void getArray(String kq, String i) {
        if (kq.equals("updateking"))
            switch (i) {
                case "1":
                    selection = new String[]{"Mg Htet Myat OO", "Section-B", "No-1", "http://kaungkhantshar.pe.hu/vote/k11.jpeg", "http://kaungkhantshar.pe.hu/vote/k12.jpeg", "http://kaungkhantshar.pe.hu/vote/k13.jpeg"};

                    break;
                case "2":
                    selection = new String[]{"Mg Min Swe Mon", "Section-B", "N0-2", "http://kaungkhantshar.pe.hu/vote/k21.jpeg", "http://kaungkhantshar.pe.hu/vote/k22.jpeg", "http://kaungkhantshar.pe.hu/vote/k23.jpeg"};
                    break;
                case "3":
                    selection = new String[]{"Mg Nay Lin Htun", "Section-C", "No-3", "http://kaungkhantshar.pe.hu/vote/k31.jpeg", "http://kaungkhantshar.pe.hu/vote/k32.jpeg", "http://kaungkhantshar.pe.hu/vote/k33.jpeg"};
                    break;
                case "4":
                    selection = new String[]{"Mg Htet Naing Min", "Section-B", "No-4", "http://kaungkhantshar.pe.hu/vote/k41.jpeg", "http://kaungkhantshar.pe.hu/vote/k42.jpeg", "http://kaungkhantshar.pe.hu/vote/k43.jpeg"};
                    break;
                case "5":
                    selection = new String[]{"Mg Bhone Myat Khaung", "Section-B", "No-5", "http://kaungkhantshar.pe.hu/vote/k51.jpeg", "http://kaungkhantshar.pe.hu/vote/k52.jpeg", "http://kaungkhantshar.pe.hu/vote/k53.jpeg"};
                    break;
                case "6":
                    selection = new String[]{"Mg Paing Zayar Soe", "Section-B", "No-6", "http://kaungkhantshar.pe.hu/vote/k61.jpeg", "http://kaungkhantshar.pe.hu/vote/k62.jpeg", "http://kaungkhantshar.pe.hu/vote/k63.jpeg"};
                    break;
                case "7":
                    selection = new String[]{"Mg Kaung Sett Min", "Section-C", "No-7", "http://kaungkhantshar.pe.hu/vote/k71.jpeg", "http://kaungkhantshar.pe.hu/vote/k72.jpeg", "http://kaungkhantshar.pe.hu/vote/k73.jpeg"};
                    break;
                case "8":
                    selection = new String[]{"Mg Min Oak Soe", "Section-A", "No-8", "http://kaungkhantshar.pe.hu/vote/k81.jpeg", "http://kaungkhantshar.pe.hu/vote/k82.jpeg", "http://kaungkhantshar.pe.hu/vote/k83.jpeg"};
                    break;
            }

        else if (kq.equals("updatequeen"))
            switch (i) {

                case "1":
                    selection = new String[]{"Ma Khine Cherry Soe", "Section-C", "No-1", "http://kaungkhantshar.pe.hu/vote/q11.jpeg", "http://kaungkhantshar.pe.hu/vote/q12.jpeg", "http://kaungkhantshar.pe.hu/vote/q13.jpeg"};
                    break;
                case "2":
                    selection = new String[]{"Nan Cherry Nyein", "Section-C", "N0-2", "http://kaungkhantshar.pe.hu/vote/q21.jpeg", "http://kaungkhantshar.pe.hu/vote/q22.jpeg", "http://kaungkhantshar.pe.hu/vote/q23.jpeg"};
                    break;
                case "3":
                    selection = new String[]{"Ma Win Pa Pa Aung", "Section-C", "No-3", "http://kaungkhantshar.pe.hu/vote/q31.jpeg", "http://kaungkhantshar.pe.hu/vote/q32.jpeg", "http://kaungkhantshar.pe.hu/vote/q33.jpeg"};
                    break;
                case "4":
                    selection = new String[]{"Ma San Pa Pa Aung", "Section-A", "No-4", "http://kaungkhantshar.pe.hu/vote/q41.jpeg", "http://kaungkhantshar.pe.hu/vote/q42.jpeg", "http://kaungkhantshar.pe.hu/vote/q43.jpeg"};
                    break;
                case "5":
                    selection = new String[]{"Ma Moe Thin Khine", "Section-C", "No-5", "http://kaungkhantshar.pe.hu/vote/q51.jpeg", "http://kaungkhantshar.pe.hu/vote/q52.jpeg", "http://kaungkhantshar.pe.hu/vote/q53.jpeg"};
                    break;
                case "6":
                    selection = new String[]{"Ma Yin Mon Htwe", "Section-C", "No-6", "http://kaungkhantshar.pe.hu/vote/q61.jpeg", "http://kaungkhantshar.pe.hu/vote/q62.jpeg", "http://kaungkhantshar.pe.hu/vote/q63.jpeg"};
                    break;
                case "7":
                    selection = new String[]{"Ma May Me Phone", "Section-C", "No-7", "http://kaungkhantshar.pe.hu/vote/q71.jpeg", "http://kaungkhantshar.pe.hu/vote/q72.jpeg", "http://kaungkhantshar.pe.hu/vote/q73.jpeg"};
                    break;
                case "8":
                    selection = new String[]{"Mi Pan Phyu", "Section-B", "No-8", "http://kaungkhantshar.pe.hu/vote/q81.jpeg", "http://kaungkhantshar.pe.hu/vote/q82.jpeg", "http://kaungkhantshar.pe.hu/vote/q83.jpeg"};
                    break;

            }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_detail);


        index = getIntent().getStringExtra("index").split("-");
        getArray(index[0], index[1]);

        Button btnVote = findViewById(R.id.btnVote);
        final SharedPreferences sharedPreferences = getSharedPreferences("vote", MODE_PRIVATE);


        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.WORM); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(3); //set scroll delay in seconds :

        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight() / 2;
        //sliderLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height - getStatusBarHeight()));
        setSliderViews();


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        TextView name = findViewById(R.id.name);
        name.setText(selection[0]);
        TextView section = findViewById(R.id.section);
        section.setText(selection[1]);
        TextView rollno = findViewById(R.id.rollno);
        rollno.setText(selection[2]);

        if (sharedPreferences.getString("user", "").equals("")) {
            btnVote.setEnabled(true);
            btnVote.setText("Please Login");

        } else if (index[0].equals("updateking"))
            if (sharedPreferences.getString("king", "").equals("0"))
                btnVote.setEnabled(true);
            else {
                btnVote.setEnabled(false);
                btnVote.setText("Voted");
            }
        else if (index[0].equals("updatequeen"))
            if (sharedPreferences.getString("queen", "").equals("0"))
                btnVote.setEnabled(true);
            else {
                btnVote.setEnabled(false);
                btnVote.setText("Voted");
            }

        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sharedPreferences.getString("user", "").equals(""))
                    startActivity(new Intent(SelectionDetailActivity.this, LoginActivity.class));

                else if (isNetworkConnected() && server == 0) {
                    getServer("0", "getstatus"); // get server start stop

                } else if (isNetworkConnected() && index.length > 0) {
                    //---cusotm round dialog
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.custom_dialog, null);

                    Button dialogVote = view.findViewById(R.id.dialgVote);
                    Button dialogCancel = view.findViewById(R.id.dialogCancel);
                    TextView dialogTxt = view.findViewById(R.id.dialogTxt);

                    AlertDialog.Builder builder = new AlertDialog.Builder(SelectionDetailActivity.this, R.style.customizedAlert);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.setView(view);

                    dialogVote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            UpdateKQ(sharedPreferences.getString("qrcode", ""), index[0], index[1]);//signal,king count
                        }
                    });

                    dialogCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

                    alertDialog.getWindow().getAttributes().windowAnimations = R.anim.dialog_enter;
                    alertDialog.show();
                } else
                    TastyToast.makeText(SelectionDetailActivity.this, "No internet connection", Toast.LENGTH_SHORT, TastyToast.ERROR).show();

            }
        });

        findViewById(R.id.f).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneFb();
            }
        });
        findViewById(R.id.p).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneFb();
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private void setSliderViews() {

        for (int i = 0; i <= 2; i++) {
            SliderView sliderView = new SliderView(SelectionDetailActivity.this);
            switch (i) {
                case 0:
                    sliderView.setImageUrl(selection[3]);
                    break;
                case 1:
                    sliderView.setImageUrl(selection[4]);
                    break;
                case 2:
                    sliderView.setImageUrl(selection[5]);
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderView.setDescription("Photo " + (i + 1));
            final int finalI = i;
          /*  sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(SelectionDetailActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });*/

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }

    private void UpdateKQ(final String qid, final String type, final String kq) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SelectionDetailActivity.this, "Please wait", "Voting....", false, false);
            }

            @Override
            protected String doInBackground(String... params) {
                String qid = params[0];
                String type = params[1];
                String kq = params[2];

                Log.i("qrcode", qid + type + kq);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("qid", qid));
                nameValuePairs.add(new BasicNameValuePair("type", type));
                //nameValuePairs.add(new BasicNameValuePair("to", kq));

                if (index[0].equals("updateking"))
                    nameValuePairs.add(new BasicNameValuePair("kid", kq));//king or queen no
                else nameValuePairs.add(new BasicNameValuePair("queenid", kq));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://minhtootintaung.com/votingdata/votelogin.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                } catch (ClientProtocolException e) {
                } catch (IOException e) {
                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                loading.dismiss();
                //Snackbar.make(null, result, Snackbar.LENGTH_LONG)
                // .setAction("Action", null).show();

                //resave to king queen to sharepreference
                SharedPreferences sharedPreferences = getSharedPreferences("vote", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (index[0].equals("updateking")) {
                    editor.putString("king", index[1]);

                    tvk.setText("Voted");

                } else {
                    editor.putString("queen", index[1]);

                    tvq.setText("Voted");
                }
                editor.commit();


                Log.i("hhhh", sharedPreferences.getString("king", "") + " : " + sharedPreferences.getString("queen", ""));
                Log.i("hhhh", result);

                startActivity(new Intent(SelectionDetailActivity.this, MainActivity.class));
                TastyToast.makeText(getApplicationContext(), "Voting Successful", Toast.LENGTH_LONG, TastyToast.SUCCESS).show();
                finish();
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(qid, type, kq);
    }


    private void getServer(final String qid, final String type) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SelectionDetailActivity.this, "Please wait", "Checking server....", false, false);
            }

            @Override
            protected String doInBackground(String... params) {
                String qid = params[0];
                String type = params[1];
                //String kq = params[2];

                // Log.i("qrcode", qid + type + kq);

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("qid", qid));
                nameValuePairs.add(new BasicNameValuePair("type", type));
                //nameValuePairs.add(new BasicNameValuePair("to", kq));

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
                //Snackbar.make(null, result, Snackbar.LENGTH_LONG)
                // .setAction("Action", null).show();
                try {
                    server = Integer.parseInt(result);
                    if (server == 0)
                        TastyToast.makeText(SelectionDetailActivity.this, "Voting unavailable", TastyToast.LENGTH_SHORT, TastyToast.INFO).show();
                } catch (Exception e) {
                    // TastyToast.makeText(SelectionDetailActivity.this, "No", TastyToast.LENGTH_SHORT, TastyToast.ERROR).show();
                }

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(qid, type);
    }

    public void phoneFb() {
        //---cusotm round dialog
        LayoutInflater inflater = getLayoutInflater();
        View v1 = inflater.inflate(R.layout.custom_dialog, null);

        Button dialogVote = v1.findViewById(R.id.dialgVote);
        Button dialogCancel = v1.findViewById(R.id.dialogCancel);
        TextView dialogTxt = v1.findViewById(R.id.dialogTxt);

        LottieAnimationView lottieAnimationView = v1.findViewById(R.id.animationView);
        lottieAnimationView.setAnimation("deadpool.json");
        //lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        AlertDialog.Builder builder = new AlertDialog.Builder(SelectionDetailActivity.this, R.style.customizedAlert);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(v1);

        dialogCancel.setVisibility(View.GONE);
        dialogVote.setVisibility(View.GONE);
        dialogTxt.setTextSize(15);
        dialogTxt.setText("Sorry guy.. We also don't know \uD83D\uDE1D\uD83D\uDE1D\uD83D\uDE1D");

        alertDialog.getWindow().getAttributes().windowAnimations = R.anim.dialog_enter;
        alertDialog.show();
    }
}
