package com.vote.queen.king.ucstvoting.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vote.queen.king.ucstvoting.R;
import com.vote.queen.king.ucstvoting.model.Agenda;

import java.util.List;

public class AgendaAdapter extends Adapter<AgendaAdapter.MyViewHolder> {

    Context context;
    List<Agenda> agendaList;


    public AgendaAdapter(Context context, List<Agenda> agendaList) {
        this.context = context;
        this.agendaList = agendaList;
    }

    @NonNull
    @Override
    public AgendaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.agenda_item, viewGroup, false);

        return new AgendaAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaAdapter.MyViewHolder myViewHolder, final int i) {

        Agenda agenda = agendaList.get(i);

        //Log.i("jjj", agendaList.size() + "");

        myViewHolder.txt1.setText(String.valueOf(i + 1));
        myViewHolder.txt2.setText(agenda.getTxt1());
        myViewHolder.txt3.setText(agenda.getTxt2());

    }

    @Override
    public int getItemCount() {
        return agendaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt1, txt2, txt3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt1 = itemView.findViewById(R.id.t1);
            txt2 = itemView.findViewById(R.id.t2);
            txt3 = itemView.findViewById(R.id.t3);


        }
    }
}
