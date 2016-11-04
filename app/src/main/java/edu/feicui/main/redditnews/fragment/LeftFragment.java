package edu.feicui.main.redditnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import edu.feicui.main.redditnews.R;
import edu.feicui.main.redditnews.main.CenterActivity;

/**
 * Created by Administrator on 2016/10/27.
 */

public class LeftFragment extends Fragment implements View.OnClickListener{
    ImageView mImg;
    LinearLayout mLyt_news;
    LinearLayout mLyt_favorite;
    LinearLayout mLyt_local;
    LinearLayout mLyt_comment;
    LinearLayout mLyt_photo;
    MainFragment mMainFragment;
    //开始创建fragment方法
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_left,container,false);
    }
//表示fragment中的View正式创建成功
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLyt_news= (LinearLayout) view.findViewById(R.id.lyt_left_news);
        mLyt_favorite = (LinearLayout)  view.findViewById(R.id.lyt_left_favorite);
        mLyt_local= (LinearLayout)  view.findViewById(R.id.lyt_left_local);
        mLyt_comment= (LinearLayout)  view.findViewById(R.id.lyt_left_comment);
        mLyt_photo= (LinearLayout)  view.findViewById(R.id.lyt_left_photo);
        mMainFragment=new MainFragment();
        mLyt_news.setOnClickListener(this);
        mLyt_favorite.setOnClickListener(this);
        mLyt_local.setOnClickListener(this);
        mLyt_comment.setOnClickListener(this);
        mLyt_photo.setOnClickListener(this);

    }

    //当前fragment所在的Activity正式被创建的方法
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lyt_left_news:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flyt_main,mMainFragment);
                fragmentTransaction.commit();
                ((CenterActivity)getActivity()).closeMenu();
                break;
        }
    }
}
