package com.example.p7xxtm1_g.kaoshi.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.p7xxtm1_g.kaoshi.model.CallBack.HttpUtilsCallBack;
import com.example.p7xxtm1_g.kaoshi.model.http.HttpUtils;
import com.example.p7xxtm1_g.kaoshi.view.activity.ShowActivity;

import java.util.HashMap;

/**
 * Created by P7XXTM1-G on 2018/4/23.
 */

public class MainPresenter extends BasePresenter {

    private final HttpUtils httpUtils;

    public MainPresenter() {
        httpUtils = HttpUtils.getHttpUtils();
    }
    public void login(String url, HashMap<String,String>hashMap, final Activity activity, final Context context){

        httpUtils.doPost(url, hashMap, new HttpUtilsCallBack() {
            @Override
            public void onSuccess(String string) {
             if (string.equals("0")){
                 Intent intent = new Intent(context, ShowActivity.class);
                 activity.startActivity(intent);

             }
            }

            @Override
            public void onFailures(String string) {

            }
        });

    }
}
