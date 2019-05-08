package com.vote.queen.king.ucstvoting.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vote.queen.king.ucstvoting.R;

public class AboutFragment extends Fragment {
    //private Button btnView1,btnView2,btnView3,btnView4;
    private ImageView imv1, imv2, imv3, imv4;

    public AboutFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_about, container, false);
return view1;
//        imv1 = view1.findViewById(R.id.imv_programmer);
//        Glide.with(getActivity().getApplicationContext()).load(R.mipmap.pthw).into(imv1);
//        imv2 = view1.findViewById(R.id.imv_programmer1);
//        Glide.with(getActivity().getApplicationContext()).load(R.mipmap.mth).into(imv2);
//        imv3 = view1.findViewById(R.id.imv_programmer2);
//        Glide.with(getActivity().getApplicationContext()).load(R.mipmap.one).into(imv3);
//        imv4 = view1.findViewById(R.id.imv_programmer3);
//        Glide.with(getActivity().getApplicationContext()).load(R.mipmap.nho).into(imv4);
//
//        view1.findViewById(R.id.btn_view).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(getOpenFacebook(getActivity(), "fb://profile/100005014789228", "https://www.facebook.com/Phyø Thíhà Wìn"));
//
//            }
//        });
//
//
//        view1.findViewById(R.id.btn_view1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(getOpenFacebook(getActivity(), "fb://profile/100012018375126", "https://www.facebook.com/Myø Thiha"));
//            }
//        });
//
//        view1.findViewById(R.id.btn_view2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(getOpenFacebook(getActivity(), "fb://profile/100003738493275", "https://www.facebook.com/Zay Yar Phyo"));
//            }
//        });
//
//        view1.findViewById(R.id.btn_view3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(getOpenFacebook(getActivity(), "fb://profile/100010536673136", "https://www.facebook.com/Ñãiñg Htet"));
//            }
//        });
//
//        return view1;
//
//
//    }
//
//    public static Intent getOpenFacebook(Context context, String id, String link) {
//        try {
//            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
//            return new Intent(Intent.ACTION_VIEW, Uri.parse(id));
//        } catch (Exception e) {
//            return new Intent(Intent.ACTION_VIEW, Uri.parse(link));
//
//        }
//
//
 }
}
