package bearkid.com.bearkiddiaryfamily.model.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by admin on 2016/7/20.
 * 身高
 */
public class Height implements Serializable{
    public static final String ID = "Kid";
    public static final String HEIGHT = "Hheight";
    public static final String DATE = "Htime";

    @Expose
    private Long Hid;

    @Expose
    private Long Htime;

    @Expose
    private Float Hheight;

    private Kid kid;

    public Long getHid() {
        return Hid;
    }

    public void setHid(Long hid) {
        Hid = hid;
    }

    public Long getHtime() {
        return Htime;
    }

    public void setHtime(Long htime) {
        Htime = htime;
    }

    public Float getHheight() {
        return Hheight;
    }

    public void setHheight(Float hheight) {
        Hheight = hheight;
    }

    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }
}
