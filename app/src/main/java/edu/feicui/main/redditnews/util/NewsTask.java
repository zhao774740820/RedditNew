package edu.feicui.main.redditnews.util;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import edu.feicui.main.redditnews.inter.OnLoadNewsListener;

/**
 * Created by Administrator on 2016/10/28.
 */

    public class NewsTask extends AsyncTask<String,Void,String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
   String mNews=null;
    HttpURLConnection urlConnection=null;
    InputStream inputStream=null;
    @Override
    protected String doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode==200) {
                inputStream = urlConnection.getInputStream();
                byte[]bytes=new byte[2*1024];
                int len=0;
                StringBuffer buffer=new StringBuffer();
                while ((len=inputStream.read(bytes))!=-1){
                    buffer.append(new String(bytes,0,len));
                }
                mNews=buffer.toString();
                return mNews;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (mNews != null&onLoadNewsListener!=null) {
            onLoadNewsListener.news(mNews);
        }

    }
    OnLoadNewsListener onLoadNewsListener;
    public void setListener(OnLoadNewsListener onLoadNewsListener){
        this.onLoadNewsListener=onLoadNewsListener;
    }
}
