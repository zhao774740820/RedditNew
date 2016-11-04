package edu.feicui.main.redditnews.main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import edu.feicui.main.redditnews.R;

/**
 * Created by Administrator on 2016/10/27.
 */

public class LogoActivity extends Activity {
    private static final String PREFS_NAME = "NoFirst";
    private static final String IS_FIRST = "first";
    ImageView mImg_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = this.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean(IS_FIRST, true);
        if (flag) {//第一次
            int position=0;
            Intent intent=new Intent(LogoActivity.this,GuidActivity.class);
            intent.putExtra("position",position);
            startActivity(intent);
            //获取编辑器对象
            SharedPreferences.Editor edit = sharedPreferences.edit();
            //使用编辑器对象添加数据
            edit.putBoolean(IS_FIRST,false);
            //提交数据
            edit.commit();
            this.finish();
        }else{//不是第一次
             setContentView(R.layout.activity_logo);
             mImg_logo= (ImageView) findViewById(R.id.img_logo);
            Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.change);
            mImg_logo.startAnimation(loadAnimation);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }


                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                     //跳转
                    Intent intent=new Intent(LogoActivity.this,CenterActivity.class);
                    startActivity(intent);
                    LogoActivity.this.finish();
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                }
            });
        }
    }
}
