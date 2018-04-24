package com.example.p7xxtm1_g.kaoshi.utils;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by P7XXTM1-G on 2018/4/23.
 */

public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PlatformConfig.setQQZone("1106784911", "jWTs4VdGK7kkGLs1");
        UMConfigure.init(this,"5add5791a40fa377fa0000f7"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0

    }
}
