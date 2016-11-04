package edu.feicui.main.redditnews.fragment;

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

public class PasswordFragment extends Fragment implements View.OnClickListener,OnLoadResponseListener{
    Button mBtn;
    EditText mEdit;
    RequestQueue mRequestQueue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_password,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtn= (Button) view.findViewById(R.id.btn_password);
        mEdit= (EditText) view.findViewById(R.id.edit_password_email);
        mBtn.setOnClickListener(this);
       mRequestQueue= Volley.newRequestQueue(getActivity());
    }

    @Override
    public void onClick(View v) {
        String email=mEdit.getText().toString();
        HttpUtil.collectionPOST(HttpInfo.BASE_URL+HttpInfo.FORGETPASS,email,this,mRequestQueue);
    }
    @Override
    public void getResponse(String ss) {
        Log.e("---","message=="+ss);

        try {
            JSONObject jsonObject = new JSONObject(ss);
            String message = jsonObject.getString("message");
            String status = jsonObject.getString("status");
            JSONObject data = jsonObject.getJSONObject("data");
            int result = data.getInt("result");
            String explain = data.getString("explain");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
