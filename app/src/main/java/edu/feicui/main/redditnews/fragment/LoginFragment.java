package edu.feicui.main.redditnews.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import edu.feicui.main.redditnews.R;
import edu.feicui.main.redditnews.info.HttpInfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2016/11/2.
 */

public class LoginFragment extends Fragment implements View.OnClickListener{
//    ViewPager mVp;
     Button mBtn_register;
    Button mBtn_password;
    Button mBtn_login;
    EditText mEdit_name;
    EditText mEdit_password;
    RegisterFragment mRegisterFragment;
    PasswordFragment mPasswordFragment;
    OkHttpClient mClient;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_login,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtn_register= (Button) view.findViewById(R.id.btn_login_zhuce);
        mBtn_password= (Button) view.findViewById(R.id.btn_login_pass);
        mBtn_login= (Button) view.findViewById(R.id.btn_login_login);
        mEdit_name= (EditText) view.findViewById(R.id.edit_login_name);
        mEdit_password= (EditText) view.findViewById(R.id.edit_login_password);
//        mVp= (ViewPager) view.findViewById(R.id.vp_login);
        mBtn_register.setOnClickListener(this);
        mBtn_login.setOnClickListener(this);
        mBtn_password.setOnClickListener(this);
        mRegisterFragment = new RegisterFragment();
        mPasswordFragment=new PasswordFragment();
//       mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//           @Override
//           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//           }
//
//           @Override
//           public void onPageScrollStateChanged(int state) {
//
//           }
//
//           @Override
//           public void onPageSelected(int position) {
//
//           }
//       });
    }
    String string;
    Handler handler;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login_zhuce:{
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flyt_main,mRegisterFragment);
                fragmentTransaction.commit();
                break;
            }
            case R.id.btn_login_pass:{
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flyt_main,mPasswordFragment);
                fragmentTransaction.commit();
                break;
            }
            case R.id.btn_login_login:{
                String name = mEdit_name.getText().toString();
                String password = mEdit_password.getText().toString();
                mClient=new OkHttpClient();
                //构造器模式，构造请求
                FormBody.Builder formbuilder=new FormBody.Builder();
                formbuilder.add("ver","1");
                formbuilder.add("uid",name);
                formbuilder.add("pwd",password);
                formbuilder.add("device","0");
                Request.Builder requestbuilder=new Request.Builder();
                //通过构造器构建请求   网址
                requestbuilder.url(HttpInfo.BASE_URL+HttpInfo.LOGIN);
                //获取请求体   FormBody extends RequestBody
                RequestBody requestBody=formbuilder.build();
                //使用构造器，将请求体放入请求中
                requestbuilder.post(requestBody);
//                构建请求
                Request request = requestbuilder.build();
                //获取call模型
                Call call = mClient.newCall(request);
                //执行请求
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                   //响应体
                        ResponseBody responseBody = response.body();
                       string = responseBody.string();
                         handler.sendEmptyMessage(1);
                    }
                });

               handler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Log.e("---","string=="+string);
                        try {

                            JSONObject jsonObject=new JSONObject(string);
                            String message = jsonObject.getString("message");
                            String status = jsonObject.getString("status");
                            JSONObject data = jsonObject.getJSONObject("data");
                            int result = data.getInt("result");
                            String token = data.getString("token");
                            String explain = data.getString("explain");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
            }
        }

    }
}
