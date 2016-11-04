package edu.feicui.main.redditnews.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import edu.feicui.main.redditnews.R;
import edu.feicui.main.redditnews.info.NewsInfo;
import edu.feicui.main.redditnews.info.NewsListInfo;
import edu.feicui.main.redditnews.inter.OnLoadNewsListener;
import edu.feicui.main.redditnews.util.NewsTask;

/**
 * Created by Administrator on 2016/10/28.
 */

public class NewsActivity extends AppCompatActivity implements OnLoadNewsListener,View.OnClickListener{
    public static final String PATH="http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
   TextView mTxt;
    ArrayList<NewsInfo> mData;
    int mPosition;
   WebView mWeb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=this.getIntent();
        mPosition=intent.getIntExtra("position",-1);
        setContentView(R.layout.activity_news);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
   mTxt= (TextView) findViewById(R.id.txt_news_back);
        mWeb= (WebView) findViewById(R.id.web_news);
        mTxt.setOnClickListener(this);
        NewsTask task=new NewsTask();
        task.setListener(this);
        task.execute(PATH);
    }


    @Override
    public void news(String news) {

        Gson gson=new Gson();
        NewsListInfo content = gson.fromJson(news, new TypeToken<NewsListInfo>() {
        }.getType());
        mData=content.getData();

            mWeb.loadUrl(mData.get(mPosition-1).getLink());

            //设置手机客户端显示样式
            WebSettings settings = mWeb.getSettings();
            //自动识别是手机端还是网页端
            settings.setJavaScriptEnabled(true);
            //设置自动适应屏幕大小                   尽可能的在一行中显示
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            //设置推荐使用的窗口
            settings.setUseWideViewPort(true);
//        //自适应大小，并且可以任意放大缩小
//        settings.setLoadWithOverviewMode(true);
//        //设置放大缩小按钮
//        settings.setBuiltInZoomControls(true);

            //设置自己的客户端
            mWeb.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, url);
                }
            });


    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(NewsActivity.this,CenterActivity.class));
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode== KeyEvent.KEYCODE_BACK) {
//            mWeb.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
