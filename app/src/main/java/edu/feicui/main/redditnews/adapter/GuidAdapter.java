package edu.feicui.main.redditnews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/27.
 */

public class GuidAdapter extends PagerAdapter {
    ImageView []mRes;
    public GuidAdapter(ImageView[]mRes){
        this.mRes=mRes;
    }
    @Override
    //滑动次数
    public int getCount() {
        return mRes==null?0:mRes.length;
    }

    @Override
    //判断加载的View是否来自object
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    //初始化条目
    public Object instantiateItem(ViewGroup container, int position) {
        //向container中添加数据，用于在ViewPager中显示
        ImageView imageView=mRes[position];
        container.addView(imageView);
        return imageView;
    }

    @Override
    //销毁看不到的条目
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mRes[position]);
    }
}
