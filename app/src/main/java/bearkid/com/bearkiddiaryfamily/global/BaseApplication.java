package bearkid.com.bearkiddiaryfamily.global;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import java.util.Stack;

import bearkid.com.bearkiddiaryfamily.ui.activity.BaseActivity;
import cn.bmob.v3.Bmob;

/**
 * Created by admin on 2016/7/4.
 */
public class BaseApplication extends Application {

    Stack<BaseActivity> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        activityStack = new Stack<>();

        //第一：默认初始化
        Bmob.initialize(this, "c5470e90b9e42cb978bd22fa1309c7b4");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }

    /**
     * 退出整个程序
     * 在这里写释放SDK资源等代码
     */
    public void exit() {
        //TODO: 释放SDK的资源
        while (activityStack.size() != 0) {
            activityStack.peek().finish();
        }
        System.exit(0);
    }

    public void pushActivity(BaseActivity activity) {
        activityStack.push(activity);
        Log.i("zy", "Activity Stack push " + activityStack.size());
    }

    public void popActivity() {
        activityStack.pop();
        Log.i("zy", "Activity Stack pop " + activityStack.size());
    }
}
