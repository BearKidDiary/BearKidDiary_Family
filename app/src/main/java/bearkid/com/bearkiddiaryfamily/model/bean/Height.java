package bearkid.com.bearkiddiaryfamily.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by admin on 2016/7/20.
 * 身高
 */
public class Height extends BmobObject {
    public static final String ID = "Kid";
    public static final String HEIGHT = "Kheight";
    public static final String DATE = "Htime";

    private int height;
    private String date;

    public int getKidHeight() {
        return height;
    }

    public void setKidHeight(int height) {
        this.height = height;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
