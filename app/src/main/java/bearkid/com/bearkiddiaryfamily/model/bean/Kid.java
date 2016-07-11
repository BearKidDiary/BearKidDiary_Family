package bearkid.com.bearkiddiaryfamily.model.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by admin on 2016/7/11.
 */
public class Kid extends BmobObject{
    private String Kname;
    private String Ksex;
    private BmobDate Kbirthday;
    private String Kask;
    private Family family;
    private BmobFile Kavatar;

    public String getKname() {
        return Kname;
    }

    public void setKname(String kname) {
        Kname = kname;
    }

    public String getKsex() {
        return Ksex;
    }

    public void setKsex(String ksex) {
        Ksex = ksex;
    }

    public BmobDate getKbirthday() {
        return Kbirthday;
    }

    public void setKbirthday(BmobDate kbirthday) {
        Kbirthday = kbirthday;
    }

    public String getKask() {
        return Kask;
    }

    public void setKask(String kask) {
        Kask = kask;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }
}
