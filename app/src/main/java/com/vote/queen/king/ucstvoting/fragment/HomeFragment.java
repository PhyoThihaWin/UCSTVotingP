package com.vote.queen.king.ucstvoting.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.sdsmdg.tastytoast.TastyToast;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;
import com.vote.queen.king.ucstvoting.FullScreenDialog;
import com.vote.queen.king.ucstvoting.LoginActivity;
import com.vote.queen.king.ucstvoting.MainActivity;
import com.vote.queen.king.ucstvoting.R;
import com.vote.queen.king.ucstvoting.SelectionDetailActivity;

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

import static android.content.Context.MODE_PRIVATE;
import static com.vote.queen.king.ucstvoting.MainActivity.oldFragment;
import static com.vote.queen.king.ucstvoting.MainActivity.tvk;
import static com.vote.queen.king.ucstvoting.MainActivity.tvq;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public static TextView resultText;


    SliderLayout sliderLayout;
    private Button btnLogin;
    ArcProgress arcProgress;
    TextView voteUnV;
    ProgressBar noServer;
//    int server;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnLogin = view.findViewById(R.id.btnLogin);
        voteUnV = view.findViewById(R.id.voteUnV);
        arcProgress = view.findViewById(R.id.vote_arc_progress);
        noServer = view.findViewById(R.id.noServer);

        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenDialog dialog = new FullScreenDialog();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                dialog.show(ft, FullScreenDialog.TAG);
            }
        });


        sliderLayout = view.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.WORM); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(2); //set scroll delay in seconds :

        setSliderViews();

        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("vote", MODE_PRIVATE);
        if (sharedPreferences.getString("user", "").equals("")) {
            btnLogin.setText("Log in");
        } else {
            btnLogin.setText("Log out");
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getString("user", "").equals("")) {
                    getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    //---cusotm round dialog
                    LayoutInflater inflater = getLayoutInflater();
                    View v1 = inflater.inflate(R.layout.custom_dialog, null);

                    Button dialogVote = v1.findViewById(R.id.dialgVote);
                    Button dialogCancel = v1.findViewById(R.id.dialogCancel);
                    TextView dialogTxt = v1.findViewById(R.id.dialogTxt);

                    dialogVote.setText("Log out");
                    dialogTxt.setText("Are you sure want to logout?");
                    LottieAnimationView lottieAnimationView = v1.findViewById(R.id.animationView);
                    lottieAnimationView.setAnimation("logout.json");
                    //lottieAnimationView.loop(true);
                    lottieAnimationView.playAnimation();

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.customizedAlert);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.setView(v1);


                    dialogVote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("user", "");
                            editor.putString("king", "");
                            editor.putString("queen", "");
                            editor.putString("qrcode", "");
                            editor.commit();

                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().finish();

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
                }
            }
        });

        getUserCount("0", "getstatus");

        getUserCount("0", "getvote");

        return view;
    }


    private void setSliderViews() {

        for (int i = 0; i <= 4; i++) {

            SliderView sliderView = new SliderView(getContext());

            switch (i) {
                case 0:
                    sliderView.setImageUrl("http://kaungkhantshar.pe.hu/vote/kinggp.jpeg");
                    break;
                case 1:
                    sliderView.setImageUrl("http://kaungkhantshar.pe.hu/vote/queengp.jpeg");
                    break;
                case 2:
                    sliderView.setImageUrl("http://kaungkhantshar.pe.hu/vote/f1.jpeg");
                    break;
                case 3:
                    sliderView.setImageUrl("http://kaungkhantshar.pe.hu/vote/f2.jpeg");
                    break;
                case 4:
                    sliderView.setImageUrl("http://kaungkhantshar.pe.hu/vote/f3.jpeg");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderView.setDescription("Memories of Welcome ");
            final int finalI = i;
            /*sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(getActivity(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });*/

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }


    private void getUserCount(final String qid, final String type) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // loading = ProgressDialog.show(SelectionDetailActivity.this, "Please wait", "Voting....", false, false);
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
                // loading.dismiss();
                //Snackbar.make(null, result, Snackbar.LENGTH_LONG)
                // .setAction("Action", null).show();

                if (type.equals("getvote")) {
                    try {
                        Double count = Double.parseDouble(result);
                        arcProgress.setProgress((int) ((count / 638.0f) * 100));
                        //  Log.i("cccc", (int)((count/20.0f)*100) + "");
                    } catch (Exception e) {
                        arcProgress.setProgress(0);
                    }
                } else if (type.equals("getstatus") && !result.equals("")) {
                    if (Integer.parseInt(result) == 1) {
                        voteUnV.setVisibility(View.GONE);
                        noServer.setVisibility(View.GONE);
                        arcProgress.setVisibility(View.VISIBLE);
                    } else {
                        voteUnV.setVisibility(View.VISIBLE);
                        arcProgress.setVisibility(View.GONE);
                        noServer.setVisibility(View.GONE);
                    }
                } else {
                    voteUnV.setVisibility(View.VISIBLE);
                    noServer.setVisibility(View.GONE);
                }
                // TastyToast.makeText(getActivity(), "No interent connection", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(qid, type);
    }

}
