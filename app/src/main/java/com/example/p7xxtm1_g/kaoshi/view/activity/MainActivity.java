package com.example.p7xxtm1_g.kaoshi.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p7xxtm1_g.kaoshi.R;
import com.example.p7xxtm1_g.kaoshi.presenter.BasePresenter;
import com.example.p7xxtm1_g.kaoshi.presenter.MainPresenter;
import com.example.p7xxtm1_g.kaoshi.utils.AccountValidatorUtil;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity<MainPresenter> implements View.OnClickListener {

    private EditText moblie;
    private EditText password;
    private Button login;
    private Button register;
    private TextView alerting;
    public String url = "https://www.zhaoapi.cn/user/login";
    private Button threelogin;
    UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }
        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }
        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }
        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    private Button fenxiang;
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }
        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,"成功                                        了",Toast.LENGTH_LONG).show();
        }
        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this,"失                                            败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }
        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,"取消                                          了",Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        fenxiang = (Button) findViewById(R.id.fenxiang);
        threelogin = (Button) findViewById(R.id.threelogin);
        alerting = (TextView) findViewById(R.id.alerting);
        moblie = (EditText) findViewById(R.id.mobile);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        login.setOnClickListener(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        threelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                
                UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);

            }
        });
        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // UMImage image = new UMImage(MainActivity.this, "imageurl");
             //   new ShareAction(MainActivity.this).withText("hello").withMedia(image).share();
             //   new ShareAction(MainActivity.this).withText("hello").setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
             //           .setCallback(shareListener).open();
               /* UMImage image = new UMImage(MainActivity.this, "imageurl");//网络图片
                new ShareAction(MainActivity.this).withText("hello").withMedia(image).share();*/
                UMWeb web = new UMWeb("https://developer.umeng.com/docs/66632/detail/66639#shareboard");
                web.setTitle("This is music title");//标题

                web.setDescription("my description");//描述

                new ShareAction(MainActivity.this).withMedia(web).withText("sdasd").setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
             .setCallback(shareListener).open();

            }
        });
    }

    @Override
    public void initDate() {


    }

    @Override
    public MainPresenter getBasePresenter() {
        return new MainPresenter();
    }

    @Override
    public int setView() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailures() {

    }

    @Override
    public void onClick(View view) {
        Log.e("ccc", "onClick: ");

        if (!AccountValidatorUtil.isMobile(moblie.getText().toString())){

            alerting.setText(R.string.wrong_moblie);
            return;
        }
        if (!AccountValidatorUtil.isPassword(password.getText().toString())){
            alerting.setText(R.string.wrong_password);
            return;
        }

        alerting.setText("");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("mobile",moblie.getText().toString());
        hashMap.put("password",password.getText().toString());
        basePresenter.login(url,hashMap,MainActivity.this,MainActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
