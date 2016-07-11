package bearkid.com.bearkiddiaryfamily.global;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

import bearkid.com.bearkiddiaryfamily.ui.activity.BaseActivity;
import cn.bmob.v3.Bmob;

/**
 * Created by admin on 2016/7/4.
 */
public class BaseApplication extends Application{

    Stack<BaseActivity> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        activityStack = new Stack<>();

        //��һ��Ĭ�ϳ�ʼ��
        Bmob.initialize(this, "c5470e90b9e42cb978bd22fa1309c7b4");

        //�ڶ�����v3.4.7�汾��ʼ,����BmobConfig,������������ʱʱ�䡢�ļ���Ƭ�ϴ�ʱÿƬ�Ĵ�С���ļ��Ĺ���ʱ��(��λΪ��)��
        //BmobConfig config =new BmobConfig.Builder(this)
        ////����appkey
        //.setApplicationId("Your Application ID")
        ////����ʱʱ�䣨��λΪ�룩��Ĭ��15s
        //.setConnectTimeout(30)
        ////�ļ���Ƭ�ϴ�ʱÿƬ�Ĵ�С����λ�ֽڣ���Ĭ��512*1024
        //.setUploadBlockSize(1024*1024)
        ////�ļ��Ĺ���ʱ��(��λΪ��)��Ĭ��1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }

    /**
     * �˳���������
     * ������д�ͷ�SDK��Դ�ȴ���
     */
    public void exit(){
        while(activityStack.size()!=0){
            activityStack.peek().finish();
        }
    }

    public void pushActivity(BaseActivity activity){
        activityStack.push(activity);
    }

    public void popActivity(){
        activityStack.pop();
    }
}
