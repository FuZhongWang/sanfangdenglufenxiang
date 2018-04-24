package com.example.p7xxtm1_g.kaoshi.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.p7xxtm1_g.kaoshi.R;
import com.example.p7xxtm1_g.kaoshi.presenter.BasePresenter;
import com.example.p7xxtm1_g.kaoshi.view.interfaces.IBaseView;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by P7XXTM1-G on 2018/4/23.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {
    public T basePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setView());
        initView();
        initDate();

        basePresenter=getBasePresenter();

    }

    public abstract void initView();
    public abstract void initDate();
    public abstract T getBasePresenter();
    public abstract int setView();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
