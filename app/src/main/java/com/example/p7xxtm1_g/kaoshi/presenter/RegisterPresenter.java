package com.example.p7xxtm1_g.kaoshi.presenter;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.p7xxtm1_g.kaoshi.model.CallBack.HttpUtilsCallBack;
import com.example.p7xxtm1_g.kaoshi.model.http.HttpUtils;

import java.util.HashMap;

/**
 * Created by P7XXTM1-G on 2018/4/23.
 */

public class RegisterPresenter extends BasePresenter {

    private final HttpUtils httpUtils;

    public RegisterPresenter() {
        httpUtils = HttpUtils.getHttpUtils();
    }
    public void register(String url, HashMap<String,String>hashMap, final Context context, final Activity activity){
            httpUtils.doPost(url, hashMap, new HttpUtilsCallBack() {
                @Override
                public void onSuccess(String string) {
                    if (string.equals("0")){
                        activity.finish();

                    }else{
                        Toast.makeText(context,"注册失败请检查账户重复",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailures(String string) {

                }
            });


    }
}
