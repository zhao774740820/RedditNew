package edu.feicui.main.redditnews.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import edu.feicui.main.redditnews.R;
import edu.feicui.main.redditnews.fragment.LeftFragment;
import edu.feicui.main.redditnews.fragment.MainFragment;
import edu.feicui.main.redditnews.fragment.RightFragment;
import me.maxwin.view.XListView;

/**
 * Created by Administrator on 2016/11/3.
 */

public class CenterActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String PATH="http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    Button mBtn_left;
    Button mBtn_right;
    TextView mTxt_main;
    SlidingMenu mSlidingMenu;
    Handler mHandler;
    XListView mXListView;
    LeftFragment mLftFragment;
    RightFragment mRightFragment;
    MainFragment mMainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_main);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        initView();
    }

    private void initView() {
        mSlidingMenu=new SlidingMenu(this);
        mBtn_left = (Button) findViewById(R.id.btn_left_main);
        mBtn_right = (Button) findViewById(R.id.btn_right_main);
        mTxt_main= (TextView) findViewById(R.id.txt_main_title);
        mHandler=new Handler();
        mXListView= (XListView)findViewById(R.id.xlst_main);
        mMainFragment=new MainFragment();
        mLftFragment=new LeftFragment();
        mRightFragment=new RightFragment();
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setBehindOffset(100);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flyt_main,mMainFragment);
        transaction.commit();
        mSlidingMenu.setMenu(R.layout.slid_left);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.lyt_slid_left,mLftFragment);
        fragmentTransaction.commit();
        mSlidingMenu.setSecondaryMenu(R.layout.slid_right);
        FragmentTransaction fragment = getSupportFragmentManager().beginTransaction();
        fragment.replace(R.id.lyt_slid_right,mRightFragment);
        fragment.commit();
        mSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        mSlidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);

        mBtn_left.setOnClickListener(this);
        mBtn_right.setOnClickListener(this);

    }






    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left_main:
                closeMenu();
                break;
            case R.id.btn_right_main:
                closeSecondaryMenu();
                break;
        }
    }

    public void closeSecondaryMenu() {
        if (mSlidingMenu.isSecondaryMenuShowing()) {
            mSlidingMenu.toggle();
        }
        mSlidingMenu.showSecondaryMenu();
    }

    public void closeMenu() {
        if (mSlidingMenu.isMenuShowing()) {
            mSlidingMenu.toggle();
        }
        mSlidingMenu.showMenu();
    }

}
