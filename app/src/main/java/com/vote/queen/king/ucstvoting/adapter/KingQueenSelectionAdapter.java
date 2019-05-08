package com.vote.queen.king.ucstvoting.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vote.queen.king.ucstvoting.R;
import com.vote.queen.king.ucstvoting.SelectionDetailActivity;
import com.vote.queen.king.ucstvoting.model.Selection;

import java.util.List;

public class KingQueenSelectionAdapter extends Adapter<KingQueenSelectionAdapter.MyViewHolder> {

    Context context;
    List<Selection> selectionList;
    String kingqueen;

    int[] img = new int[]{R.drawable.htetmyatoo,R.drawable.minswemon,R.drawable.naylinhtun,R.drawable.htetnaingmin, R.drawable.bhonemyatkhaung,R.drawable.paingzayarsoe,R.drawable.kaungsetmin,R.drawable.minoaksoe,R.drawable.khinecherrysoe,R.drawable.nancherrynyein,R.drawable.winpapaaung,R.drawable.sanpapaaung,R.drawable.moethihkhine,R.drawable.yinmonhtwe,R.drawable.maymephone,R.drawable.panphyu};

    public KingQueenSelectionAdapter(Context context, List<Selection> selectionList, String kingqueen) {
        this.context = context;
        this.selectionList = selectionList;
        this.kingqueen = kingqueen;
    }

    @NonNull
    @Override
    public KingQueenSelectionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.selection_item, viewGroup, false);

        return new KingQueenSelectionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KingQueenSelectionAdapter.MyViewHolder myViewHolder, final int i) {

        Selection selection = selectionList.get(i);
        myViewHolder.name.setText(selection.getName());
        myViewHolder.txt1.setText(selection.getTxt1());
        myViewHolder.txt2.setText(selection.getTxt2());
        if(kingqueen.equals("updateking"))
        //GlideApp.with(context).load(img[i]).into(myViewHolder.imageView);
        Glide.with(context)
                .load(img[i])

                .apply(new RequestOptions()
                        .centerCrop().circleCrop().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .dontAnimate())
                .into(myViewHolder.imageView);
        else{
            Glide.with(context)
                    .load(img[i+8])

                    .apply(new RequestOptions()
                            .centerCrop().circleCrop().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .dontAnimate())
                    .into(myViewHolder.imageView);

        }

        myViewHolder.linearNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SelectionDetailActivity.class);
                intent.putExtra("index", kingqueen + "-" + (i+1));
                context.startActivity(intent);

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
        View view;
        LinearLayout linearNext;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);
            linearNext = itemView.findViewById(R.id.linearNext);

            imageView = itemView.findViewById(R.id.profile);

        }
    }
}
