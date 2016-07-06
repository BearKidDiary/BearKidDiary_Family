package bearkid.com.bearkiddiaryfamily.global;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 利用SharePreference存储一些基本的数据
 */
public class LocalDB {
    public static final String FILE_NAME = "bearkid";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public LocalDB(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /**
     * 当前用户的手机号码
     */
    public void putPhoneNum(String phoneNum) {
        editor.putString("phoneNum", phoneNum);
        editor.commit();
    }

    /**
     * 获取当前用户的手机号码
     */
    public String getPhoneNum() {
        return sharedPreferences.getString("phoneNum", "");
    }
}
