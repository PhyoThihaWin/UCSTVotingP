package com.vote.queen.king.ucstvoting.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vote.queen.king.ucstvoting.R;
import com.vote.queen.king.ucstvoting.adapter.KingQueenSelectionAdapter;
import com.vote.queen.king.ucstvoting.model.Selection;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KingFragment extends Fragment {

    KingQueenSelectionAdapter selectionAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    List<Selection> selectionList;


    public KingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        prepareData();
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_king, container, false);


        recyclerView = view.findViewById(R.id.recycler);
        selectionList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        selectionAdapter = new KingQueenSelectionAdapter(getContext(), selectionList,"updateking");
        recyclerView.setAdapter(selectionAdapter);

        return view;
    }

    public void prepareData() {
        selectionList.clear();
        selectionList.add(new Selection("Mg Htet Myat OO", "Section-B", "N0-1", 0));
        selectionList.add(new Selection("Mg Min Swe Mon", "Section-B", "N0-2", 1));
        selectionList.add(new Selection("Mg Nay Lin Htun", "Section-C", "No-3", 2));
        selectionList.add(new Selection("Mg Htet Naing Min", "Section-B", "N0-4", 3));
        selectionList.add(new Selection("Mg Bhone Myat Khaung", "Section-B", "N0-5", 4));
        selectionList.add(new Selection("Mg Paing Zayar Soe", "Section-B", "No-6", 5));
        selectionList.add(new Selection("Mg Kaung Sett Min", "Section-C", "N0-7", 6));
        selectionList.add(new Selection("Mg Min Oak Soe", "Section-A", "N0-8", 7));
    }

}
