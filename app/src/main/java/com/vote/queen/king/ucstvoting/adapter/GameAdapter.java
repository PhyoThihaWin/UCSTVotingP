package com.vote.queen.king.ucstvoting.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vote.queen.king.ucstvoting.R;
import com.vote.queen.king.ucstvoting.SelectionDetailActivity;
import com.vote.queen.king.ucstvoting.model.Agenda;
import com.vote.queen.king.ucstvoting.model.Selection;

import java.util.List;

public class GameAdapter extends Adapter<GameAdapter.MyViewHolder> {

    Context context;
    List<Agenda> selectionList;
    String kingqueen;

    int[] img = new int[]{R.drawable.gun, R.drawable.tin, R.drawable.ballshoot, R.drawable.rubber, R.drawable.basketball};

    public GameAdapter(Context context, List<Agenda> selectionList) {
        this.context = context;
        this.selectionList = selectionList;

    }

    @NonNull
    @Override
    public GameAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.game_item, viewGroup, false);

        return new GameAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.MyViewHolder myViewHolder, final int i) {

        Agenda agenda = selectionList.get(i);

        Glide.with(context)
                .load(img[i])

                .apply(new RequestOptions()
                        .centerCrop().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .dontAnimate())
                .into(myViewHolder.imageView);

        myViewHolder.txt1.setText(agenda.getTxt1());
        myViewHolder.txt2.setText(agenda.getTxt2());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneFb();
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, txt1, txt2;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);

            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);


        }
    }

    public void phoneFb() {
        //---cusotm round dialog
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v1 = inflater.inflate(R.layout.custom_game_dialog, null);

        TextView dialogTxt = v1.findViewById(R.id.dialogTxt);

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.customizedAlert);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(v1);
        alertDialog.getWindow().getAttributes().windowAnimations = R.anim.dialog_enter;
        alertDialog.show();
    }

}
