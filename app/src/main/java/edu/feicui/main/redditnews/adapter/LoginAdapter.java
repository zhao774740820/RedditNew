package edu.feicui.main.redditnews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import edu.feicui.main.redditnews.fragment.PasswordFragment;
import edu.feicui.main.redditnews.fragment.RegisterFragment;

/**
 * Created by Administrator on 2016/11/3.
 */

public class LoginAdapter extends PagerAdapter{
    PasswordFragment mPasswordFragment;
    RegisterFragment mRegisterFragment;
    public LoginAdapter(RegisterFragment registerFragment,PasswordFragment passwordFragment){
       this.mPasswordFragment=passwordFragment;
        this.mRegisterFragment=registerFragment;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView(container);
    }
}
