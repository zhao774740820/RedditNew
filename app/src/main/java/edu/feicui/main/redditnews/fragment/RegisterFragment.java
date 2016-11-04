package edu.feicui.main.redditnews.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import edu.feicui.main.redditnews.R;
import edu.feicui.main.redditnews.info.HttpInfo;
import edu.feicui.main.redditnews.inter.OnLoadResponseListener;
import edu.feicui.main.redditnews.util.HttpUtil;

/**
 * Created by Administrator on 2016/11/2.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener,OnLoadResponseListener{
    Button mBtn;
    EditText mEdit_email;
    EditText mEdit_name;
    EditText mEdit_password;
    RequestQueue mRequestQueue;
    private static final String PREFS_NAME = "register";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_register,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtn= (Button) view.findViewById(R.id.btn_register);
        mEdit_email= (EditText) view.findViewById(R.id.edit_register_email);
        mEdit_name= (EditText) view.findViewById(R.id.edit_register_name);
        mEdit_password= (EditText) view.findViewById(R.id.edit_register_password);
        mBtn.setOnClickListener(this);
        mRequestQueue = Volley.newRequestQueue(getActivity());
    }

    @Override
    public void onClick(View v) {
        String email = mEdit_email.getText().toString();
        String name = mEdit_name.getText().toString();
        String password = mEdit_password.getText().toString();
       HttpUtil.collectionGET(HttpInfo.BASE_URL+HttpInfo.REGISTER+"ver=1&uid="+name+"&email="+email+"&pwd="+password,this,mRequestQueue);
//        Toast.makeText(getActivity(),explain,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getResponse(String s) {
        Log.e("==","message=="+s);
        try {
            JSONObject jsonObject=new JSONObject(s);
            String message = jsonObject.getString("message");
            int status = jsonObject.getInt("status");
            JSONObject data = jsonObject.getJSONObject("data");
            int result = data.getInt("result");
            String token = data.getString("token");
            String explain = data.getString("explain");
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt("result",result);
            edit.putString("token",token);
            edit.putString("explain",explain);
            edit.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
