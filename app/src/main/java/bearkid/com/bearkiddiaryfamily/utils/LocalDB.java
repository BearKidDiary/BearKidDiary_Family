package bearkid.com.bearkiddiaryfamily.utils;

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
        return sharedPreferences.getString("phoneNum", null);
    }

    public void putUid(Long Uid) {
        editor.putLong("Uid", Uid).commit();
    }

    public Long getUid() {
        return sharedPreferences.getLong("Uid", -1);
    }

    public void putUserName(String userName) {
        editor.putString("userName", userName);
        editor.commit();
    }

    public String getUserName() {
        return sharedPreferences.getString("userName", "");
    }

    public void putUserSex(String sex) {
        editor.putString("userSex", sex).commit();
    }

    public String getUserSex() {
        return sharedPreferences.getString("userSex", "男");
    }

    public void putUserAddress(String userAddress) {
        editor.putString("userAddress", userAddress);
        editor.commit();
    }

    public String getUserAddress() {
        return sharedPreferences.getString("userAddress", "");
    }

    public void putUserEmail(String userEmail) {
        editor.putString("userEmail", userEmail);
        editor.commit();
    }

    public String getUserEmail() {
        return sharedPreferences.getString("userEmail", "");
    }
}
