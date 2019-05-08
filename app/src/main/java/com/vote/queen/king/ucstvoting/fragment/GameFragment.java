package com.vote.queen.king.ucstvoting.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vote.queen.king.ucstvoting.R;
import com.vote.queen.king.ucstvoting.adapter.AgendaAdapter;
import com.vote.queen.king.ucstvoting.adapter.GameAdapter;
import com.vote.queen.king.ucstvoting.model.Agenda;
import com.vote.queen.king.ucstvoting.model.Selection;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    private ImageView imv1,imv2,imv3,imv4,imv5;

    GameAdapter gameAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    List<Agenda> agendaList;


    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_game, container, false);

//        imv1 = v.findViewById(R.id.gun);
//        Glide.with(getActivity().getApplicationContext()).load(R.drawable.gun).into(imv1);
//
//        imv2 = v.findViewById(R.id.tin);
//        Glide.with(getActivity().getApplicationContext()).load(R.drawable.tin).into(imv2);
//
//        imv3= v.findViewById(R.id.ballshoot);
//        Glide.with(getActivity().getApplicationContext()).load(R.drawable.ballshoot).into(imv3);
//
//        imv4 = v.findViewById(R.id.rubber);
//        Glide.with(getActivity().getApplicationContext()).load(R.drawable.rubber).into(imv4);
//
//        imv5 = v.findViewById(R.id.basketball);
//        Glide.with(getActivity().getApplicationContext()).load(R.drawable.basketball).into(imv5);

        recyclerView = v.findViewById(R.id.recycler);
        agendaList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        prepareData();
        gameAdapter = new GameAdapter(getActivity(), agendaList);
        recyclerView.setAdapter(gameAdapter);


//        imvBasketBall=v.findViewById(R.id.basketball);
//        imvBasketBall.setImageURI(Uri.parse("https://www.google.com/search?biw=740&bih=690&tbm=isch&sa=1&ei=ZXE9XIaKA5mwrQGihKKACw&q=table+tennis+ball+into+basket&oq=table+tennis+ball+into+basket&gs_l=img.3...9388.11401..11737...0.0..0.131.977.0j8......0....1..gws-wiz-img.......0.Whmfm6ABH68#imgrc=CeooQN8rv4BAAM"));



        return v;
    }

    public void prepareData() {
        agendaList.clear();
        agendaList.add(new Agenda("Gun", "-5 shots = 500 ks"));
        agendaList.add(new Agenda("Shoot Ball", "-5 shots = 500 ks"));
        agendaList.add(new Agenda("Shot Arrow", "-5 shots = 500 ks"));
        agendaList.add(new Agenda("Shot rubber band", "-5 shots = 500 ks"));
        agendaList.add(new Agenda("Basketball", "-5 shots = 500 ks"));

    }

}
