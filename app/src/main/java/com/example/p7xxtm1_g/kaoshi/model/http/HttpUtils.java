package com.example.p7xxtm1_g.kaoshi.model.http;

import com.example.p7xxtm1_g.kaoshi.model.CallBack.HttpUtilsCallBack;
import com.example.p7xxtm1_g.kaoshi.model.bean.JavaBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by P7XXTM1-G on 2018/4/23.
 */

public  class HttpUtils implements Callback {
    private HttpUtilsCallBack httpUtilsCallBack;
    private static HttpUtils httpUtils = new HttpUtils();
    private HttpUtils() {

    }
    public static HttpUtils getHttpUtils(){

        return httpUtils;
    }


    public void doPost(String url, HashMap<String,String>hashMap, HttpUtilsCallBack httpUtilsCallBack){
       this.httpUtilsCallBack=httpUtilsCallBack;
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        FormBody.Builder builder = new FormBody.Builder();
        Iterator<String> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = hashMap.get(key);
            builder.add(key,value);

        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(this);

    }

    @Override
    public void onFailure(Call call, IOException e) {
        String s = e.toString();
        httpUtilsCallBack.onFailures(s);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String string = response.body().string();
        Gson gson = new Gson();
        JavaBean javaBean = gson.fromJson(string, JavaBean.class);
        String code = javaBean.getCode();
        httpUtilsCallBack.onSuccess(code);

    }
}
