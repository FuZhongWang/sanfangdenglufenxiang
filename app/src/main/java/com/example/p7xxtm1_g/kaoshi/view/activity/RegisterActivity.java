package com.example.p7xxtm1_g.kaoshi.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.p7xxtm1_g.kaoshi.R;
import com.example.p7xxtm1_g.kaoshi.presenter.RegisterPresenter;
import com.example.p7xxtm1_g.kaoshi.utils.AccountValidatorUtil;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements View.OnClickListener {
    public String urll="https://www.zhaoapi.cn/user/reg";
    private EditText mobile_tag;
    private EditText password_tag;
    private Button register_sure;
    private TextView ralerting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        ralerting = (TextView) findViewById(R.id.ralerting);
        mobile_tag = (EditText) findViewById(R.id.mobile_tag);
        password_tag = (EditText) findViewById(R.id.password_tag);
        register_sure = (Button) findViewById(R.id.register_sure);
        register_sure.setOnClickListener(this);
    }

    @Override
    public void initDate() {

    }

    @Override
    public RegisterPresenter getBasePresenter() {
        return new RegisterPresenter();
    }

    @Override
    public int setView() {
        return R.layout.activity_register;
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailures() {

    }

    @Override
    public void onClick(View view) {
        if (!AccountValidatorUtil.isMobile(mobile_tag.getText().toString())){

            ralerting.setText(R.string.wrong_moblie);
            return;
        }
        if (!AccountValidatorUtil.isPassword(password_tag.getText().toString())){
            ralerting.setText(R.string.wrong_password);
            return;
        }


        ralerting.setText("");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("mobile",mobile_tag.getText().toString());
        hashMap.put("mobile",password_tag.getText().toString());
        basePresenter.register(urll,hashMap,this,this);
    }
}
