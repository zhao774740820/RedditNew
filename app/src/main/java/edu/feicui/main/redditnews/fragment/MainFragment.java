package edu.feicui.main.redditnews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.feicui.main.redditnews.R;
import edu.feicui.main.redditnews.adapter.MainAdapter;
import edu.feicui.main.redditnews.info.NewsInfo;
import edu.feicui.main.redditnews.info.NewsListInfo;
import edu.feicui.main.redditnews.inter.OnLoadNewsListener;
import edu.feicui.main.redditnews.main.NewsActivity;
import edu.feicui.main.redditnews.util.NewsTask;
import me.maxwin.view.XListView;

/**
 * Created by Administrator on 2016/10/31.
 */

public class MainFragment extends Fragment implements XListView.IXListViewListener,OnLoadNewsListener,View.OnClickListener,AdapterView.OnItemClickListener{
    public static final String PATH="http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    Button mBtn_left;
    Button mBtn_right;
    TextView mTxt_main;
    Handler mHandler;
    ArrayList<NewsInfo> mData;
    MainAdapter mAdapter   ;

    XListView mXListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_main,container,false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTxt_main= (TextView) view.findViewById(R.id.txt_main_title);
        mHandler=new Handler();
        mXListView= (XListView)view.findViewById(R.id.xlst_main);
        mXListView.setOnItemClickListener(this);

        NewsTask task=new NewsTask();
        task.setListener(this);
        task.execute(PATH);


    }
    @Override
    public void news(String news) {

        Gson gson=new Gson();
        Type type=new TypeToken<NewsListInfo>(){}.getType();
        NewsListInfo content= gson.fromJson(news, type);
        mData= content.getData();

        mAdapter =new MainAdapter(getActivity());
        mAdapter.setData(mData);

        mXListView.setAdapter(mAdapter);
        //设置可以刷新
        mXListView.setPullRefreshEnable(true);
        //可以上拉加载
        mXListView.setPullLoadEnable(true);
        //下拉刷新和上拉加载必须设置监听事件，否则停不下来或者无法加载更多
        mXListView.setXListViewListener(this);


    }



    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                 stop();
            }
        },2000);
    }


    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

          stop();
            }
        },2000);
    }
    public void stop(){
        //停止刷新和加载
        mXListView.stopLoadMore();
        mXListView.stopRefresh();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mXListView.setRefreshTime(format.format(new Date(System.currentTimeMillis())));
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),NewsActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }
}
