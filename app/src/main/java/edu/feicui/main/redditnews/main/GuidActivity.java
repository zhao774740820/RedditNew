package edu.feicui.main.redditnews.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import edu.feicui.main.redditnews.R;
import edu.feicui.main.redditnews.adapter.GuidAdapter;

public class GuidActivity extends Activity {
    int mPosition;
    ViewPager mVp;
    ImageView[] mImg;
    ImageView[]mRes;
    //滑动图片
    int[]mResId={R.mipmap.welcome, R.mipmap.wy, R.mipmap.bd, R.mipmap.small};
    GuidAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        mPosition=this.getIntent().getIntExtra("position",-1);
        initView();
        initAdapter();
    }
    private void initAdapter() {
        mAdapter=new GuidAdapter(mRes);
        mVp.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
    private void initView() {
        mVp= (ViewPager) findViewById(R.id.vp_guid);
        mImg=new ImageView[4];
        //四个点
        mImg[0]= (ImageView) findViewById(R.id.img_guid_0);
        mImg[1]= (ImageView) findViewById(R.id.img_guid_1);
        mImg[2]= (ImageView) findViewById(R.id.img_guid_2);
        mImg[3]= (ImageView) findViewById(R.id.img_guid_3);
        //选中后的点
        mImg[0].setImageResource(R.mipmap.adware_style_selected);
        //可滑动的图片
        mRes=new ImageView[4];
        for (int i=0;i<4;i++){
            mRes[i]=new ImageView(this);
            mRes[i].setImageResource(mResId[i]);
        }
        //内容发生改变时
        mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            // 滑动中
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            // 滑动状态改变的时候。
            public void onPageScrollStateChanged(int state) {

            }

            @Override
            // 真正的滑动到位之后执行的方法
            public void onPageSelected(int position) {
                for (int i=0;i<mImg.length;i++){
                    mImg[i].setImageResource(R.mipmap.adware_style_default);
                    if (position>=3){
                        startActivity(new Intent(GuidActivity.this,LogoActivity.class));
                        GuidActivity.this.finish();
                       overridePendingTransition(R.anim.enter, R.anim.exit);
                    }
                }
                mImg[position].setImageResource(R.mipmap.adware_style_selected);
            }


        });

    }
}
