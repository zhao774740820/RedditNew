package edu.feicui.main.redditnews.util;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Hashtable;
import java.util.Map;

import edu.feicui.main.redditnews.inter.OnLoadResponseListener;

/**
 * Created by Administrator on 2016/11/2.
 */

public class HttpUtil {
    public static void collectionGET(String url, final OnLoadResponseListener listener, RequestQueue requestQueue) {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.getResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("---", "VolleyError==" + error.getMessage());
            }
        });
        requestQueue.add(request);
    }

    public static void collectionPOST(String url, final String email, final OnLoadResponseListener listener, RequestQueue requestQueue) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
           listener.getResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("---", "VolleyError==" + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new Hashtable<>();
                map.put("ver","1");
               map.put("email",email);
                return map;
            }
        };
        requestQueue.add(request);
    }
}
