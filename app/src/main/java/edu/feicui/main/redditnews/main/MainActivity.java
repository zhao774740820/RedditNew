package edu.feicui.main.redditnews.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.feicui.main.redditnews.R;
import edu.feicui.main.redditnews.fragment.LeftFragment;
import edu.feicui.main.redditnews.fragment.LoginFragment;
import edu.feicui.main.redditnews.fragment.MainFragment;
import edu.feicui.main.redditnews.fragment.RightFragment;

/**
 * Created by Administrator on 2016/10/27.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    DrawerLayout mDrawerLayout;
    LeftFragment mLeftFragment;
    RightFragment mRightFragment;
    MainFragment mMainFragment;
    LoginFragment mLoginFragment;
    Button mBtn_left;
    Button mBtn_right;
    TextView mTxt_main;
    ImageView mImg_profile;
    TextView mTxt_profile;
    LinearLayout mLyt_news;
    LinearLayout mLyt_favorite;
    LinearLayout mLyt_local;
    LinearLayout mLyt_comment;
    LinearLayout mLyt_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        initView();

    }

    private void initView() {
        mDrawerLayout= (DrawerLayout)findViewById(R.id.dly_main);
        mBtn_left = (Button) findViewById(R.id.btn_left_main);
        mBtn_right = (Button) findViewById(R.id.btn_right_main);
        mTxt_main= (TextView) findViewById(R.id.txt_main_title);
        mImg_profile= (ImageView) findViewById(R.id.img_right_profile);
        mTxt_profile= (TextView) findViewById(R.id.txt_right_profile);
        mLyt_news= (LinearLayout) findViewById(R.id.lyt_left_news);
        mLyt_favorite = (LinearLayout) findViewById(R.id.lyt_left_favorite);
        mLyt_local= (LinearLayout) findViewById(R.id.lyt_left_local);
        mLyt_comment= (LinearLayout) findViewById(R.id.lyt_left_comment);
        mLyt_photo= (LinearLayout) findViewById(R.id.lyt_left_photo);
        mMainFragment=new MainFragment();
        mLeftFragment = new LeftFragment();
        mRightFragment = new RightFragment();
        mLoginFragment=new LoginFragment();
        mBtn_left.setOnClickListener(this);
        mBtn_right.setOnClickListener(this);
        mImg_profile.setOnClickListener(this);
        mLyt_news.setOnClickListener(this);
        mLyt_favorite.setOnClickListener(this);
        mLyt_local.setOnClickListener(this);
        mLyt_comment.setOnClickListener(this);
        mLyt_photo.setOnClickListener(this);
        mTxt_main.setText("资讯");
        // 获取业务对象  每执行一次 业务 就必须重新获取一次业务对象
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        //      执行业务
        fragmentTransaction.replace(R.id.flyt_main,mMainFragment);
        //提交后才能实现业务
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left_main: {
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT) == false) {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
                break;
            }
            case R.id.lyt_left_news:{
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)==true) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flyt_main,mMainFragment);
                fragmentTransaction.commit();
               break;
            }
            case R.id.btn_right_main: {
                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT) == false) {
                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                } else {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                }
                break;
            }
            case R.id.img_right_profile: {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flyt_main, mLoginFragment);
                fragmentTransaction.commit();
                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT) == true) {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                }
                mTxt_main.setText("用户登录");
                break;
            }
            case R.id.txt_right_profile: {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flyt_main, mLoginFragment);
                fragmentTransaction.commit();
                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT) == true) {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                }
                mTxt_main.setText("用户登录");
                break;
            }

        }

    }
}
