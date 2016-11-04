package edu.feicui.main.redditnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.feicui.main.redditnews.R;
import edu.feicui.main.redditnews.main.CenterActivity;

/**
 * Created by Administrator on 2016/10/28.
 */

public class RightFragment extends Fragment implements View.OnClickListener{
    ImageView mImg_profile;
    TextView mTxt_profile;
    LoginFragment mLoginFragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_right, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImg_profile= (ImageView) view.findViewById(R.id.img_right_profile);
        mTxt_profile= (TextView) view.findViewById(R.id.txt_right_profile);
        mLoginFragment=new LoginFragment();
        mImg_profile.setOnClickListener(this);
        mTxt_profile.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flyt_main,mLoginFragment);
        fragmentTransaction.commit();
        ((CenterActivity)getActivity()).closeSecondaryMenu();
    }
}
