package com.vote.queen.king.ucstvoting;

import android.Manifest;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.evolve.backdroplibrary.BackdropContainer;
import com.vote.queen.king.ucstvoting.fragment.AboutFragment;
import com.vote.queen.king.ucstvoting.fragment.GameFragment;
import com.vote.queen.king.ucstvoting.fragment.HomeFragment;
import com.vote.queen.king.ucstvoting.fragment.KingFragment;
import com.vote.queen.king.ucstvoting.fragment.LuckydrawFragment;
import com.vote.queen.king.ucstvoting.fragment.QueenFragment;

public class MainActivity extends AppCompatActivity {

    private Class<?> mClss;
    public static BackdropContainer backdropContainer;
    private CardView boy, girl, home, game;
    public static Fragment oldFragment;

    public static TextView tvk, tvq;

    private boolean doubleBackToExitPressedOnce = false; //double exit

    //permission
    private static final int REQUEST_CODE_PERMISSION = 2;
    String[] mPermission = {Manifest.permission.CAMERA, Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.init_tool_bar);
        toolbar.setTitle("Fresher Welcome");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        oldFragment = new HomeFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
        transaction.replace(R.id.container, new HomeFragment()).commit();

        //---backdrop container---//
        backdropContainer = (BackdropContainer) findViewById(R.id.backdropcontainer);
        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
        backdropContainer.attachToolbar(toolbar)
                .dropInterpolator(new LinearInterpolator())
                .dropHeight(height - getResources().getDimensionPixelSize(R.dimen.backdrop_height))
                .build();

        //---permission request
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission[0])
                    != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, mPermission[1])
                            != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, mPermission[2])
                            != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        mPermission, REQUEST_CODE_PERMISSION);
                // If any permission aboe not allowed by user, this condition will execute every tim, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        SharedPreferences sharedPreferences = getSharedPreferences("vote", MODE_PRIVATE);
        // Toast.makeText(this, sharedPreferences.getString("user", "" )+":"+sharedPreferences.getString("king","")+":"+sharedPreferences.getString("queen",""), Toast.LENGTH_LONG).show();
        Log.i("hhhh", sharedPreferences.getString("king", "") + " : " + sharedPreferences.getString("queen", ""));


        boy = findViewById(R.id.boy);
        girl = findViewById(R.id.girl);
        home = findViewById(R.id.home);
        home = findViewById(R.id.home);
        game = findViewById(R.id.happy_zone);

        TextView textView = findViewById(R.id.txtLog);
        if (sharedPreferences.getString("user", "").equals("")) textView.setText("Log in");
        else textView.setText("Log out");

        tvk = findViewById(R.id.kingResult);
        tvq = findViewById(R.id.queenResult);

        if (!sharedPreferences.getString("king", "").equals("0") && !sharedPreferences.getString("king", "").equals(""))
            tvk.setText("Voted");
        if (!sharedPreferences.getString("queen", "").equals("0") && !sharedPreferences.getString("king", "").equals(""))
            tvq.setText("Voted");

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragTransaction(R.id.container, new HomeFragment());
                backdropContainer.closeBackview();
                toolbar.setTitle("Fresher Welcome");

            }
        });

        boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragTransaction(R.id.container, new KingFragment());
                backdropContainer.closeBackview();
                toolbar.setTitle("King Selection");

            }
        });

        girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragTransaction(R.id.container, new QueenFragment());
                backdropContainer.closeBackview();
                toolbar.setTitle("Queen Selection");

            }
        });


        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragTransaction(R.id.container, new GameFragment());
                backdropContainer.closeBackview();
                toolbar.setTitle("Happy Zone");

            }
        });

        findViewById(R.id.about_us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fragTransaction(R.id.container, new AboutFragment());
                // backdropContainer.closeBackview();
                toolbar.setTitle("About us");
                LayoutInflater inflater = getLayoutInflater();

                View view1 = inflater.inflate(R.layout.fragment_about, null);


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.customizedAlert);
                AlertDialog alertDialog = builder.create();
                alertDialog.setView(view1);

                //alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                alertDialog.getWindow().getAttributes().windowAnimations = R.anim.dialog_enter;
                alertDialog.show();

            }
        });

        findViewById(R.id.lucky_draw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final SharedPreferences sharedPreferences = getSharedPreferences("vote", MODE_PRIVATE);

                if (!sharedPreferences.getString("user", "").equals("")) {

                    //---cusotm round dialog
                    LayoutInflater inflater = getLayoutInflater();
                    View v = inflater.inflate(R.layout.custom_dialog, null);

                    Button dialogVote = v.findViewById(R.id.dialgVote);
                    Button dialogCancel = v.findViewById(R.id.dialogCancel);
                    TextView dialogTxt = v.findViewById(R.id.dialogTxt);

                    dialogVote.setText("Logout");
                    dialogTxt.setText("Are you sure want to logout?");
                    LottieAnimationView lottieAnimationView = v.findViewById(R.id.animationView);
                    lottieAnimationView.setAnimation("logout.json");
                    //lottieAnimationView.loop(true);
                    lottieAnimationView.playAnimation();

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.customizedAlert);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.setView(v);


                    dialogVote.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("user", "");
                            editor.putString("king", "");
                            editor.putString("queen", "");
                            editor.putString("qrcode", "");
                            editor.commit();

                            startActivity(new Intent(MainActivity.this, MainActivity.class));
                            finish();
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

                } else {

                    startActivity(new Intent(MainActivity.this, LoginActivity.class));

                }

            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                if (grantResults.length == 3 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[2] == PackageManager.PERMISSION_GRANTED) {

                    // Success Stuff here
                }
        }
    }

    public void fragTransaction(int container, Fragment f) {
        if (f != null && !f.getClass().getSimpleName().equals(oldFragment.getClass().getSimpleName())) {
            Log.i("Fragment...", oldFragment.toString());
            Log.i("Fragment...", f.toString());
            oldFragment = f;

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            //transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
            transaction.replace(container, f).commit();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public void onBackPressed() {


        if (new HomeFragment().getClass().getSimpleName().equals(oldFragment.getClass().getSimpleName())) {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press again..", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            //transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
            transaction.replace(R.id.container, new HomeFragment()).commit();
            oldFragment = new HomeFragment();
        }
        backdropContainer.closeBackview();

    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.tt:
                // Toast.makeText(MainActivity.this, "Timetable", Toast.LENGTH_SHORT).show();

               }


        return super.onOptionsItemSelected(item);
    }*/


}
