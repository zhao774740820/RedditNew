package edu.feicui.main.redditnews.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import edu.feicui.main.redditnews.info.NewsInfo;
import edu.feicui.main.redditnews.info.NewsListInfo;
import edu.feicui.main.redditnews.inter.OnLoadNewsListener;
import edu.feicui.main.redditnews.util.NewsTask;

/**
 * Created by Administrator on 2016/10/27.
 */

public class BaseActivity extends AppCompatActivity implements OnLoadNewsListener{
    public static final String PATH="http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    String mContent;
    ArrayList<NewsInfo> mData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        NewsTask task=new NewsTask();
        task.setListener(this);
        task.execute(PATH);
    }

    @Override
    public void news(String news) {
        this.mContent =news;
        Gson gson=new Gson();
        Type type=new TypeToken<NewsListInfo>(){}.getType();
        NewsListInfo content= gson.fromJson(mContent, type);
        mData= content.getData();


    }
}
