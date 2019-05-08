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
public class QueenFragment extends Fragment {


    KingQueenSelectionAdapter selectionAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    List<Selection> selectionList;

    public QueenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_queen, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        selectionList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        prepareData();
        selectionAdapter = new KingQueenSelectionAdapter(getContext(), selectionList,"updatequeen");
        recyclerView.setAdapter(selectionAdapter);

        return view;
    }

    public void prepareData() {


        selectionList.add(new Selection("Ma Khine Cherry Soe", "Section-C", "N0-1",8));
        selectionList.add(new Selection("Nan Cherry Nyein", "Section-C", "N0-2", 9));
        selectionList.add(new Selection("Ma Win Pa Pa Aung", "Section-C", "N0-3", 10));
        selectionList.add(new Selection("Ma San Pa Pa Aung", "Section-A", "No-4", 11));
        selectionList.add(new Selection("Ma Moe Thin Khine", "Section-C", "N0-5", 12));
        selectionList.add(new Selection("Ma Yin Mon Htwe", "Section-C", "N0-6", 13));
        selectionList.add(new Selection("Ma May Me Phone", "Section-C", "N0-7", 14));
        selectionList.add(new Selection("Mi Pan Phyu", "Section-B", "N0-8", 15));
//
    }

}
