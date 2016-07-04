package bearkid.com.bearkiddiaryfamily.global;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

import bearkid.com.bearkiddiaryfamily.ui.activity.BaseActivity;

/**
 * Created by admin on 2016/7/4.
 */
public class BaseApplication extends Application{

    Stack<BaseActivity> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 退出整个程序
     * 在这里写释放SDK资源等代码
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
