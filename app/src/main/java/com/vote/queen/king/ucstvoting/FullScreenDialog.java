package com.vote.queen.king.ucstvoting;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sdsmdg.tastytoast.TastyToast;
import com.vote.queen.king.ucstvoting.adapter.AgendaAdapter;
import com.vote.queen.king.ucstvoting.model.Agenda;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FullScreenDialog extends DialogFragment {

    public static String TAG = "FullScreenDialog";

    AgendaAdapter agendaAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    List<Agenda> agendaList;
    ProgressDialog loading;
    ProgressBar progressBar;

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.full_screen_dialog, container, false);

        progressBar = view.findViewById(R.id.noData);

        // AppBarLayout appBarLayout = view.findViewById(R.id.appbar);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        toolbar.setTitle("Welcome Agenda");

        //loading = ProgressDialog.show(getActivity(), "Please wait...", "Getting data", false, false);

        recyclerView = view.findViewById(R.id.recycler);
        agendaList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);


        //--check network condition
        if (!isNetworkConnected()) {
            Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.VISIBLE);
        } else {

            JSON_DATA_WEB_CALL();
        }

        return view;
    }

   /* public void prepareData() {
        //agendaList.clear();
        JSON_DATA_WEB_CALL();
        if (agendaList.isEmpty()) Toast.makeText(getContext(), "gggg", Toast.LENGTH_SHORT).show();
    }*/


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            //---animate the full screen dialog---//
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogThemeAnim;

            dialog.getWindow().setLayout(width, height);
        }
    }

    public void JSON_DATA_WEB_CALL() {

        String url_json = "http://kaungkhantshar.pe.hu/vote/agenda.json";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url_json,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                        // loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        TastyToast.makeText(getActivity(), "connection error", Toast.LENGTH_SHORT, TastyToast.ERROR).show();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array) {

        //  etOutput.setText("");
        agendaList.clear();

        for (int i = array.length() - 1; i >= 0; i--) {

            try {
                JSONObject json = array.getJSONObject(i);
                //   etOutput.append(reLetter + " , " + json.getString(JSON_COUNT) + "\n");
                agendaList.add(new Agenda(json.getString("name"), json.getString("member")));

            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
        progressBar.setVisibility(View.GONE);
        if (agendaList.isEmpty()) progressBar.setVisibility(View.VISIBLE);
        agendaAdapter = new AgendaAdapter(getActivity(), agendaList);
        recyclerView.setAdapter(agendaAdapter);

    }



}
