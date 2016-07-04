package bearkid.com.bearkiddiaryfamily.global;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

/**
 * Created by admin on 2016/7/4.
 */
public class BaseApplication extends Application{

    Stack<Activity> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
    }


}
